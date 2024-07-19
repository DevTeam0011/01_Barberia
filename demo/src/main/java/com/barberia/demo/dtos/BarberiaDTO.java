package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.UUID;
import com.barberia.demo.entidades.ImagenEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import lombok.Data;

@Data
public class BarberiaDTO {
private UUID id;
private String nombre;
private String ubicacion;
private String horario;
private Boolean estado;

private jefeEntidad jefe;
private ImagenEntidad foto;

private Date createdAt;
private Date updatedAt;
}
