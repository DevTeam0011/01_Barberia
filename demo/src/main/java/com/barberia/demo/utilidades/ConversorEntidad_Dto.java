package com.barberia.demo.utilidades;

import com.barberia.demo.dtos.AdministadorDTO;
import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.dtos.ValoracionDTO;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;

public class ConversorEntidad_Dto {
    public static AdministadorDTO convertirAdmin(administradorEntidad aEntidad) {
        AdministadorDTO instancia = new AdministadorDTO();
        instancia.setId(aEntidad.getId());
        instancia.setRol(aEntidad.getRol());
        instancia.setEstado(aEntidad.getEstado());
        instancia.setCreatedAt(aEntidad.getCreatedAt());
        instancia.setUpdatedAt(aEntidad.getUpdatedAt());

        if (aEntidad.getUsuarioAdmin() != null) {
            UsuarioDTO usuario = convertirUsuario(aEntidad.getUsuarioAdmin());
            instancia.setUsuarioAdmin(usuario);
        }

        return instancia;
    }

    public static UsuarioDTO convertirUsuario(usuarioEntidad uEntidad) {
        return new UsuarioDTO();


    } 
    
    public static ValoracionDTO convertirValoracion(valoracionEntidad vEntidad){
        ValoracionDTO instancia = new ValoracionDTO();
        instancia.setId(vEntidad.getId());
        instancia.setPuntaje(vEntidad.getPuntaje());
        instancia.setComentarios(vEntidad.getComentario());
        instancia.setEstado(vEntidad.getEstado());
        instancia.setCreatedAt(vEntidad.getCreatedAt());
        instancia.setUpdatedAt(vEntidad.getUpdatedAt());

       /*  if(vEntidad.getBarberoValoracion() !=null){
        BarberoDTO usuario = convertirBarbero(vEntidad.getBarberoValoracion());
        instancia.setBarberoValoracion(usuario);
        }*/
        return instancia;


    }
}
