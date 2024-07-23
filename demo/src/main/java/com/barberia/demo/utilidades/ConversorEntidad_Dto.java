package com.barberia.demo.utilidades;

import java.util.List;

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
import com.barberia.demo.entidades.ImagenEntidad;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.liquidacionEntidad;
import com.barberia.demo.entidades.notificacionEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.turnoEntidad;

import com.barberia.demo.dtos.BarberoDTO;
import com.barberia.demo.entidades.barberiaEntidad;
import com.barberia.demo.entidades.barberoEntidad;

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
    
    public static ValoracionDTO convertirValoracion(valoracionEntidad vEntidad) {
        ValoracionDTO instancia = new ValoracionDTO();
        instancia.setId(vEntidad.getId());
        instancia.setPuntaje(vEntidad.getPuntaje());
        instancia.setComentarios(vEntidad.getComentario());
        instancia.setEstado(vEntidad.getEstado());
        instancia.setCreatedAt(vEntidad.getCreatedAt());
        instancia.setUpdatedAt(vEntidad.getUpdatedAt());

        if(vEntidad.getBarberoValoracion() !=null){
            BarberoDTO usuario = convertirBarbero(vEntidad.getBarberoValoracion());
            instancia.setBarberoValoracion(usuario);
        }

        if (vEntidad.getTurnoValoracion() != null) {
            TurnoDTO turno = convertirTurno(vEntidad.getTurnoValoracion());
            instancia.setTurnoValoracion(turno);
        }

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
            BarberoDTO barbero = convertirBarbero(sEntidad.getBarberoServicio());
            instancia.setBarberoServicio(barbero);
        }

        if (sEntidad.getJefeServicio() != null) {
            JefeDTO jefe = convertirJefe(sEntidad.getJefeServicio());
            instancia.setJefeServicio(jefe);
        }

        if (sEntidad.getTurnoServicio() != null) {
            TurnoDTO turno = convertirTurno(sEntidad.getTurnoServicio());
            instancia.setTurnoServicio(turno);
        }
        return instancia;
    }

    public static BarberiaDTO convertirBarberia(barberiaEntidad bEntidad) {
        BarberiaDTO instancia = new BarberiaDTO();
        instancia.setId(bEntidad.getId());
        instancia.setNombre(bEntidad.getNombre());
        instancia.setUbicacion(bEntidad.getUbicacion());
        instancia.setHorario(bEntidad.getHorario());
        instancia.setEstado(bEntidad.getEstado());

        if (bEntidad.getJefe() != null) {
            JefeDTO jefe = convertirJefe(bEntidad.getJefe());
            instancia.setJefe(jefe);
        }

        if (bEntidad.getFoto() != null) {
            ImagenDTO imagen = convertirImagen(bEntidad.getFoto());
            instancia.setFoto(imagen);
        }

        instancia.setCreatedAt(bEntidad.getCreatedAt());
        instancia.setUpdatedAt(bEntidad.getUpdatedAt());

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
            UsuarioDTO usuario = convertirUsuario(tEntidad.getUsuarioTurno());
            instancia.setUsuarioTurno(usuario);
        }

        if (tEntidad.getTurnoBarbero() != null) {
            BarberoDTO turno = convertirBarbero(tEntidad.getTurnoBarbero());
            instancia.setTurnoBarbero(turno);
        }

        if (tEntidad.getNotificacion() != null) {
            NotificacionDTO notificacion = convertirNotificacion(tEntidad.getNotificacion());
            instancia.setNotificacion(notificacion);
        }

        if (tEntidad.getLiquidacion() != null) {
            LiquidacionDTO liquidacion = convertirLiquidacion(tEntidad.getLiquidacion());
            instancia.setLiquidacion(liquidacion);
        }

        if (tEntidad.getServicioTurno() != null) {
            ServicioDTO servicio = convertirServicio(tEntidad.getServicioTurno());
            instancia.setServicioTurno(servicio);
        }

        if (tEntidad.getValoracion() != null) {
            ValoracionDTO valoracion = convertirValoracion(tEntidad.getValoracion());
            instancia.setValoracion(valoracion);
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
            TurnoDTO turno = convertirTurno(lEntidad.getTurnoLiquidacion());
            instancia.setTurnoLiquidacion(turno);
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
            TurnoDTO turno = convertirTurno(nEntidad.getTurnoNotificacion());
            instancia.setTurnoNotificacion(turno);
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
            UsuarioDTO usuario = convertirUsuario(jEntidad.getUsuarioJefe());
            instancia.setUsuarioJefe(usuario);
        }

        if (jEntidad.getBarberia() != null) {
            BarberiaDTO barberia = convertirBarberia(jEntidad.getBarberia());
            instancia.setBarberia(barberia);
        }

        if (jEntidad.getBarberos() != null) {
            List<BarberoDTO> barberos = jEntidad.getBarberos().stream().map(
                (barbero) -> convertirBarbero(barbero)
            ).toList();
            instancia.setBarberos(barberos);
        }

        if (jEntidad.getServicioJefe() != null) {
            ServicioDTO servicio = convertirServicio(jEntidad.getServicioJefe());
            instancia.setServicioJefe(servicio);
        }

        return instancia;

    }

    public static BarberoDTO convertirBarbero(barberoEntidad bEntidad) {
        BarberoDTO instancia = new BarberoDTO();
        instancia.setId(bEntidad.getId());
        instancia.setEstado(bEntidad.getEstado());
        
        if (bEntidad.getJefeBarbero() != null) {
            JefeDTO jefe = convertirJefe(bEntidad.getJefeBarbero());
            instancia.setJefeBarbero(jefe);
        }

        if (bEntidad.getUsuarioBarbero() != null) {
            UsuarioDTO usuario = convertirUsuario(bEntidad.getUsuarioBarbero());
            instancia.setUsuarioBarbero(usuario);
        }

        if (bEntidad.getValoracion() != null) {
            ValoracionDTO valoracion = convertirValoracion(bEntidad.getValoracion());
            instancia.setValoracion(valoracion);
        }

        if (bEntidad.getServicios() != null) {
            List<ServicioDTO> servicios = bEntidad.getServicios().stream().map(
                (servicio) -> convertirServicio(servicio)
            ).toList();
            instancia.setServicios(servicios);
        }

        if (bEntidad.getTurnos() != null) {
            List<ServicioDTO> servicios = bEntidad.getServicios().stream().map(
                (servicio) -> convertirServicio(servicio)
            ).toList();
            instancia.setServicios(servicios);
        }

        return instancia;
    }
}
