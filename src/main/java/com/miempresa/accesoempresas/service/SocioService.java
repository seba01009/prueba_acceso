package com.miempresa.accesoempresas.service;

import com.miempresa.accesoempresas.model.Socio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioService {

    public static List<Socio> obtenerSociosPorZona(Connection conn, int idZona) throws SQLException {
        List<Socio> socios = new ArrayList<>();

        String sql = "SELECT id_socio, nombre, rut, direccion FROM socios WHERE zona_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idZona);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_socio");
            String nombre = rs.getString("nombre");
            String rut = rs.getString("rut");
            String direccion = rs.getString("direccion");

            Socio socio = new Socio(id, nombre, rut, direccion);
            socios.add(socio);
        }

        rs.close();
        stmt.close();

        return socios;
    }
}
