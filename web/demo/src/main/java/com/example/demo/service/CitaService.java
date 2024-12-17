package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Cita;
import com.example.demo.repository.CitaRepository;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    // Actualizar una cita
    public Cita actualizarCita(Long id, Cita citaActualizada) {
        return citaRepository.findById(id).map(cita -> {
            cita.setFechaHora(citaActualizada.getFechaHora());
            cita.setPaciente(citaActualizada.getPaciente()); // Relación con el paciente
            cita.setMedico(citaActualizada.getMedico()); // Relación con el médico
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + id));
    }

    // Método para borrar una cita
    public void borrarCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada con el ID: " + id);
        }
        citaRepository.deleteById(id);
    }
}
