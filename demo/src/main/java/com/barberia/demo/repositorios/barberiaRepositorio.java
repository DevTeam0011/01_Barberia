package com.barberia.demo.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barberia.demo.entidades.barberiaEntidad;
import org.springframework.stereotype.Repository;

@Repository
public interface barberiaRepositorio extends JpaRepository<barberiaEntidad, UUID>{
    
    // LISTAR BARBERÍAS SEGÚN LOCALIDAD
    @Query("select b from barberiaEntidad b where b.localidad like %:localidad%")
    List<barberiaEntidad> listarBarberiasPorLocalidad(@Param("localidad") String localidad);

}
