package com.barberia.demo.servicio;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.barberia.demo.entidades.ImagenEntidad;
import com.barberia.demo.excepciones.MiExcepcion;
import com.barberia.demo.repositorios.imagenRepostiorio;

@Service
public class imagenServicio {
    @Autowired
    imagenRepostiorio iRepositorio;

    // CARGAR FOTO DE PERFIL
    @Transactional
    public ImagenEntidad guardar(MultipartFile archivo) throws MiExcepcion {
        if (archivo != null && !archivo.isEmpty()) {
            try {
                ImagenEntidad imagen = new ImagenEntidad();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return iRepositorio.save(imagen);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return null;

            }
        } else {
            System.out.println("No se cargo la imagen pana");
            throw new MiExcepcion("El archivo proporcionado es nulo o está vacío");

        }
    }

    // ACTUALIZAR FOTO DE PERFIL
    public ImagenEntidad actualizarImg(MultipartFile archivo, UUID idImagen) throws MiExcepcion {
        if (archivo != null) {
            try {
                ImagenEntidad imagen = new ImagenEntidad();
                if (idImagen != null) {
                    Optional<ImagenEntidad> respuesta = iRepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return iRepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    // LISTAR TODAS LAS FOTOS DE LA BASE DE DATOS.
    @Transactional(readOnly = true)
    public List<ImagenEntidad> listarTodos() {
        return iRepositorio.findAll();
    }

    public ImagenEntidad guardarImagenPredeterminada() throws MiExcepcion {
        try {
            // Cargar la imagen desde el classpath
            ClassPathResource resource = new ClassPathResource("static/img/usuarioLogo.png");

            // Obtener el contenido de la imagen como un array de bytes
            byte[] contenido = Files.readAllBytes(resource.getFile().toPath());

            ImagenEntidad imagen = new ImagenEntidad();
            imagen.setNombre("imagen_predeterminada.jpg");
            imagen.setMime("image/jpeg"); // Cambia esto según el formato de tu imagen
            imagen.setContenido(contenido);

            return iRepositorio.save(imagen);
        } catch (IOException e) {
            throw new MiExcepcion("Error al cargar la imagen predeterminada: " + e.getMessage());
        }
    }

}
