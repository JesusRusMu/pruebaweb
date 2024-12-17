package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.HistorialCitas;
import com.example.demo.repository.HistorialCitasRepository;

@Service
public class HistorialCitasService {

    @Autowired
    private HistorialCitasRepository historialCitasRepository;

    public List<HistorialCitas> obtenerHistorialPorPaciente(Long pacienteId) {
        return historialCitasRepository.findByPacienteId(pacienteId);
    }

    public HistorialCitas guardarHistorial(HistorialCitas historial) {
        return historialCitasRepository.save(historial);
    }
}

