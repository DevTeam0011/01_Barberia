package com.barberia.demo.dtos;

import java.util.Date;
import java.util.UUID;

import com.barberia.demo.entidades.turnoEntidad;

import lombok.Data;

@Data
public class LiquidacionDTO {
    private UUID id;
    private Double total;

    private turnoEntidad turnoLiquidacion;

    private Date createdAt;
    private Date updatedAt;
}
