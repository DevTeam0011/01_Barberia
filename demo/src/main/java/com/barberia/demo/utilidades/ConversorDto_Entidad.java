package com.barberia.demo.utilidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barberia.demo.dtos.AdministradorDTO;
import com.barberia.demo.dtos.BarberiaDTO;
import com.barberia.demo.dtos.BarberoDTO;
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
import com.barberia.demo.entidades.barberiaEntidad;
import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.liquidacionEntidad;
import com.barberia.demo.entidades.notificacionEntidad;
import com.barberia.demo.entidades.servicioEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.entidades.valoracionEntidad;

public class ConversorDto_Entidad {
    private static enum entidades {
        admin, barberia, barbero, imagen, jefe, liquidacion, notificacion, servicio, turno, usuario, valoracion
    }
    private static Map<entidades, Object> EntidadesTemporales = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <entidad>entidad convertir(Object dto) throws Exception {
        entidad retorno = null;

        if (dto instanceof AdministradorDTO) {
            administradorEntidad admin = convertirAdmin((AdministradorDTO) dto);
            retorno = (entidad) admin;
        }
        if (dto instanceof UsuarioDTO) {
            usuarioEntidad usuario = convertirUsuario((UsuarioDTO) dto);
            retorno = (entidad) usuario;
        }
        if (dto instanceof JefeDTO) {
            jefeEntidad jefe = convertirJefe((JefeDTO) dto);
            retorno = (entidad) jefe;
        }
        if (dto instanceof BarberoDTO) {
            retorno = (entidad) convertirBarbero((BarberoDTO) dto);
        }
        if (dto instanceof BarberiaDTO) {
            retorno = (entidad) convertirBarberia((BarberiaDTO) dto);
        }
        if (dto instanceof ValoracionDTO) {
            retorno = (entidad) convertirValoracion((ValoracionDTO) dto);
        }
        if (dto instanceof ServicioDTO) {
            retorno = (entidad) convertirServicio((ServicioDTO) dto);
        }
        if (dto instanceof TurnoDTO) {
            retorno = (entidad) convertirTurno((TurnoDTO) dto);
        }
        if (dto instanceof LiquidacionDTO) {
            retorno = (entidad) convertirLiquidacion((LiquidacionDTO) dto);
        }
        if (dto instanceof NotificacionDTO) {
            retorno = (entidad) convertirNotificacion((NotificacionDTO) dto);
        }
        if (dto instanceof ImagenDTO) {
            retorno = (entidad) convertirImagen((ImagenDTO) dto);
        }

        EntidadesTemporales.clear();

        if (retorno == null) {
            throw new Exception("El tipo de dto ingresado no es válido: " + dto.getClass().toString());
        }

        return retorno;
    }

    // ADMIN
    private static administradorEntidad convertirAdmin(AdministradorDTO aDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.admin)) 
            return (administradorEntidad) EntidadesTemporales.get(entidades.admin);

        administradorEntidad instancia = new administradorEntidad();
        instancia.setId(aDTO.getId());
        instancia.setRol(aDTO.getRol());
        instancia.setEstado(aDTO.getEstado());

        instancia.setCreatedAt(aDTO.getCreatedAt());
        instancia.setUpdatedAt(aDTO.getUpdatedAt());
        
        EntidadesTemporales.put(entidades.admin, instancia);

        if (aDTO.getUsuarioAdmin() != null) {
            usuarioEntidad usuario = convertirUsuario(aDTO.getUsuarioAdmin());
            instancia.setUsuarioAdmin(usuario);
        }
        
        return instancia;
    }

    // USUARIO
    private static usuarioEntidad convertirUsuario(UsuarioDTO uDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.usuario)) 
            return (usuarioEntidad) EntidadesTemporales.get(entidades.usuario);
        
        usuarioEntidad instancia = new usuarioEntidad();
        instancia.setId(uDTO.getId());
        instancia.setRol(uDTO.getRol());
        instancia.setNombre(uDTO.getNombre());
        instancia.setEmail(uDTO.getEmail());
        instancia.setContrasena(uDTO.getContrasena());
        instancia.setTelefono(uDTO.getTelefono());
        instancia.setEstado(uDTO.getEstado());

        instancia.setCreatedAt(uDTO.getCreatedAt());
        instancia.setUpdatedAt(uDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.usuario, instancia);

        if (uDTO.getAdmin() != null) {
            administradorEntidad admin = convertirAdmin(uDTO.getAdmin());
            instancia.setAdmin(admin);
        }
        if (uDTO.getBarbero() != null) {
            barberoEntidad barbero = convertirBarbero(uDTO.getBarbero());
            instancia.setBarbero(barbero);
        }
        if (uDTO.getTurnos() != null) {
            List<turnoEntidad> turnos = uDTO.getTurnos().stream().map(
                (turno) -> convertirTurno(turno)
            ).toList();
            instancia.setTurnos(turnos);
        }
        if (uDTO.getServicios() != null) {
            List<servicioEntidad> servicios = uDTO.getServicios().stream().map(
                (servicio) -> convertirServicio(servicio)
            ).toList();
            instancia.setServicios(servicios);
        }
        if (uDTO.getJefe() != null) {
            jefeEntidad jefe = convertirJefe(uDTO.getJefe());
            instancia.setJefe(jefe);
        }
        if(uDTO.getFoto() != null) {
            ImagenEntidad foto = convertirImagen(uDTO.getFoto());
            instancia.setFoto(foto);
        }

        return instancia;
    }

    // JEFE
    private static jefeEntidad convertirJefe(JefeDTO jDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.jefe))
            return (jefeEntidad) EntidadesTemporales.get(entidades.jefe);

        jefeEntidad instancia = new jefeEntidad();
        instancia.setId(jDTO.getId());
        instancia.setRol(jDTO.getRol());
        instancia.setEstado(jDTO.getEstado());

        instancia.setCreatedAt(jDTO.getCreatedAt());
        instancia.setUpdatedAt(jDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.jefe, instancia);

        // RELACIONES
        if (jDTO.getUsuarioJefe() != null) {
            usuarioEntidad usuario = convertirUsuario(jDTO.getUsuarioJefe());
            instancia.setUsuarioJefe(usuario);
        }
        if (jDTO.getBarberia() != null) {
            barberiaEntidad barberia = convertirBarberia(jDTO.getBarberia());
            instancia.setBarberia(barberia);
        }
        if (jDTO.getBarberos() != null) {
            List<barberoEntidad> barberos = jDTO.getBarberos().stream().map(
                (barbero) -> convertirBarbero(barbero)
            ).toList();
            instancia.setBarberos(barberos);
        }
        if (jDTO.getServicioJefe() != null) {
            servicioEntidad servicio = convertirServicio(jDTO.getServicioJefe());
            instancia.setServicioJefe(servicio);
        }

        return instancia;
    }

    // BARBERO
    private static barberoEntidad convertirBarbero(BarberoDTO bDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.barbero))
            return (barberoEntidad) EntidadesTemporales.get(entidades.barbero);

        barberoEntidad instancia = new barberoEntidad();
        instancia.setId(bDTO.getId());
        instancia.setEstado(bDTO.getEstado());

        instancia.setCreatedAt(bDTO.getCreatedAt());
        instancia.setUpdatedAt(bDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.barbero, instancia);
        
        if (bDTO.getJefeBarbero() != null) {
            jefeEntidad jefe = convertirJefe(bDTO.getJefeBarbero());
            instancia.setJefeBarbero(jefe);
        }
        if (bDTO.getUsuarioBarbero() != null) {
            usuarioEntidad usuario = convertirUsuario(bDTO.getUsuarioBarbero());
            instancia.setUsuarioBarbero(usuario);
        }
        if (bDTO.getValoracion() != null) {
            valoracionEntidad valoracion = convertirValoracion(bDTO.getValoracion());
            instancia.setValoracion(valoracion);
        }
        if (bDTO.getServicios() != null) {
            List<servicioEntidad> servicios = bDTO.getServicios().stream().map(
                (servicio) -> convertirServicio(servicio)
            ).toList();
            instancia.setServicios(servicios);
        }
        if (bDTO.getTurnos() != null) {
            List<turnoEntidad> turnos = bDTO.getTurnos().stream().map(
                (turno) -> convertirTurno(turno)
            ).toList();
            instancia.setTurnos(turnos);
        }

        return instancia;
    }

    // BARBERÍA
    private static barberiaEntidad convertirBarberia(BarberiaDTO bDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.barberia))
            return (barberiaEntidad) EntidadesTemporales.get(entidades.barberia);

        barberiaEntidad instancia = new barberiaEntidad();
        instancia.setId(bDTO.getId());
        instancia.setNombre(bDTO.getNombre());
        instancia.setUbicacion(bDTO.getUbicacion());
        instancia.setHorario(bDTO.getHorario());
        instancia.setEstado(bDTO.getEstado());
        
        instancia.setCreatedAt(bDTO.getCreatedAt());
        instancia.setUpdatedAt(bDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.barberia, instancia);

        if (bDTO.getJefe() != null) {
            jefeEntidad jefe = convertirJefe(bDTO.getJefe());
            instancia.setJefe(jefe);
        }
        if (bDTO.getFoto() != null) {
            ImagenEntidad imagen = convertirImagen(bDTO.getFoto());
            instancia.setFoto(imagen);
        }

        return instancia;
    }

    // VALORACIÓN
    private static valoracionEntidad convertirValoracion(ValoracionDTO vDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.valoracion))
            return (valoracionEntidad) EntidadesTemporales.get(entidades.valoracion);

        valoracionEntidad instancia = new valoracionEntidad();
        instancia.setId(vDTO.getId());
        instancia.setPuntaje(vDTO.getPuntaje());
        instancia.setComentario(vDTO.getComentario());
        instancia.setEstado(vDTO.getEstado());
    
        instancia.setCreatedAt(vDTO.getCreatedAt());
        instancia.setUpdatedAt(vDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.valoracion, instancia);

        if(vDTO.getBarberoValoracion() !=null){
            barberoEntidad usuario = convertirBarbero(vDTO.getBarberoValoracion());
            instancia.setBarberoValoracion(usuario);
        }

        if (vDTO.getTurnoValoracion() != null) {
            turnoEntidad turno = convertirTurno(vDTO.getTurnoValoracion());
            instancia.setTurnoValoracion(turno);
        }

        return instancia;
    }

    // SERVICIO
    private static servicioEntidad convertirServicio(ServicioDTO sDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.servicio))
            return (servicioEntidad) EntidadesTemporales.get(entidades.servicio);

        servicioEntidad instancia = new servicioEntidad();
        instancia.setId(sDTO.getId());
        instancia.setNombre(sDTO.getNombre());
        instancia.setPrecio(sDTO.getPrecio());
        instancia.setDuracion(sDTO.getDuracion());
        instancia.setEstado(sDTO.getEstado());

        instancia.setCreatedAt(sDTO.getCreatedAt());
        instancia.setUpdatedAt(sDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.servicio, instancia);

        // RELACIONES
        if (sDTO.getUsuarioServicio() != null) {
            usuarioEntidad usuario = convertirUsuario(sDTO.getUsuarioServicio());
            instancia.setUsuarioServicio(usuario);
        }
        if (sDTO.getBarberoServicio() != null) {
            barberoEntidad barbero = convertirBarbero(sDTO.getBarberoServicio());
            instancia.setBarberoServicio(barbero);
        }
        if (sDTO.getJefeServicio() != null) {
            jefeEntidad jefe = convertirJefe(sDTO.getJefeServicio());
            instancia.setJefeServicio(jefe);
        }
        if (sDTO.getTurnoServicio() != null) {
            turnoEntidad turno = convertirTurno(sDTO.getTurnoServicio());
            instancia.setTurnoServicio(turno);
        }

        return instancia;
    }

    // TURNO
    private static turnoEntidad convertirTurno(TurnoDTO tDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.turno)) 
            return (turnoEntidad) EntidadesTemporales.get(entidades.turno);

        turnoEntidad instancia = new turnoEntidad();
        instancia.setId(tDTO.getId());
        instancia.setFechaTurno(tDTO.getFechaTurno());
        instancia.setHoraTurno(tDTO.getHoraTurno());
        instancia.setEstado(tDTO.getEstado());

        instancia.setCreatedAt(tDTO.getCreatedAt());
        instancia.setUpdatedAt(tDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.turno, instancia);

        // RELACIONES
        if (tDTO.getUsuarioTurno() != null) {
            usuarioEntidad usuario = convertirUsuario(tDTO.getUsuarioTurno());
            instancia.setUsuarioTurno(usuario);
        }
        if (tDTO.getTurnoBarbero() != null) {
            barberoEntidad turno = convertirBarbero(tDTO.getTurnoBarbero());
            instancia.setTurnoBarbero(turno);
        }
        if (tDTO.getNotificacion() != null) {
            notificacionEntidad notificacion = convertirNotificacion(tDTO.getNotificacion());
            instancia.setNotificacion(notificacion);
        }
        if (tDTO.getLiquidacion() != null) {
            liquidacionEntidad liquidacion = convertirLiquidacion(tDTO.getLiquidacion());
            instancia.setLiquidacion(liquidacion);
        }
        if (tDTO.getServicioTurno() != null) {
            servicioEntidad servicio = convertirServicio(tDTO.getServicioTurno());
            instancia.setServicioTurno(servicio);
        }
        if (tDTO.getValoracion() != null) {
            valoracionEntidad valoracion = convertirValoracion(tDTO.getValoracion());
            instancia.setValoracion(valoracion);
        }

        return instancia;
    }

    // LIQUIDACION
    private static liquidacionEntidad convertirLiquidacion(LiquidacionDTO lDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.liquidacion))
            return (liquidacionEntidad) EntidadesTemporales.get(entidades.liquidacion);

        liquidacionEntidad instancia = new liquidacionEntidad();
        instancia.setId(lDTO.getId());
        instancia.setTotal(lDTO.getTotal());

        instancia.setCreatedAt(lDTO.getCreatedAt());
        instancia.setUpdatedAt(lDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.liquidacion, instancia);

        // RELACIONES
        if (lDTO.getTurnoLiquidacion() != null) {
            turnoEntidad turno = convertirTurno(lDTO.getTurnoLiquidacion());
            instancia.setTurnoLiquidacion(turno);
        }

        return instancia;
    }

    // NOTIFICACION
    private static notificacionEntidad convertirNotificacion(NotificacionDTO nDTO) {
        // OBTENCIÓN DEL Entidad SI YA SE MAPEÓ (para evitar bucles infinitos al mapear las relaciones)
        if (EntidadesTemporales.containsKey(entidades.notificacion))
            return (notificacionEntidad) EntidadesTemporales.get(entidades.notificacion);

        notificacionEntidad instancia = new notificacionEntidad();
        instancia.setId(nDTO.getId());
        instancia.setEstado(nDTO.getEstado());

        instancia.setCreatedAt(nDTO.getCreatedAt());
        instancia.setUpdatedAt(nDTO.getUpdatedAt());

        EntidadesTemporales.put(entidades.notificacion, instancia);

        // RELACIONES
        if (nDTO.getTurnoNotificacion() != null) {
            turnoEntidad turno = convertirTurno(nDTO.getTurnoNotificacion());
            instancia.setTurnoNotificacion(turno);
        }

        return instancia;
    }

    // IMAGEN
    private static ImagenEntidad convertirImagen(ImagenDTO iDTO) {
        ImagenEntidad instancia = new ImagenEntidad();
        instancia.setId(iDTO.getId());
        instancia.setMime(iDTO.getMime());
        instancia.setNombre(iDTO.getNombre());
        instancia.setContenido(iDTO.getContenido());

        return instancia;
    }
}
