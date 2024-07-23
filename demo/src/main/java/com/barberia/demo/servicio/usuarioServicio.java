package com.barberia.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import com.barberia.demo.repositorios.usuarioRepositorio;

@Service
public class usuarioServicio {
   @Autowired
   usuarioRepositorio uRepositorio;
   
   public void crearUsuario(UsuarioDTO usuarioDTO){

   }

}

