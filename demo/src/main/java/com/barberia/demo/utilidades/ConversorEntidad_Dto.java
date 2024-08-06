package com.barberia.demo.utilidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barberia.demo.dtos.AdministradorDTO;
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
    private static enum entidades {
        admin, barberia, barbero, imagen, jefe, liquidacion, notificacion, servicio, turno, usuario, valoracion
    }
    private static Map<entidades, Object> DTOsTemporales = new HashMap<>();

    // ADMIN
    public static AdministradorDTO convertirAdmin(administradorEntidad aEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.admin)) 
            return (AdministradorDTO) DTOsTemporales.get(entidades.admin);

        AdministradorDTO instancia = new AdministradorDTO();
        instancia.setId(aEntidad.getId());
        // instancia.setRol(aEntidad.getRol());
        instancia.setEstado(aEntidad.getEstado());

        instancia.setCreatedAt(aEntidad.getCreatedAt());
        instancia.setUpdatedAt(aEntidad.getUpdatedAt());
        
        DTOsTemporales.put(entidades.admin, instancia);

        if (aEntidad.getUsuarioAdmin() != null) {
            UsuarioDTO usuario = convertirUsuario(aEntidad.getUsuarioAdmin());
            instancia.setUsuarioAdmin(usuario);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // USUARIO
    public static UsuarioDTO convertirUsuario(usuarioEntidad uEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.usuario)) 
            return (UsuarioDTO) DTOsTemporales.get(entidades.usuario);
        
        UsuarioDTO instancia = new UsuarioDTO();
        instancia.setId(uEntidad.getId());
        instancia.setRol(uEntidad.getRol());
        instancia.setNombre(uEntidad.getNombre());
        instancia.setEmail(uEntidad.getEmail());
        instancia.setContrasena(uEntidad.getContrasena());
        instancia.setTelefono(uEntidad.getTelefono());
        instancia.setEstado(uEntidad.getEstado());

        instancia.setCreatedAt(uEntidad.getCreatedAt());
        instancia.setUpdatedAt(uEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.usuario, instancia);

        if (uEntidad.getAdmin() != null) {
            AdministradorDTO admin = convertirAdmin(uEntidad.getAdmin());
            instancia.setAdmin(admin);
        }
        if (uEntidad.getBarbero() != null) {
            BarberoDTO barbero = convertirBarbero(uEntidad.getBarbero());
            instancia.setBarbero(barbero);
        }
        if (uEntidad.getTurnos() != null) {
            List<TurnoDTO> turnos = uEntidad.getTurnos().stream().map(
                (turno) -> convertirTurno(turno)
            ).toList();
            instancia.setTurnos(turnos);
        }
        if (uEntidad.getServicios() != null) {
            List<ServicioDTO> servicios = uEntidad.getServicios().stream().map(
                (servicio) -> convertirServicio(servicio)
            ).toList();
            instancia.setServicios(servicios);
        }
        if (uEntidad.getJefe() != null) {
            JefeDTO jefe = convertirJefe(uEntidad.getJefe());
            instancia.setJefe(jefe);
        }
        if(uEntidad.getFoto() != null) {
            ImagenDTO foto = convertirImagen(uEntidad.getFoto());
            instancia.setFoto(foto);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // JEFE
    public static JefeDTO convertirJefe(jefeEntidad jEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.jefe))
            return (JefeDTO) DTOsTemporales.get(entidades.jefe);

        JefeDTO instancia = new JefeDTO();
        instancia.setId(jEntidad.getId());
        // instancia.setRol(jEntidad.getRol());
        instancia.setEstado(jEntidad.getEstado());

        instancia.setCreatedAt(jEntidad.getCreatedAt());
        instancia.setUpdatedAt(jEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.jefe, instancia);

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

        DTOsTemporales.clear();
        return instancia;
    }

    // BARBERO
    public static BarberoDTO convertirBarbero(barberoEntidad bEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.barbero))
            return (BarberoDTO) DTOsTemporales.get(entidades.barbero);

        BarberoDTO instancia = new BarberoDTO();
        instancia.setId(bEntidad.getId());
        instancia.setEstado(bEntidad.getEstado());

        instancia.setCreatedAt(bEntidad.getCreatedAt());
        instancia.setUpdatedAt(bEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.barbero, instancia);
        
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
            List<TurnoDTO> turnos = bEntidad.getTurnos().stream().map(
                (turno) -> convertirTurno(turno)
            ).toList();
            instancia.setTurnos(turnos);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // BARBERÍA
    public static BarberiaDTO convertirBarberia(barberiaEntidad bEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.barberia))
            return (BarberiaDTO) DTOsTemporales.get(entidades.barberia);

        BarberiaDTO instancia = new BarberiaDTO();
        instancia.setId(bEntidad.getId());
        instancia.setNombre(bEntidad.getNombre());
        instancia.setUbicacion(bEntidad.getUbicacion());
        instancia.setLocalidad(bEntidad.getLocalidad());
        instancia.setHorario(bEntidad.getHorario());
        instancia.setEstado(bEntidad.getEstado());
        
        instancia.setCreatedAt(bEntidad.getCreatedAt());
        instancia.setUpdatedAt(bEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.barberia, instancia);

        if (bEntidad.getJefe() != null) {
            JefeDTO jefe = convertirJefe(bEntidad.getJefe());
            instancia.setJefe(jefe);
        }
        if (bEntidad.getFoto() != null) {
            ImagenDTO imagen = convertirImagen(bEntidad.getFoto());
            instancia.setFoto(imagen);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // VALORACIÓN
    public static ValoracionDTO convertirValoracion(valoracionEntidad vEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.valoracion))
            return (ValoracionDTO) DTOsTemporales.get(entidades.valoracion);

        ValoracionDTO instancia = new ValoracionDTO();
        instancia.setId(vEntidad.getId());
        instancia.setPuntaje(vEntidad.getPuntaje());
        instancia.setComentario(vEntidad.getComentario());
        instancia.setEstado(vEntidad.getEstado());
    
        instancia.setCreatedAt(vEntidad.getCreatedAt());
        instancia.setUpdatedAt(vEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.valoracion, instancia);

        if(vEntidad.getBarberoValoracion() !=null){
            BarberoDTO usuario = convertirBarbero(vEntidad.getBarberoValoracion());
            instancia.setBarberoValoracion(usuario);
        }

        if (vEntidad.getTurnoValoracion() != null) {
            TurnoDTO turno = convertirTurno(vEntidad.getTurnoValoracion());
            instancia.setTurnoValoracion(turno);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // SERVICIO
    public static ServicioDTO convertirServicio(servicioEntidad sEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.servicio))
            return (ServicioDTO) DTOsTemporales.get(entidades.servicio);

        ServicioDTO instancia = new ServicioDTO();
        instancia.setId(sEntidad.getId());
        instancia.setNombre(sEntidad.getNombre());
        instancia.setPrecio(sEntidad.getPrecio());
        instancia.setDuracion(sEntidad.getDuracion());
        instancia.setEstado(sEntidad.getEstado());

        instancia.setCreatedAt(sEntidad.getCreatedAt());
        instancia.setUpdatedAt(sEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.servicio, instancia);

        // RELACIONES
        if (sEntidad.getUsuarioServicio() != null) {
            UsuarioDTO usuario = convertirUsuario(sEntidad.getUsuarioServicio());
            instancia.setUsuarioServicio(usuario);
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

        DTOsTemporales.clear();
        return instancia;
    }

    // TURNO
    public static TurnoDTO convertirTurno(turnoEntidad tEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.turno)) 
            return (TurnoDTO) DTOsTemporales.get(entidades.turno);

        TurnoDTO instancia = new TurnoDTO();
        instancia.setId(tEntidad.getId());
        instancia.setFechaTurno(tEntidad.getFechaTurno());
        instancia.setHoraTurno(tEntidad.getHoraTurno());
        instancia.setEstado(tEntidad.getEstado());

        instancia.setCreatedAt(tEntidad.getCreatedAt());
        instancia.setUpdatedAt(tEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.turno, instancia);

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

        DTOsTemporales.clear();
        return instancia;
    }

    // LIQUIDACION
    public static LiquidacionDTO convertirLiquidacion(liquidacionEntidad lEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.liquidacion))
            return (LiquidacionDTO) DTOsTemporales.get(entidades.liquidacion);

        LiquidacionDTO instancia = new LiquidacionDTO();
        instancia.setId(lEntidad.getId());
        instancia.setTotal(lEntidad.getTotal());

        instancia.setCreatedAt(lEntidad.getCreatedAt());
        instancia.setUpdatedAt(lEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.liquidacion, instancia);

        // RELACIONES
        if (lEntidad.getTurnoLiquidacion() != null) {
            TurnoDTO turno = convertirTurno(lEntidad.getTurnoLiquidacion());
            instancia.setTurnoLiquidacion(turno);
        }

        DTOsTemporales.clear();
        return instancia;
    }

    // NOTIFICACION
    public static NotificacionDTO convertirNotificacion(notificacionEntidad nEntidad) {
        // OBTENCIÓN DEL DTO SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (DTOsTemporales.containsKey(entidades.notificacion))
            return (NotificacionDTO) DTOsTemporales.get(entidades.notificacion);

        NotificacionDTO instancia = new NotificacionDTO();
        instancia.setId(nEntidad.getId());
        instancia.setEstado(nEntidad.getEstado());

        instancia.setCreatedAt(nEntidad.getCreatedAt());
        instancia.setUpdatedAt(nEntidad.getUpdatedAt());

        DTOsTemporales.put(entidades.notificacion, instancia);

        // RELACIONES
        if (nEntidad.getTurnoNotificacion() != null) {
            TurnoDTO turno = convertirTurno(nEntidad.getTurnoNotificacion());
            instancia.setTurnoNotificacion(turno);
        }

        DTOsTemporales.clear();
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
}
