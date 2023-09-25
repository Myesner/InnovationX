package com.example.myapplication;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String costoUnitario;
    private String precioUnitario;

    public Producto(String nombre, String costoUnitario, String precioUnitario) {
        this.nombre = nombre;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(String costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
