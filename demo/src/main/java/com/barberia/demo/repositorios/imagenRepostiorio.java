package com.barberia.demo.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barberia.demo.entidades.ImagenEntidad;

@Repository
public interface imagenRepostiorio extends JpaRepository<ImagenEntidad, UUID>{

    
} 