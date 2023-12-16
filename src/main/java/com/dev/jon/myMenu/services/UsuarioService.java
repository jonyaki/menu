package com.dev.jon.myMenu.services;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.repository.UsuarioMenuRelacionRepository;
import com.dev.jon.myMenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMenuRelacionRepository usuarioMenuRelacionRepository;
    public Optional<Usuario> encontrarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void guardarUsuario(Usuario usuario) {
        usuario.encriptarPassword(); // Encriptar la contraseña antes de guardar
        usuarioRepository.save(usuario);
    }

    // Otros métodos según tus necesidades
}