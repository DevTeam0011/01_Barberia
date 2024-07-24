package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class ValoracionDTO {
    private UUID id;
    private Double puntaje;
    private String comentario;
    private Boolean estado;

    private BarberoDTO barberoValoracion;
    private TurnoDTO turnoValoracion;

    private Date createdAt;
    private Date updatedAt;
}
