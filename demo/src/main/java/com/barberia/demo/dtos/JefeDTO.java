package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.barberia.demo.entidades.barberiaEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import lombok.Data;

@Data
public class JefeDTO {
    private UUID id;
    private Rol rol;
    private Boolean estado;

    private usuarioEntidad usuarioJefe;
    private barberiaEntidad barberia;
    private List<barberiaEntidad> barberos;
    private servicioEntidad servicioJefe;

    private Date createdAt;
    private Date updatedAt;

}
