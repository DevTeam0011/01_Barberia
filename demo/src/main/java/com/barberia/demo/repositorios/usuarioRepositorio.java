package com.barberia.demo.repositorios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberia.demo.entidades.usuarioEntidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepositorio extends JpaRepository<usuarioEntidad, UUID> {
    //busqueda por email
    @Query("SELECT u FROM usuarioEntidad u WHERE u.email = :email")
    public Optional<usuarioEntidad> buscarPorEmail(@Param("email") String email);
    
}
