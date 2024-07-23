package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class BarberiaDTO {
    private UUID id;
    private String nombre;
    private String ubicacion;
    private String horario;
    private Boolean estado;

    private JefeDTO jefe;
    private ImagenDTO foto;

    private Date createdAt;
    private Date updatedAt;
}
