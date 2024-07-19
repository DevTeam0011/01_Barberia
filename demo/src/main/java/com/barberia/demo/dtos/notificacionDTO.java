package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;

import com.barberia.demo.entidades.turnoEntidad;

import lombok.Data;

@Data
public class notificacionDTO {
    private UUID id;

    private Boolean estado;

     private turnoEntidad turnoNotificacion;

     private Date createdAt;

     private Date updatedAt;
    
}
