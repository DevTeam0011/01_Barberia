package com.barberia.demo.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberia.demo.dtos.BarberoDTO;
import com.barberia.demo.dtos.TurnoDTO;
import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.entidades.barberoEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.repositorios.barberoRepositorio;
import com.barberia.demo.repositorios.turnoRepositorio;
import com.barberia.demo.repositorios.usuarioRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@Service
public class turnoServicio {
    @Autowired
    turnoRepositorio tRepositorio;
    @Autowired
    barberoRepositorio bRepositorio;
    @Autowired
    usuarioRepositorio uRepositorio;

    @Autowired
    private notificacionServicio nService;

    // CREAR TURNO
    @Transactional
    public void crearTurno(TurnoDTO tDTO) {
        turnoEntidad newTurno = ConversorDto_Entidad.convertirTurno(tDTO);

        if (tDTO.getUsuarioTurno() != null) {
            Optional<usuarioEntidad> usuarioOpt = uRepositorio.findById(tDTO.getUsuarioTurno().getId());
            if (usuarioOpt.isPresent()) {
                usuarioEntidad usuario = usuarioOpt.get();
                newTurno.setUsuarioTurno(usuario);
            }
        }

        if (tDTO.getTurnoBarbero() != null) {
            Optional<barberoEntidad> barberoOpt = bRepositorio.findById(tDTO.getTurnoBarbero().getId());
            if (barberoOpt.isPresent()) {
                barberoEntidad barbero = barberoOpt.get();
                newTurno.setTurnoBarbero(barbero);
            }
        }

        tRepositorio.save(newTurno);

        // Sacar info. para las notif. por mail
        String horaTurno = tDTO.getHoraTurno().toString();
        String fechaTurno = tDTO.getFechaTurno().toString();
        String nombreBarbero = tDTO.getTurnoBarbero().getUsuarioBarbero().getNombre();
        String nombreUsuario = tDTO.getUsuarioTurno().getNombre();

        // Mensaje de notificación con información adicional para el usuario
        String mensajeUsuario = String.format("Su turno se creó con éxito para el %s a las %s con el barbero %s.",
                fechaTurno, horaTurno, nombreBarbero);

        // Mensaje de notificación con información adicional para el barbero
        String mensajeBarbero = String.format("Se ha creado un nuevo turno para el %s a las %s con el cliente %s.",
                fechaTurno, horaTurno, nombreUsuario);

        // Envía notificaciones
        nService.enviarNotificacion(tDTO.getUsuarioTurno(), mensajeUsuario);
        nService.enviarNotificacion(tDTO.getTurnoBarbero(), mensajeBarbero);
    }

    // MODIFICAR TURNO (FECHA / HORA)
    @Transactional
    public void modificarTurnoFyH(TurnoDTO tDto) {
        turnoEntidad turno = tRepositorio.findById(tDto.getId()).orElse(null);

        turno.setHoraTurno(tDto.getHoraTurno());
        turno.setFechaTurno(tDto.getFechaTurno());

        tRepositorio.save(turno);
    }

    // MODIFICAR TURNO (BARBERO)
    @Transactional
    public void modificarTurnoBarbero(TurnoDTO tDto) {
        turnoEntidad turno = tRepositorio.findById(tDto.getId()).orElse(null);

        Optional<barberoEntidad> barbero = bRepositorio.findById(tDto.getTurnoBarbero().getId());
        if (barbero.isPresent()) {
            turno.setTurnoBarbero(barbero.get());
        }

        tRepositorio.save(turno);
    }

    // MODIFICAR TURNO (ESTADO)
    @Transactional
    public void modificarEstado(TurnoDTO tDto) {
        turnoEntidad turno = tRepositorio.findById(tDto.getId()).orElse(null);
        turno.setEstado(tDto.getEstado());
        tRepositorio.save(turno);
    }

    // LISTAR TURNOS
    @Transactional(readOnly = true)
    public List<TurnoDTO> listarTurnos() {
        List<turnoEntidad> turnos = tRepositorio.findAll();
        return turnos.stream().map(ConversorEntidad_Dto::convertirTurno)
                .collect(Collectors.toList());
    }

    // LISTAR TURNOS (xUSUARIO)
    @Transactional(readOnly = true)
    public List<TurnoDTO> listarTurnosxUsuario(UsuarioDTO uDto) {
        Optional<usuarioEntidad> usuario = uRepositorio.findById(uDto.getId());
        if (usuario.isPresent()) {
            List<turnoEntidad> turnos = tRepositorio.listarTurnosxUsuario(usuario.get());
            return turnos.stream().map(ConversorEntidad_Dto::convertirTurno)
                    .collect(Collectors.toList());
        }

        return null;

    }

    // LISTAR TURNOS (xBARBERO)
    @Transactional(readOnly = true)
    public List<TurnoDTO> listarTurnosxBarbero(BarberoDTO bDto) {
        Optional<barberoEntidad> barbero = bRepositorio.findById(bDto.getId());
        if (barbero.isPresent()) {
            List<turnoEntidad> turnos = tRepositorio.listarTurnosxBarbero(barbero.get());
            return turnos.stream().map(ConversorEntidad_Dto::convertirTurno)
                    .collect(Collectors.toList());
        }

        return null;

    }

    // LISTAR TURNOS (xFECHA)
    @Transactional(readOnly = true)
    public List<TurnoDTO> listarTurnosxFecha(String fecha) {
        // Recibe el dato de tipo String y lo pasa a LocalDate
        LocalDate fechaBusqueda = LocalDate.parse(fecha);
        List<turnoEntidad> turnos = tRepositorio.listarTurnosxFecha(fechaBusqueda);

        return turnos.stream().map(ConversorEntidad_Dto::convertirTurno)
                .collect(Collectors.toList());
    }

    // BUSCAR TURNO (ID)
    @Transactional(readOnly = true)
    public TurnoDTO buscarTurnoPorId(UUID id) {
        Optional<turnoEntidad> turno = tRepositorio.findById(id);
        return turno.map(ConversorEntidad_Dto::convertirTurno).orElse(null);
    }

}
