package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberia.demo.dtos.BarberiaDTO;
import com.barberia.demo.entidades.barberiaEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.repositorios.barberiaRepositorio;
import com.barberia.demo.repositorios.jefeRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@Service
public class barberiaServicio {
    @Autowired 
    barberiaRepositorio bRepositorio;

    @Autowired
    jefeRepositorio jRepositorio;

    // CREAR BARBERÍA
    @Transactional
    public void crearBarberia(BarberiaDTO bDto) {
        bDto.setEstado(true);
        barberiaEntidad newBarberia = ConversorDto_Entidad.convertirBarberia(bDto);

        if (bDto.getJefe() != null) {
            Optional<jefeEntidad> jefe = jRepositorio.findById(bDto.getJefe().getId());

            if (jefe.isPresent()) {
                jefeEntidad jefeEntidad = jefe.get();
                newBarberia.setJefe(jefeEntidad);
            }
        }
        // imagen

        bRepositorio.save(newBarberia);
    }

    // MODIFICAR BARBERÍA (ESTADO)
    @Transactional
    public void modificarBarberiaEstado(BarberiaDTO bDTO) {
        Optional<barberiaEntidad> barberiaOpt = bRepositorio.findById(bDTO.getId());

        if (barberiaOpt.isPresent()) {
            barberiaEntidad barberia = barberiaOpt.get();
            barberia.setEstado(bDTO.getEstado());

            bRepositorio.save(barberia);
        }
    }

    // MODIFICAR BARBERÍA
    @Transactional
    public void modificarBarberia(BarberiaDTO bDTO) {
        Optional<barberiaEntidad> barberiaOpt = bRepositorio.findById(bDTO.getId());

        if (barberiaOpt.isPresent()) {
            barberiaEntidad barberiaEntidad = barberiaOpt.get();
            barberiaEntidad = ConversorDto_Entidad.convertirBarberia(bDTO);

            bRepositorio.save(barberiaEntidad);
        }
    }

    // LISTAR TODAS LAS BARBERÍAS
    @Transactional(readOnly = true)
    public List<BarberiaDTO> listarBarberias() {
        List<barberiaEntidad> lista = bRepositorio.findAll();

        return lista.stream().map(ConversorEntidad_Dto::convertirBarberia).toList();
    }

    // LISTAR BARBERÍAS POR LOCALIDAD
    @Transactional(readOnly = true)
    public List<BarberiaDTO> listarBarberiasPorLocalidad(String localidad) {
        List<barberiaEntidad> lista = bRepositorio.listarBarberiasPorLocalidad(localidad);

        return lista.stream().map(ConversorEntidad_Dto::convertirBarberia).toList();
    }

    // BUSCAR POR ID
    @Transactional(readOnly = true)
    public BarberiaDTO buscarPorId(UUID id) {
        Optional<barberiaEntidad> barOptional = bRepositorio.findById(id);

        return barOptional.map(ConversorEntidad_Dto::convertirBarberia).orElse(null);
    }
}
