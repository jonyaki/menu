package com.dev.jon.myMenu.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_menu_relacion")
public class UsuarioMenuRelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public UsuarioMenuRelacion(Usuario usuario, Menu menu) {
        this.usuario = usuario;
        this.menu = menu;
    }
    public UsuarioMenuRelacion() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


}
