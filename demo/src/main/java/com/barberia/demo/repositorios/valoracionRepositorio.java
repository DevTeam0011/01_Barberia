package com.barberia.demo.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberia.demo.entidades.valoracionEntidad;
import org.springframework.stereotype.Repository;

@Repository
public interface valoracionRepositorio extends JpaRepository<UUID,valoracionEntidad> {
    
}
