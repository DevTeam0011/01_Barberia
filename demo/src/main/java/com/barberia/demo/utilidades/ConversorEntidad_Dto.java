package com.barberia.demo.utilidades;

import java.util.List;

import javax.imageio.ImageIO;

import com.barberia.demo.dtos.AdministadorDTO;
import com.barberia.demo.dtos.BarberiaDTO;
import com.barberia.demo.dtos.ImagenDTO;
import com.barberia.demo.dtos.JefeDTO;
import com.barberia.demo.dtos.LiquidacionDTO;
import com.barberia.demo.dtos.NotificacionDTO;
import com.barberia.demo.dtos.ServicioDTO;
import com.barberia.demo.dtos.TurnoDTO;
import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.dtos.ValoracionDTO;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.liquidacionEntidad;
import com.barberia.demo.entidades.notificacionEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;

import jakarta.persistence.criteria.CriteriaBuilder.In;

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

    // SERVICIO
    public static ServicioDTO convertirServicio(servicioEntidad sEntidad) {
        ServicioDTO instancia = new ServicioDTO();
        instancia.setId(sEntidad.getId());
        instancia.setNombre(sEntidad.getNombre());
        instancia.setPrecio(sEntidad.getPrecio());
        instancia.setDuracion(sEntidad.getDuracion());
        instancia.setEstado(sEntidad.getEstado());
        instancia.setCreatedAt(sEntidad.getCreatedAt());
        instancia.setUpdatedAt(sEntidad.getUpdatedAt());

        // RELACIONES
        if (sEntidad.getUsuarioServicio() != null) {
            // UsuarioDTO usuario = convertirUsuario(sEntidad.getUsuarioServicio);
            // instancia.setUsuarioServicio(usuario);
        }

        if (sEntidad.getBarberoServicio() != null) {
            // BarberiaDTO barbero = convertirBarbero(sEntidad.getBarberoServicio());
            // instancia.setBarberoServicio(barbero);
        }

        if (sEntidad.getJefeServicio() != null) {
            // JefeDTO jefe = convertirJefe(sEntidad.getJefeServicio());
            // instancia.getJefeServicio(jefe);
        }

        if (sEntidad.getTurnoServicio() != null) {
            // TurnoDTO turno = convertirTurno(sEntidad.getTurnoServicio());
            // instancia.getTurnoServicio(turno);
        }

        return instancia;
    }

    // TURNO
    public static TurnoDTO convertirTurno(turnoEntidad tEntidad) {
        TurnoDTO instancia = new TurnoDTO();
        instancia.setId(tEntidad.getId());
        instancia.setFechaTurno(tEntidad.getFechaTurno());
        instancia.setHoraTurno(tEntidad.getHoraTurno());
        instancia.setEstado(tEntidad.getEstado());
        instancia.setCreatedAt(tEntidad.getCreatedAt());
        instancia.setUpdatedAt(tEntidad.getUpdatedAt());

        // RELACIONES
        if (tEntidad.getUsuarioTurno() != null) {
            // UsuarioDTO usuario = convertirUsuario(tEntidad.getUsuarioTurno());
            // instancia.setUsuarioTurno(usuario);
        }

        if (tEntidad.getTurnoBarbero() != null) {
            // TurnoDTO turno = convertirTurno(tEntidad.getTurnoBarbero());
            // instancia.setTurnoBarbero(turno);
        }

        if (tEntidad.getNotificacion() != null) {
            // NotificacionDTO notificacion =
            // convertirNotificacion(tEntidad.getNotificacion());
            // instancia.setNotificacion(notificacion);
        }

        if (tEntidad.getLiquidacion() != null) {
            // LiquidacionDTO liquidacion = convertirLiquidacion(tEntidad.getLiquidacion());
            // instancia.setLiquidacion(liquidacion);
        }

        if (tEntidad.getServicioTurno() != null) {
            // ServicioDTO servicio = convertirTurno(tEntidad.getServicioTurno());
            // instancia.setServicioTurno(servicio);
        }

        if (tEntidad.getValoracion() != null) {
            // ValoracionDTO valoracion = convertirValoracion(tEntidad.getValoracion());
            // instancia.setValoracion(valoracion);
        }

        return instancia;

    }

    // LIQUIDACION
    public static LiquidacionDTO convertirLiquidacion(liquidacionEntidad lEntidad) {
        LiquidacionDTO instancia = new LiquidacionDTO();
        instancia.setId(lEntidad.getId());
        instancia.setTotal(lEntidad.getTotal());
        instancia.setCreatedAt(lEntidad.getCreatedAt());
        instancia.setUpdatedAt(lEntidad.getUpdatedAt());

        // RELACIONES
        if (lEntidad.getTurnoLiquidacion() != null) {
            // TurnoDTO turno = convertirTurno(lEntidad.getTurnoLiquidacion());
            // instancia.setTurnoLiquidacion(turno);
        }

        return instancia;

    }

    // NOTIFICACION
    public static NotificacionDTO convertirNotificacion(notificacionEntidad nEntidad) {
        NotificacionDTO instancia = new NotificacionDTO();
        instancia.setId(nEntidad.getId());
        instancia.setEstado(nEntidad.getEstado());
        instancia.setCreatedAt(nEntidad.getCreatedAt());
        instancia.setUpdatedAt(nEntidad.getUpdatedAt());

        // RELACIONES
        if (nEntidad.getTurnoNotificacion() != null) {
            // TurnoDTO turno = convertirTurno(nEntidad.getTurnoNotificacion());
            // instancia.setTurnoNotificacion(turno);
        }

        return instancia;

    }

    // IMAGEN
    public static ImagenDTO convertirImagen(ImagenEntidad iEntidad) {
        ImagenDTO instancia = new ImagenDTO();
        instancia.setId(iEntidad.getId());
        instancia.setMime(iEntidad.getMime());
        instancia.setNombre(iEntidad.getNombre());
        instancia.setContenido(iEntidad.getContenido());

        return instancia;
    }

    // JEFE
    public static JefeDTO convertirJefe(jefeEntidad jEntidad) {
        JefeDTO instancia = new JefeDTO();
        instancia.setId(jEntidad.getId());
        instancia.setRol(jEntidad.getRol());
        instancia.setEstado(jEntidad.getEstado());
        instancia.setCreatedAt(jEntidad.getCreatedAt());
        instancia.setUpdatedAt(jEntidad.getUpdatedAt());

        // RELACIONES
        if (jEntidad.getUsuarioJefe() != null) {
            // UsuarioDTO usuario = convertirUsuario(jEntidad.getUsuarioJefe());
            // instancia.setUsuarioJefe(usuario);
        }

        if (jEntidad.getBarberia() != null) {
            // BarberiaDTO barberia = convertirBarberia(jEntidad.getBarberia());
            // instancia.setBarberia(barberia);
        }

        if (jEntidad.getBarberos() != null) {
            // List<BarberoDTO> barberos = jEntidad.getBarberos().stream().map(
            // (barbero) -> convertirServicio(barbero)
            // ).toList();
            // instancia.setBarberos(barberos);
        }

        if (jEntidad.getServicioJefe() != null) {
            // ServicioDTO servicio = convertirServicio(jEntidad.getServicioJefe());
            // instancia.setServicioJefe(servicio);
        }

        return instancia;

    }

}
