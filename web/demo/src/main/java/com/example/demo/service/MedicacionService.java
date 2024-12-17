package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Medicacion;
import com.example.demo.repository.MedicacionRepository;

@Service
public class MedicacionService {
    @Autowired
    private MedicacionRepository medicacionRepository;

    public Medicacion crearMedicacion(Medicacion medicacion) {
        return medicacionRepository.save(medicacion);
    }

    public List<Medicacion> obtenerMedicacionesPorPaciente(Long pacienteId) {
        return medicacionRepository.findByPacienteId(pacienteId);
    }

    public Medicacion actualizarMedicacion(Long id, Medicacion medicacion) {
        Medicacion medicacionExistente = medicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicacion no encontrada con ID: " + id));
        medicacionExistente.setNombre(medicacion.getNombre());
        medicacionExistente.setDosis(medicacion.getDosis());
        medicacionExistente.setFrecuencia(medicacion.getFrecuencia());
        medicacionExistente.setFechaInicio(medicacion.getFechaInicio());
        medicacionExistente.setFechaFin(medicacion.getFechaFin());
        return medicacionRepository.save(medicacionExistente);
    }

    public void eliminarMedicacion(Long id) {
        medicacionRepository.deleteById(id);
    }
}

