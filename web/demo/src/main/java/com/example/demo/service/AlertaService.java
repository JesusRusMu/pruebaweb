package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alerta;
import com.example.demo.model.Paciente;
import com.example.demo.repository.AlertaRepository;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository alertaRepository;

    public Alerta generarAlerta(Paciente paciente, String mensaje) {
        Alerta alerta = new Alerta();
        alerta.setPaciente(paciente);
        alerta.setMensaje(mensaje);
        alerta.setFechaHora(LocalDateTime.now());
        return alertaRepository.save(alerta);
    }

    public List<Alerta> obtenerAlertasPorPaciente(Long pacienteId) {
        return alertaRepository.findByPacienteId(pacienteId);
    }
}

