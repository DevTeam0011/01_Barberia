package com.barberia.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barberia.demo.dtos.BarberoDTO;
import com.barberia.demo.dtos.NotificacionDTO;
import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.entidades.notificacionEntidad;
import com.barberia.demo.entidades.turnoEntidad;
import com.barberia.demo.repositorios.notificacionRepositorio;
import com.barberia.demo.repositorios.turnoRepositorio;
import com.barberia.demo.utilidades.ConversorDto_Entidad;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@Service
public class notificacionServicio {
    @Autowired
    notificacionRepositorio nRepositorio;
    @Autowired
    turnoRepositorio tRepositorio;

    // ENVIO DE MAILS
    @Autowired
    private JavaMailSender mailSender;

    public void enviarNotificacion(UsuarioDTO usuario, String mensaje) {
        enviarCorreo(usuario.getEmail(), "Notificación de Turno", mensaje);
    }

    public void enviarNotificacion(BarberoDTO barbero, String mensaje) {
        enviarCorreo(barbero.getUsuarioBarbero().getEmail(), "Notificación de Turno", mensaje);
    }

    private void enviarCorreo(String destinatario, String asunto, String texto) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(texto);
        mailSender.send(mensaje);
    }
    // ---------------------------------------------

    // CREAR NOTIFICACION
    @Transactional
    public void crearNotificacion(NotificacionDTO nDTO) {
        notificacionEntidad newNotificacion = ConversorDto_Entidad.convertirNotificacion(nDTO);
        if (nDTO.getTurnoNotificacion() != null) {
            Optional<turnoEntidad> turnoOpt = tRepositorio.findById(nDTO.getTurnoNotificacion().getId());
            if (turnoOpt.isPresent()) {
                turnoEntidad turno = turnoOpt.get();
                newNotificacion.setTurnoNotificacion(turno);
            }
        }

        nRepositorio.save(newNotificacion);
    }

    // MODIFICAR NOTIFICACION (ESTADO)
    @Transactional
    public void modificarNotificacion(NotificacionDTO nDTO) {
        notificacionEntidad notificacion = nRepositorio.findById(nDTO.getId()).orElse(null);

        notificacion.setEstado(nDTO.getEstado());
        nRepositorio.save(notificacion);

    }

    // LISTAR NOTIFICACIONES
    @Transactional(readOnly = true)
    public List<NotificacionDTO> listarNotificaciones() {
        List<notificacionEntidad> notificaciones = nRepositorio.findAll();
        return notificaciones.stream().map(ConversorEntidad_Dto::convertirNotificacion)
                .collect(Collectors.toList());
    }

    // BUSCAR NOTIFICACION (ID)
    @Transactional(readOnly = true)
    public NotificacionDTO buscarNotiPorId(UUID id) {
        Optional<notificacionEntidad> notificacioOpt = nRepositorio.findById(id);
        return notificacioOpt.map(ConversorEntidad_Dto::convertirNotificacion).orElse(null);
    }

}
