package com.barberia.demo.entidades;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class turnoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    // Fecha y hora del turno en variables separadas.
    @Column(nullable = false)
    private LocalDate fechaTurno;
    @Column(nullable = false)
    private LocalTime horaTurno;
    // Estado del turno
    @Column(nullable = false)
    private Boolean estado;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private usuarioEntidad usuarioTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbero_id")
    private barberoEntidad turnoBarbero;

    @OneToOne(mappedBy = "turnoNotificacion", fetch = FetchType.LAZY)
    private notificacionEntidad notificacion;

    @OneToOne(mappedBy = "turnoLiquidacion", fetch = FetchType.LAZY)
    private liquidacionEntidad liquidacion;

    @OneToOne(mappedBy = "turnoServicio",fetch = FetchType.LAZY)
    private servicioEntidad servicioTurno;

    @OneToOne(mappedBy = "turnoValoracion", fetch = FetchType.LAZY)
    private valoracionEntidad valoracion;

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
