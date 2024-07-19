package com.barberia.demo.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.barberia.demo.entidades.ImagenEntidad;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.enums.Rol;

import lombok.Data;
@Data
public class usuarioDTO {
    private UUID id;

    private Rol rol;

    private String nombre;

    private String email;

    private String contrasena;

    private String telefono;

    private Boolean estado;

     private administradorEntidad admin;

     private barberoEntidad barbero;

     private List<turnoEntidad> turnos;

     private List<servicioEntidad> servicios;

     private jefeEntidad jefe;

     private ImagenEntidad foto;

     private Date createdAt;

     private Date updatedAt;
    
}
