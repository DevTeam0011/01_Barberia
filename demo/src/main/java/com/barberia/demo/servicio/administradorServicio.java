package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barberia.demo.dtos.AdministradorDTO;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import com.barberia.demo.repositorios.administradorRepositorio;
import com.barberia.demo.repositorios.usuarioRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class administradorServicio {
    @Autowired
    administradorRepositorio aRepositorio;
    @Autowired
    usuarioRepositorio uRepositorio;

    // CREAR ADMINISTRADOR
    @Transactional
    public void crearAdministrador(AdministradorDTO aDTO) {
        administradorEntidad newAdmin = ConversorDto_Entidad.convertirAdmin(aDTO);

        if (aDTO.getUsuarioAdmin().getId() != null) {
            Optional<usuarioEntidad> usuario = uRepositorio.findById(aDTO.getUsuarioAdmin().getId());
            // usuario.ifPresent(newAdmin::setUsuarioAdmin);

            if (usuario.isPresent()) {
                usuarioEntidad usuarioAdmin = usuario.get();
                usuarioAdmin.setRol(Rol.ADMIN);
                newAdmin.setUsuarioAdmin(usuarioAdmin);
            }
        }

        aRepositorio.save(newAdmin);
    }

    // MODIFICAR ADMINISTRADOR(ESTADO)
    @Transactional
    public void modificarAdministrador(AdministradorDTO aDTO) {
        Optional<administradorEntidad> adminOpt = aRepositorio.findById(aDTO.getId());
        if (adminOpt.isPresent()) {
            administradorEntidad admin = adminOpt.get();
            admin.setEstado(aDTO.getEstado());

            aRepositorio.save(admin);
        }
    }

    // LISTAR ADMINISTRADORES
    // @Transactional(readOnly = true)
    public List<AdministradorDTO> listarAdministradores() {
        List<administradorEntidad> administradores = aRepositorio.findAll();
        return administradores.stream()
                .map(ConversorEntidad_Dto::convertirAdmin)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    // @Transactional(readOnly = true)
    public AdministradorDTO buscarPorId(UUID id) {
        Optional<administradorEntidad> admin = aRepositorio.findById(id);
        return admin.map(ConversorEntidad_Dto::convertirAdmin).orElse(null);
    }

}
