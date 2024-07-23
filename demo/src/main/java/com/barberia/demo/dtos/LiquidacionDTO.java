package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class LiquidacionDTO {
    private UUID id;
    private Double total;

    private TurnoDTO turnoLiquidacion;

    private Date createdAt;
    private Date updatedAt;
}
