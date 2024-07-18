package com.barberia.demo.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberia.demo.entidades.usuarioEntidad;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepositorio extends JpaRepository<usuarioEntidad,UUID> {
    
}
