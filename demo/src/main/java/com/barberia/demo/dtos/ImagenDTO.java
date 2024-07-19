package com.barberia.demo.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ImagenDTO {
    private UUID id;
    private String mime;
    private String nombre;
    private byte[] contenido;
}
