package com.dev.jon.myMenu.services;

import com.dev.jon.myMenu.entitys.Producto;
import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.repository.MenuRepository;
import com.dev.jon.myMenu.repository.ProductoRepository;
import com.dev.jon.myMenu.repository.UsuarioMenuRelacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MenuService menuService;
    @Autowired
    private UsuarioMenuRelacionRepository usuarioMenuRelacionRepository;

    public List<Producto> obtenerTodosLosProductos(Long menuId, Usuario usuario) {
        List<Producto> productos = new ArrayList<>();

        menuService.getMenuByMenuIdAndUserId(menuId, usuario.getId()).ifPresentOrElse(
                menuObtenido -> productos.addAll(productoRepository.findByMenuId(menuObtenido.getId())),
                () -> {
                    throw new IllegalArgumentException("No puede obtener productos porque el menú no es suyo");
                }
        );

        return productos;
    }


    public Optional<Producto> obtenerProductoPorId(Long id, Usuario usuario) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (usuarioMenuRelacionRepository.existsByUsuarioAndMenu(usuario, producto.get().getMenu())) {
            return producto;
        }else {
            throw new IllegalArgumentException("No puede obtener productos Por que no existe en su menu");
        }

    }

    public void crearProducto(Producto producto, Long menuId, Usuario usuario) {
        menuService.getMenuByMenuIdAndUserId(menuId, usuario.getId())
                .map(menu -> {
                    producto.setMenu(menu);
                    productoRepository.save(producto);
                    return menu;
                })
                .orElseGet(() -> {
                    throw new IllegalArgumentException("No puede agregar un producto porque el menú no es suyo");
                });
    }

    public void actualizarProducto(Producto producto, Menu menuParameter, Usuario usuario) {
        menuService.getMenuByMenuIdAndUserId(menuParameter.getId(), usuario.getId())
                .ifPresentOrElse(menu -> {
                    productoRepository.findById(producto.getId()).ifPresentOrElse(existingProducto -> {
                            productoRepository.save(producto);
                    }, () -> {
                        throw new IllegalArgumentException("No puede Actualizar producto por que no existe para este menu");
                    });
                }, () -> {
                    throw new IllegalArgumentException("No puede agregar un producto porque el menú no es suyo");
                });
    }

    public void eliminarProducto(Producto producto, Menu menuParameter, Usuario usuario) {

        menuService.getMenuByMenuIdAndUserId(menuParameter.getId(), usuario.getId())
                .ifPresentOrElse(menu -> {
                    productoRepository.findById(producto.getId()).ifPresentOrElse(existingProducto -> {
                            productoRepository.deleteById(producto.getId());
                    }, () -> {
                        throw new IllegalArgumentException("No puede borrar producto por que no existe para este menu");
                    });
                }, () -> {
                    throw new IllegalArgumentException("No puede agregar un producto porque el menú no es suyo");
                });

    }
}
