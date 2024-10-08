package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class AdministradorDTO {
    private UUID id;
    private Boolean estado;
    private Date createdAt;
    private Date updatedAt;

    private UsuarioDTO usuarioAdmin;
}
