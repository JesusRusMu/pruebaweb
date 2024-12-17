package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Mensaje;
import com.example.demo.repository.MensajeRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para enviar un mensaje y guardarlo en la base de datos
    public Mensaje enviarMensaje(Mensaje mensaje) {
        Long pacienteId = mensaje.getPaciente().getId();
        Long medicoId = mensaje.getMedico().getId();
    
        // Validar que los IDs existen en la base de datos
        if (!usuarioRepository.existsById(pacienteId) || !usuarioRepository.existsById(medicoId)) {
            throw new IllegalArgumentException("Paciente o médico no encontrado.");
        }
    
        // Configurar la fecha y guardar el mensaje
        mensaje.setFechaEnvio(LocalDateTime.now());
        return mensajeRepository.save(mensaje);
    }
    

    // Método para recibir mensajes (consulta almacenada en base de datos)
    public List<Mensaje> obtenerMensajes(Long pacienteId, Long medicoId) {
        // Obtener mensajes en ambos sentidos
        List<Mensaje> mensajesPacienteAMedico = mensajeRepository.findByPacienteIdAndMedicoId(pacienteId, medicoId);
        List<Mensaje> mensajesMedicoAPaciente = mensajeRepository.findByMedicoIdAndPacienteId(medicoId, pacienteId);

        // Combinar ambos flujos y devolverlos
        mensajesPacienteAMedico.addAll(mensajesMedicoAPaciente);
        mensajesPacienteAMedico.sort((m1, m2) -> m1.getFechaEnvio().compareTo(m2.getFechaEnvio()));

        return mensajesPacienteAMedico;
    }
}
