package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;

import lombok.Data;

@Data
public class BarberoDTO {
    private UUID id;
    private Boolean estado;
    private jefeEntidad jefeBarbero;
    private usuarioEntidad usuarioBarbero;

    private List<servicioEntidad> servicios;
    private List<turnoEntidad> turno;
    private valoracionEntidad valoracion;
    
    private Date createdAt;
    private Date updatedAt;

}
