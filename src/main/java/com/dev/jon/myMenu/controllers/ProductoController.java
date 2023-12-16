package com.dev.jon.myMenu.controllers;

import com.dev.jon.myMenu.entitys.Producto;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.request.CrearProductoRequest;
import com.dev.jon.myMenu.services.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/menu/{id}")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos(@PathVariable Long id,HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            List<Producto> productos = productoService.obtenerTodosLosProductos(id,usuario);
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            Optional<Producto> producto = productoService.obtenerProductoPorId(id,usuario);
            return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody CrearProductoRequest request, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            productoService.crearProducto(request.getProducto(),request.getMenu().getId(),usuario);
            return ResponseEntity.ok("Producto Creado Exitosamente");
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping()
    public ResponseEntity<String> actualizarProducto( @RequestBody CrearProductoRequest request, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            productoService.actualizarProducto(request.getProducto(),request.getMenu(),usuario);
            return ResponseEntity.ok("Producto actualizado exitosamente.");
        } else {
            return ResponseEntity.status(401).body("No has iniciado sesión.");
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> eliminarProducto( @RequestBody CrearProductoRequest request, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            productoService.eliminarProducto(request.getProducto(),request.getMenu(),usuario);
            return ResponseEntity.ok("Producto eliminado exitosamente.");
        } else {
            return ResponseEntity.status(401).body("No has iniciado sesión.");
        }
    }
}