package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;

import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;

import lombok.Data;

@Data
public class servicioDTO {
    private UUID id;

    private String nombre;

    private Double precio;

    private String duracion;

    private Boolean estado;

    private usuarioEntidad usuarioServicio;

     private barberoEntidad barberoServicio;

     private jefeEntidad jefeServicio;

     private turnoEntidad turnoServicio;

     private Date createdAt;

     private Date updatedAt;





    
}
