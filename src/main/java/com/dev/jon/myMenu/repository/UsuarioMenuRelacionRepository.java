package com.dev.jon.myMenu.repository;
import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Usuario;
import com.dev.jon.myMenu.entitys.UsuarioMenuRelacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioMenuRelacionRepository extends JpaRepository<UsuarioMenuRelacion, Long> {
    Set<UsuarioMenuRelacion> findAllByUsuarioId(Long usuarioId);

    UsuarioMenuRelacion findMenuByMenuIdAndUsuarioId(Long menuId, Long usuarioId);

    Boolean existsByUsuarioAndMenu(Usuario usuario, Menu menu);
}
