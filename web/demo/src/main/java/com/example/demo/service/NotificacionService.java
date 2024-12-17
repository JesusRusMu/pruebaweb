package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notificacion;
import com.example.demo.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerNotificacionesPorPaciente(Long pacienteId) {
        return notificacionRepository.findByPacienteId(pacienteId);
    }

    public Notificacion crearNotificacion(Notificacion notificacion) {
        notificacion.setFechaCreacion(LocalDateTime.now());
        return notificacionRepository.save(notificacion);
    }
}

