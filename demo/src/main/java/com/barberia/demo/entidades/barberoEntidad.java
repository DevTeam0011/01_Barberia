package com.barberia.demo.entidades;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.barberia.demo.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class barberoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    // ROL
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena;
    
    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jefe_id")
    private jefeEntidad jefeBarbero;

    @OneToMany(mappedBy = "barberoServicio", fetch = FetchType.LAZY)
    private List<servicioEntidad> servicios;

    @OneToMany(mappedBy = "turnoBarbero", fetch = FetchType.LAZY)
    private List<turnoEntidad> turno;

    @OneToOne(mappedBy = "barberoValoracion", fetch = FetchType.LAZY)
    private valoracionEntidad valoracion;



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