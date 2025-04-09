package com.miempresa.accesoempresas.model;

public class Zona {
    private int idZona;
    private String nombreZona;
    private String descripcion;

    // Constructor
    public Zona(int idZona, String nombreZona, String descripcion) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.descripcion = descripcion;
    }

    // Getters
    public int getIdZona() { return idZona; }
    public String getNombreZona() { return nombreZona; }
    public String getDescripcion() { return descripcion; }
}

