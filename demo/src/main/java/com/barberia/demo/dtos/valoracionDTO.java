package com.barberia.demo.dtos;




import java.util.Date;
import java.util.UUID;

import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.turnoEntidad;

import lombok.Data;
@Data
public class valoracionDTO {
    private UUID id;

    private Double puntaje;

    private String comentarios;

    private Boolean estado;

     private barberoEntidad barberoValoracion;

     private turnoEntidad turnoValoracion;

     private Date createdAt;

     private Date updatedAt;


    
}
