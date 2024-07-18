package com.barberia.demo.entidades;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
public class usuarioEntidad {
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
    private String telefono;

    // Estado de usuario.
    @Column(nullable = false)
    private Boolean estado;

    @OneToOne(mappedBy = "usuarioAdmin", fetch = FetchType.LAZY)
    private administradorEntidad admin;

    @OneToOne(mappedBy = "usuarioBarbero", fetch = FetchType.LAZY)
    private barberoEntidad barbero;

    @OneToMany(mappedBy = "usuarioTurno", fetch = FetchType.LAZY)
    private List<turnoEntidad> turnos;

    @OneToMany(mappedBy = "usuarioServicio", fetch = FetchType.LAZY)
    private List<servicioEntidad> servicios;

    @OneToOne(mappedBy = "usuarioJefe", fetch = FetchType.LAZY)
    private jefeEntidad jefe;

    @OneToOne
    private ImagenEntidad foto;

    // Datos de creacion y ultima modificacion de usuario.
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    //------------------------METODOS--------------------------------------
    @PrePersist // Before creating a user
    protected void onCreate() {
        this.createdAt = new Date(); // Default current_timestamp
    }

    @PreUpdate // before update
    protected void onUpdate() {
        this.updatedAt = new Date(); // default current_timestamp on update current_timestamp
    }

}
