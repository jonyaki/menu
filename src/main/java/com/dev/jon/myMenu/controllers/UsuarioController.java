package com.dev.jon.myMenu.controllers;

import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.encontrarPorEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.badRequest().body("El email ya está registrado.");
        }

        usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok("Usuario creado exitosamente.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario usuario, HttpSession session) {
        Optional<Usuario> usuarioExistente = usuarioService.encontrarPorEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && usuarioExistente.get().verificarPassword(usuario.getPassword())) {
            session.setAttribute("usuario", usuarioExistente.get());
            return ResponseEntity.ok("Inicio de sesión exitoso.");
        } else {
            return ResponseEntity.badRequest().body("Credenciales incorrectas.");
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<String> obtenerPerfil(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            return ResponseEntity.ok("Nombre de usuario: " + usuario.getNombre());
        } else {
            return ResponseEntity.status(401).body("No has iniciado sesión.");
        }
    }
}
