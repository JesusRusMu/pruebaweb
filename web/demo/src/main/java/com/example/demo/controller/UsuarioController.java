package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(@PathVariable String username) {
        return usuarioService.buscarPorUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200") // permite solicitudes desde Angular
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        return usuarioRepository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword())
                .map(u -> ResponseEntity.ok(Map.of("message", "Login exitoso", "user", u, "rol", u.getRol())))
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuario o contraseña incorrectos")));
    }


}

