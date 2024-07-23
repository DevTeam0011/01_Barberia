package com.barberia.demo.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.barberia.demo.enums.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
    private UUID id;
    private Rol rol;
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    private Boolean estado;

    private AdministadorDTO admin;
    private BarberoDTO barbero;
    private List<TurnoDTO> turnos;
    private List<ServicioDTO> servicios;
    private JefeDTO jefe;
    private ImagenDTO foto;

    private Date createdAt;
    private Date updatedAt;
}
