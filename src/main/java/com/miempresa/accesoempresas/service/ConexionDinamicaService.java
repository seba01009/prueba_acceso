package com.miempresa.accesoempresas.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDinamicaService {

    public static Connection conectar(String ip, String nombreBd, String usuario, String clave) throws SQLException {
        String url = "jdbc:mysql://" + ip + ":3306/" + nombreBd + "?useSSL=false&serverTimezone=UTC";

        // Retorna la conexión activa hacia la base de datos de la empresa
        return DriverManager.getConnection(url, usuario, clave);
    }
}

