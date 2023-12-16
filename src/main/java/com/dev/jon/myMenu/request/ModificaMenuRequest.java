package com.dev.jon.myMenu.request;

public class ModificaMenuRequest {
    private String nuevoNombre;
    private String nuevaDescripcion;
    private Long idMenu;

    // Constructores, getters y setters...

    public ModificaMenuRequest() {
    }

    public ModificaMenuRequest(String nuevoNombre, String nuevaDescripcion, Long idMenu) {
        this.nuevoNombre = nuevoNombre;
        this.nuevaDescripcion = nuevaDescripcion;
        this.idMenu = idMenu;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getNuevaDescripcion() {
        return nuevaDescripcion;
    }

    public void setNuevaDescripcion(String nuevaDescripcion) {
        this.nuevaDescripcion = nuevaDescripcion;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }
}
