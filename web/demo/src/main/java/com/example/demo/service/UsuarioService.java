package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        if ("Paciente".equalsIgnoreCase(usuario.getRol())) {
            // Crear un paciente asociado al usuario
            Paciente paciente = new Paciente();
            paciente.setNombre(usuario.getUsername()); // Usar username como nombre inicial
            paciente.setPassword(usuario.getPassword()); // Configurar la misma contraseña

            // Asociar al médico si se proporciona un médico
            if (usuario.getMedicoId() != null) {
                Medico medico = medicoRepository.findById(usuario.getMedicoId())
                        .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + usuario.getMedicoId()));
                paciente.setMedico(medico);
            }

            // Guardar paciente
            pacienteRepository.save(paciente);
        }
        
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}

