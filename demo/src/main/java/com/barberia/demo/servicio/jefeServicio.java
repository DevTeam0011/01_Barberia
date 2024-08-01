package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.demo.dtos.JefeDTO;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import com.barberia.demo.repositorios.barberiaRepositorio;
import com.barberia.demo.repositorios.jefeRepositorio;
import com.barberia.demo.repositorios.servicioRepositorio;
import com.barberia.demo.repositorios.usuarioRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

import org.springframework.transaction.annotation.Transactional;

@Service
public class jefeServicio {

    @Autowired
    jefeRepositorio jRepositorio;
    @Autowired
    usuarioRepositorio uRepositorio;
    @Autowired
    barberiaRepositorio bRepositorio;
    @Autowired
    servicioRepositorio sRepositorio;

    // CREAR JEFE
    @Transactional
    public void crearJefe(JefeDTO jDTO) {
        jefeEntidad newJefe = ConversorDto_Entidad.convertirJefe(jDTO);
        // Se trae el usuario relacionado a jefe y se le cambia el rol.
        if (jDTO.getUsuarioJefe() != null) {
            Optional<usuarioEntidad> usuario = uRepositorio.findById(jDTO.getUsuarioJefe().getId());
            if (usuario.isPresent()) {
                usuarioEntidad usuarioJefe = usuario.get();
                usuarioJefe.setRol(Rol.JEFE);
                newJefe.setUsuarioJefe(usuarioJefe);
            }
        }

        jRepositorio.save(newJefe);
    }

    // MODIFICAR JEFE(ESTADO)
    @Transactional
    public void modificarJefe(JefeDTO jDTO) {
        Optional<jefeEntidad> jefeOpt = jRepositorio.findById(jDTO.getId());
        if (jefeOpt.isPresent()) {
            jefeEntidad jefe = jefeOpt.get();
            jefe.setEstado(jDTO.getEstado());
            jRepositorio.save(jefe);
        }
    }

    // LISTAR JEFES
    // @Transactional(readOnly = true)
    public List<JefeDTO> listarJefes() {
        List<jefeEntidad> jefes = jRepositorio.findAll();
        return jefes.stream()
                .map(ConversorEntidad_Dto::convertirJefe)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    // @Transactional(readOnly = true)
    public JefeDTO buscarPorId(UUID id) {
        Optional<jefeEntidad> jefe = jRepositorio.findById(id);
        return jefe.map(ConversorEntidad_Dto::convertirJefe).orElse(null);
    }
}