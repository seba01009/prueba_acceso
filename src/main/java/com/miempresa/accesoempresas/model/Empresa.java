package com.miempresa.accesoempresas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    private String codigo;

    private String nombreEmpresa;
    private String rut;
    private String ip;
    private String nombreBd;
    private String usuarioBd;
    private String claveBd;

    // --- Getters y Setters ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNombreBd() {
        return nombreBd;
    }

    public void setNombreBd(String nombreBd) {
        this.nombreBd = nombreBd;
    }

    public String getUsuarioBd() {
        return usuarioBd;
    }

    public void setUsuarioBd(String usuarioBd) {
        this.usuarioBd = usuarioBd;
    }

    public String getClaveBd() {
        return claveBd;
    }

    public void setClaveBd(String claveBd) {
        this.claveBd = claveBd;
    }
}
