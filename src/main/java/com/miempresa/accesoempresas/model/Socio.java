package com.miempresa.accesoempresas.model;

public class Socio {
    private int idSocio;
    private String nombre;
    private String rut;
    private String direccion;

    public Socio(int idSocio, String nombre, String rut, String direccion) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
    }

    public int getIdSocio() { return idSocio; }
    public String getNombre() { return nombre; }
    public String getRut() { return rut; }
    public String getDireccion() { return direccion; }
}
