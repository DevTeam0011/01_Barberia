package com.barberia.demo.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;

import org.springframework.stereotype.Repository;

@Repository
public interface turnoRepositorio extends JpaRepository<turnoEntidad, UUID> {

    // BUSCAR TURNOS (xUsuario)
    @Query("SELECT u FROM turnoEntidad u WHERE u.usuarioTurno = :usuario")
    public List<turnoEntidad> listarTurnosxUsuario(@Param("usuario") usuarioEntidad usuario);

    // BUSCAR TURNOS (xBarbero)
    @Query("SELECT u FROM turnoEntidad u WHERE u.turnoBarbero = :barbero")
    public List<turnoEntidad> listarTurnosxBarbero(@Param("barbero") barberoEntidad barbero);

   // BUSCAR TURNOS (xFecha)
   @Query("SELECT u FROM turnoEntidad u WHERE u.fechaTurno = :fecha")
   public List<turnoEntidad> listarTurnosxFecha(@Param("fecha") LocalDate fecha);

}
