package com.miempresa.accesoempresas.service;

import com.miempresa.accesoempresas.model.Zona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZonaService {

    public static List<Zona> obtenerZonas(Connection conn) {
        List<Zona> zonas = new ArrayList<>();

        String sql = "SELECT id_zona, nombre_zona, descripcion FROM zonas";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_zona");
                String nombre = rs.getString("nombre_zona");
                String descripcion = rs.getString("descripcion");

                Zona zona = new Zona(id, nombre, descripcion);
                zonas.add(zona);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Error al consultar zonas: " + e.getMessage());
        }

        return zonas;
    }
}
