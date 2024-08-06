package com.barberia.demo.servicio;


import com.barberia.demo.dtos.ServicioDTO;
import com.barberia.demo.entidades.*;
import com.barberia.demo.repositorios.*;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class servicioServicio {

    @Autowired
    servicioRepositorio sRepositorio;
    @Autowired
    usuarioRepositorio uRepositorio;
    @Autowired
    barberoRepositorio bRepositorio;
    @Autowired
    jefeRepositorio jRepositorio;
    @Autowired
    turnoRepositorio tRepositorio;


    //CREAR SERIVICO
    @Transactional
    public void crearServicio(ServicioDTO sDTO){
        sDTO.setEstado(true);
        servicioEntidad newServicio = ConversorDto_Entidad.convertirServicio(sDTO);

        if (sDTO.getUsuarioServicio()!= null){
            Optional<usuarioEntidad> usuario = uRepositorio.findById(sDTO.getUsuarioServicio().getId());
            if (usuario.isPresent()){
                usuarioEntidad usuarioServicio = usuario.get();
                newServicio.setUsuarioServicio(usuarioServicio);

            }
        }
        if (sDTO.getBarberoServicio() !=null){
            Optional<barberoEntidad> barbero = bRepositorio.findById(sDTO.getBarberoServicio().getId());
            barbero.ifPresent(newServicio::setBarberoServicio);
        }
        if (sDTO.getJefeServicio() !=null){
            Optional<jefeEntidad> jefe = jRepositorio.findById(sDTO.getJefeServicio().getId());
            jefe.ifPresent(newServicio::setJefeServicio);
        }
        if (sDTO.getTurnoServicio() !=null){
            Optional<turnoEntidad> jefe = tRepositorio.findById(sDTO.getTurnoServicio().getId());
            jefe.ifPresent(newServicio::setTurnoServicio);
        }

        sRepositorio.save(newServicio);
    }
    //MODIFICAR SERVICIO
    @Transactional
    public void modificarServicio(ServicioDTO sDTO){
        Optional<servicioEntidad> servicioOpt = sRepositorio.findById(sDTO.getId());
        if(servicioOpt.isPresent()){
            servicioEntidad servicio = servicioOpt.get();
            servicio.setEstado(sDTO.getEstado());

            sRepositorio.save(servicio);
        }
    }
    //LISTAR SERVICIOS
    @Transactional(readOnly = true)
    public List<ServicioDTO> listarServicios() {
        List<servicioEntidad> servicio = sRepositorio.findAll();
        return servicio.stream()
                .map(ConversorEntidad_Dto::convertirServicio)
                .collect(Collectors.toList());

    }
}
