package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UsuarioRepository usuarioRepository;

    public MedicoService(MedicoRepository medicoRepository, UsuarioRepository usuarioRepository) {
        this.medicoRepository = medicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos los médicos
    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }

    // Obtener un médico por ID
    public Optional<Medico> obtenerPorId(Long id) {
        return medicoRepository.findById(id);
    }

    // Guardar o actualizar un médico
    public Medico actualizarMedico(Long id, Medico medico) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
    
        medicoExistente.setNombre(medico.getNombre());
        medicoExistente.setApellido(medico.getApellido());
        medicoExistente.setEmail(medico.getEmail());
        medicoExistente.setEspecialidad(medico.getEspecialidad());
    
        if (medico.getPacientes() != null) {
            medicoExistente.getPacientes().clear();
            for (Paciente paciente : medico.getPacientes()) {
                paciente.setMedico(medicoExistente);
                medicoExistente.getPacientes().add(paciente);
            }
        }
    
        return medicoRepository.save(medicoExistente);
    }
                
    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }
    
    // Eliminar un médico por ID
    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
