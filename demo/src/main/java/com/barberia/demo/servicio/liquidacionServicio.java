package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.demo.dtos.LiquidacionDTO;
import com.barberia.demo.entidades.liquidacionEntidad;
import com.barberia.demo.repositorios.liquidacionRepositorio;
import com.barberia.demo.repositorios.turnoRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;
import org.springframework.transaction.annotation.Transactional;



@Service

public class liquidacionServicio {

    @Autowired
    liquidacionRepositorio lRepositorio;
    @Autowired
    turnoRepositorio tRepositorio;

    // CREAR Liquidcion
    @Transactional
    public void crearLiquidacion(LiquidacionDTO lDTO){
        liquidacionEntidad newLiquidacionEntidad = ConversorDto_Entidad.convertirLiquidacion(lDTO);
        lRepositorio.save(newLiquidacionEntidad);

    }

    // MODIFICAR Liquidacion (FECHA / HORA)

    @Transactional
    public void modificarLiquidacion(LiquidacionDTO lDto){
        //liquidacionEntidad lEntidad = lRepositorio.findById(lDto.getId()).orElse(null);
        Optional<liquidacionEntidad>liquidacionOpt = lRepositorio.findById(lDto.getId());

        if(liquidacionOpt.isPresent()){
            liquidacionEntidad lEntidad = liquidacionOpt.get();
            lEntidad  = ConversorDto_Entidad.convertirLiquidacion(lDto);


            lRepositorio.save(lEntidad);

        }

    }

    // LISTAR TODAS LAS LIQUIDACIONES
    @Transactional(readOnly = true)
    public List<LiquidacionDTO> listarLiquidacion(){

        List<liquidacionEntidad> listaLiquidacion = lRepositorio.findAll();
        return listaLiquidacion.stream().map(ConversorEntidad_Dto::convertirLiquidacion).toList();
        
    }

     @Transactional(readOnly = true)
    public LiquidacionDTO buscarPorId(UUID id) {

        Optional<liquidacionEntidad> liquidacionOptional = lRepositorio.findById(id);

        return liquidacionOptional.map(ConversorEntidad_Dto::convertirLiquidacion).orElse(null);
    }
    
}
