package com.dev.jon.myMenu.request;

import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Producto;

public class CrearProductoRequest {
    Menu menu;
    Producto producto;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
