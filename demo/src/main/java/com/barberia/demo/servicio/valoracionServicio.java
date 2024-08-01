package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberia.demo.dtos.BarberoDTO;
import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.dtos.ValoracionDTO;
import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;
import com.barberia.demo.repositorios.barberoRepositorio;
import com.barberia.demo.repositorios.usuarioRepositorio;
import com.barberia.demo.repositorios.valoracionRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@Service
public class valoracionServicio {
    @Autowired
    valoracionRepositorio vRepositorio;
    @Autowired
    usuarioRepositorio uRepositorio;
    @Autowired
    barberoRepositorio bRepositorio;

    // CREAR VALORACION
    @Transactional
    public void crearValoracion(ValoracionDTO vDto) {
        valoracionEntidad newValoracion = ConversorDto_Entidad.convertirValoracion(vDto);
        vRepositorio.save(newValoracion);
    }

    // MODIFICAR VALORACION (ESTADO)
    @Transactional
    public void modificarEstado(ValoracionDTO vDto) {
        valoracionEntidad valoracion = vRepositorio.findById(vDto.getId()).orElse(null);
        valoracion.setEstado(vDto.getEstado());
        vRepositorio.save(valoracion);
    }

    // MODIFICAR VALORACION (COMENTARIO)
    @Transactional
    public void modificarComentario(ValoracionDTO vDto) {
        valoracionEntidad valoracion = vRepositorio.findById(vDto.getId()).orElse(null);
        valoracion.setComentario(vDto.getComentario());
        vRepositorio.save(valoracion);
    }

    // LISTAR VALORACIONES (USUARIO)
    @Transactional(readOnly = true)
    public List<ValoracionDTO> listarValoracionesxUsuario(UsuarioDTO uDto) {
        Optional<usuarioEntidad> usuario = uRepositorio.findById(uDto.getId());
        if (usuario.isPresent()) {
            List<valoracionEntidad> valoraciones = vRepositorio.listarValoracionesxUsuario(usuario.get());
            return valoraciones.stream().map(ConversorEntidad_Dto::convertirValoracion)
                    .collect(Collectors.toList());
        }

        return null;

    }

    // LISTAR VALORACIONES (BARBERO)
    @Transactional(readOnly = true)
    public List<ValoracionDTO> listarValoracionesxBarbero(BarberoDTO bDto) {
        Optional<barberoEntidad> barbero = bRepositorio.findById(bDto.getId());
        if (barbero.isPresent()) {
            List<valoracionEntidad> valoraciones = vRepositorio.listarValoracionesxBarbero(barbero.get());
            return valoraciones.stream().map(ConversorEntidad_Dto::convertirValoracion)
                    .collect(Collectors.toList());
        }

        return null;

    }

    // LISTAR VALORACIONES (PUNTAJE)
    @Transactional(readOnly = true)
    public List<ValoracionDTO> listarValoracionesxPuntaje(Double puntaje) {
        List<valoracionEntidad> valoraciones = vRepositorio.listarValoracionesxPuntaje(puntaje);
        return valoraciones.stream().map(ConversorEntidad_Dto::convertirValoracion)
                .collect(Collectors.toList());
    }

}
