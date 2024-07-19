package com.barberia.demo.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.liquidacionEntidad;
import com.barberia.demo.entidades.notificacionEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;

import lombok.Data;

@Data
public class turnoDTO {
    private UUID id;

    private LocalDate fechaTurno;

    private LocalTime horaTurno;

    private Boolean estado;

    private usuarioEntidad usuarioTurno;

    private barberoEntidad turnoBarbero;

    private notificacionEntidad notificacion;

    private liquidacionEntidad liquidacion;

    private servicioEntidad servicioTurno;

     private valoracionEntidad valoracion;

     private Date createdAt;

     private Date updatedAt;
    
}
