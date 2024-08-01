package com.barberia.demo.entidades;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class administradorEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    // @Enumerated(EnumType.STRING)
    // private Rol rol;
    @Column(updatable = false)
    private Boolean estado;
    
    // Relaciones
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private usuarioEntidad usuarioAdmin;

    // Datos de creacion y ultima modificacion.
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    //------------------------METODOS--------------------------------------

    @PrePersist // Antes de crear
    protected void creacion() {
        this.createdAt = new Date(); // Se asignará la fecha actual
    }
    @PreUpdate // Antes de actualizar
    protected void actualizacion() {
        this.updatedAt = new Date(); // Se asignará la fecha actual
    }
}
