package com.barberia.demo.entidades;

import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenEntidad {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String mime;
  private String nombre;
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "LONGBLOB")
  private byte[] contenido;
}
