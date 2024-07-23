package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class NotificacionDTO {
    private UUID id;
    private Boolean estado;

    private TurnoDTO turnoNotificacion;

    private Date createdAt;
    private Date updatedAt;
}
