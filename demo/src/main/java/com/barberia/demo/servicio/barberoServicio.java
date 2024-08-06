package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberia.demo.dtos.BarberoDTO;
import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import com.barberia.demo.repositorios.barberoRepositorio;
import com.barberia.demo.repositorios.jefeRepositorio;
import com.barberia.demo.repositorios.usuarioRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@Service
public class barberoServicio {

    @Autowired
    barberoRepositorio bRepositorio;

    @Autowired
    usuarioRepositorio uRepositorio;

    @Autowired
    jefeRepositorio jRepositorio;

    // CREAR BARBERO
    @Transactional
    public void crearBarbero(BarberoDTO bDTO) {
        bDTO.setEstado(true);
        barberoEntidad newBarbero = ConversorDto_Entidad.convertirBarbero(bDTO);

        if (bDTO.getUsuarioBarbero() != null) {
            Optional<usuarioEntidad> usuario = uRepositorio.findById(bDTO.getUsuarioBarbero().getId());
            if (usuario.isPresent()) {
                usuarioEntidad usuarioBarbero = usuario.get();
                usuarioBarbero.setRol(Rol.BARBERO);
                newBarbero.setUsuarioBarbero(usuarioBarbero);
            }
        }
        if (bDTO.getJefeBarbero() != null) {
            Optional<jefeEntidad> jefe = jRepositorio.findById(bDTO.getJefeBarbero().getId());
            jefe.ifPresent(newBarbero::setJefeBarbero);
        }

        bRepositorio.save(newBarbero);
    }


   // MODIFICAR BARBERO(ESTADO)
    @Transactional
    public void modificarBarbero(BarberoDTO bDTO) {
        Optional<barberoEntidad> barberoOpt = bRepositorio.findById(bDTO.getId());
        if (barberoOpt.isPresent()) {
            barberoEntidad barbero = barberoOpt.get();
            barbero.setEstado(bDTO.getEstado());

            bRepositorio.save(barbero);
        }
    }


    // LISTAR BARBEROS
    @Transactional(readOnly = true)
    public List<BarberoDTO> listarBarberos() {
        List<barberoEntidad> barberos = bRepositorio.findAll();
        return barberos.stream()
                .map(ConversorEntidad_Dto::convertirBarbero)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Transactional(readOnly = true)
    public BarberoDTO buscarPorId(UUID id) {
        Optional<barberoEntidad> barbero = bRepositorio.findById(id);
        return barbero.map(ConversorEntidad_Dto::convertirBarbero).orElse(null);
    }
}
