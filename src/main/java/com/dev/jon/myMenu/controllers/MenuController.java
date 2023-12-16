package com.dev.jon.myMenu.controllers;

import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.request.AgregarUsuarioRequest;
import com.dev.jon.myMenu.request.ModificaMenuRequest;
import com.dev.jon.myMenu.services.MenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearMenu(@RequestBody Menu menu, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            menuService.guardarMenuConUsuario(menu, usuario);
            return ResponseEntity.ok("Menú creado exitosamente.");
        } else {
            return ResponseEntity.status(401).body("No has iniciado sesión.");
        }
    }

    @PostMapping("/agregar-usuario")
    public ResponseEntity<String> agregarUsuarioAlMenu(@RequestBody AgregarUsuarioRequest request,
                                                       HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            menuService.addOtherUserInMenu(request, usuario);
            return ResponseEntity.status(200).body("Todo salio Bien");

        } else {
            return ResponseEntity.status(401).body("No has iniciado sesión.");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarNombreYDescripcionDelMenu(@RequestBody ModificaMenuRequest request, HttpSession session
    ) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        menuService.actualizarNombreYDescripcionDelMenu(request,usuario);
        return ResponseEntity.ok("Nombre y descripción del menú actualizados exitosamente.");
    }

    @GetMapping("/listar")
    public ResponseEntity<Set<Menu>> listarMenusPorUsuario(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            Set<Menu> menus = menuService.getAllMenuByUser(usuario);
            return ResponseEntity.ok(menus);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}