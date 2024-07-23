package com.barberia.demo.dtos;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class BarberoDTO {
    private UUID id;
    private Boolean estado;
    private JefeDTO jefeBarbero;
    private UsuarioDTO usuarioBarbero;

    private List<ServicioDTO> servicios;
    private List<TurnoDTO> turno;
    private ValoracionDTO valoracion;
    
    private Date createdAt;
    private Date updatedAt;
}
