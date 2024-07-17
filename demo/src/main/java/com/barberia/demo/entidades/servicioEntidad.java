package com.barberia.demo.entidades;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class servicioEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private String duracion;
    
    // Estado de servicio.
    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private usuarioEntidad usuarioServicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbero_id")
    private barberoEntidad barberoServicio;

    @OneToOne(mappedBy = "servicioJefe",fetch = FetchType.LAZY)
    private jefeEntidad jefeServicio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turno_id")
    private turnoEntidad turnoServicio;



    // Datos de creacion y ultima modificacion.
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist // Before creating a user
    protected void onCreate() {
        this.createdAt = new Date(); // Default current_timestamp
    }

    @PreUpdate // before update
    protected void onUpdate() {
        this.updatedAt = new Date(); // default current_timestamp on update current_timestamp
    }

}
