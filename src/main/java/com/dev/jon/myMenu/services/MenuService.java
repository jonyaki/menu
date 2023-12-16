package com.dev.jon.myMenu.services;

import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.entitys.UsuarioMenuRelacion;
import com.dev.jon.myMenu.repository.MenuRepository;
import com.dev.jon.myMenu.repository.UsuarioMenuRelacionRepository;
import com.dev.jon.myMenu.repository.UsuarioRepository;
import com.dev.jon.myMenu.request.AgregarUsuarioRequest;
import com.dev.jon.myMenu.request.ModificaMenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UsuarioMenuRelacionRepository usuarioMenuRelacionRepository;

    public void actualizarNombreYDescripcionDelMenu(ModificaMenuRequest request, Usuario userOwner) {
        Optional<Menu> menuOptional = menuRepository.findById(request.getIdMenu());
        if (usuarioMenuRelacionRepository.existsByUsuarioAndMenu(userOwner, menuOptional.get())) {
            menuOptional.ifPresent(menu -> {
                menu.setNombre(request.getNuevoNombre());
                menu.setDescripcion(request.getNuevaDescripcion());
                menuRepository.save(menu);
            });
        }}
    public void addOtherUserInMenu(AgregarUsuarioRequest request, Usuario userOwner) {
        Menu menu = menuRepository.getById(request.getIdMenu());
        if (usuarioMenuRelacionRepository.existsByUsuarioAndMenu(userOwner, menu)) {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getUserMail());
            if (usuario.isPresent()) {
                creaRelacionUsuarioMenu(usuario.get(), menu);
            }
        }
    }

    public Set<Menu> getAllMenuByUser(Usuario usuario) {
        return usuarioMenuRelacionRepository.findAllByUsuarioId(usuario.getId())
                .stream()
                .map(UsuarioMenuRelacion::getMenu)
                .collect(Collectors.toSet());
    }
    public Optional<Menu> getMenuByMenuIdAndUserId(Long menuId,Long usuarioId) {
        return Optional.ofNullable(usuarioMenuRelacionRepository.findMenuByMenuIdAndUsuarioId(menuId, usuarioId).getMenu());

    }
    public void guardarMenuConUsuario(Menu menu, Usuario usuario) {
        menuRepository.save(menu);
        UsuarioMenuRelacion relacion = new UsuarioMenuRelacion(usuario, menu);
        usuarioMenuRelacionRepository.save(relacion);
    }


    public void creaRelacionUsuarioMenu(Usuario usuario, Menu menu) {
        UsuarioMenuRelacion relacion = new UsuarioMenuRelacion(usuario, menu);
        usuarioMenuRelacionRepository.save(relacion);
    }
}
