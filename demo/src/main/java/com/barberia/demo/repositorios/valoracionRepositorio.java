package com.barberia.demo.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;
import org.springframework.stereotype.Repository;

@Repository
public interface valoracionRepositorio extends JpaRepository<valoracionEntidad, UUID> {
    // BUSCAR TURNOS (xUsuario)
    @Query("SELECT u FROM valoracionEntidad u WHERE u.turnoValoracion.usuarioTurno = :usuario")
    public List<valoracionEntidad> listarValoracionesxUsuario(@Param("usuario") usuarioEntidad usuario);

    // BUSCAR TURNOS (xBarbero)
    @Query("SELECT u FROM valoracionEntidad u WHERE u.barberoValoracion = :barbero")
    public List<valoracionEntidad> listarValoracionesxBarbero(@Param("barbero") barberoEntidad barbero);

    // BUSCAR VALORACIONES POR PUNTAJE
    @Query("SELECT v FROM valoracionEntidad v WHERE v.puntaje = :puntaje ORDER BY v.puntaje DESC")
    public List<valoracionEntidad> listarValoracionesxPuntaje(@Param("puntaje") Double puntaje);

}
