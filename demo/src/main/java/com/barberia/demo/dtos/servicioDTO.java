package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class ServicioDTO {
    private UUID id;
    private String nombre;
    private Double precio;
    private String duracion;
    private Boolean estado;

    private UsuarioDTO usuarioServicio;
    private BarberoDTO barberoServicio;
    private JefeDTO jefeServicio;
    private TurnoDTO turnoServicio;

    private Date createdAt;
    private Date updatedAt;





    
}
