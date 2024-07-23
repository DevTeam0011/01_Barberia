package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import com.barberia.demo.enums.Rol;
import lombok.Data;

@Data
public class JefeDTO {
    private UUID id;
    private Rol rol;
    private Boolean estado;

    private UsuarioDTO usuarioJefe;
    private BarberiaDTO barberia;
    private List<BarberiaDTO> barberos;
    private ServicioDTO servicioJefe;

    private Date createdAt;
    private Date updatedAt;
}
