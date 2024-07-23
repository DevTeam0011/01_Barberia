package com.barberia.demo.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class TurnoDTO {
    private UUID id;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    private Boolean estado;

    private UsuarioDTO usuarioTurno;
    private BarberoDTO turnoBarbero;
    private NotificacionDTO notificacion;
    private LiquidacionDTO liquidacion;
    private ServicioDTO servicioTurno;
    private ValoracionDTO valoracion;

    private Date createdAt;
 private Date updatedAt;
    
}
