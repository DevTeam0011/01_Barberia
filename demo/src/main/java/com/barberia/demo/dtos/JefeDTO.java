package com.barberia.demo.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class JefeDTO {
    private UUID id;
    private Boolean estado;

    private UsuarioDTO usuarioJefe;
    private BarberiaDTO barberia;
    private List<BarberoDTO> barberos;
    private ServicioDTO servicioJefe;

    private Date createdAt;
    private Date updatedAt;
}
