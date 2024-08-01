package com.barberia.demo.servicio;

import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.repositorios.usuarioRepositorio;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class usuarioServicio {
   @Autowired
   usuarioRepositorio uRepositorio;

   // CREAR USUARIO
   @Transactional
   public void crearUsuario(UsuarioDTO uDTO) {
      usuarioEntidad newUsuario = ConversorDto_Entidad.convertirUsuario(uDTO);
      uRepositorio.save(newUsuario);
   }

   // MODIFICAR USUARIO
   @Transactional
   public void modificarUsuario(UsuarioDTO uDTO) {
      Optional<usuarioEntidad> usuario = uRepositorio.findById(uDTO.getId());
      if (usuario.isPresent()) {
         usuarioEntidad newUsuario = usuario.get();
         newUsuario = ConversorDto_Entidad.convertirUsuario(uDTO);
         uRepositorio.save(newUsuario);
      }
   }

   // ELIMINAR(DAR DE BAJA)
   @Transactional
   public void eliminarUsuario(UUID id) {
      Optional<usuarioEntidad> usuario = uRepositorio.findById(id);
      if (usuario.isPresent()) {
         usuarioEntidad newUsuario = usuario.get();
         newUsuario.setEstado(false);
         uRepositorio.save(newUsuario);
      }
   }

   // LISTAR USUARIOS
   @Transactional(readOnly = true)
   public List<UsuarioDTO> listarUsuarios() {
      List<usuarioEntidad> usuarios = uRepositorio.findAll();
      return usuarios.stream().map(
            usuario -> ConversorEntidad_Dto.convertirUsuario(usuario)).toList();
   }

   // BUSCAR POR EMAIL
   public UsuarioDTO buscarPorEmail(String email) {
      return ConversorEntidad_Dto.convertirUsuario(uRepositorio.buscarPorEmail(email).get());
   }

   //Busqueda por roles
   
}
