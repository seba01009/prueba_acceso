package com.miempresa.accesoempresas.controller;

import com.miempresa.accesoempresas.model.Usuario;
import com.miempresa.accesoempresas.model.Empresa;
import com.miempresa.accesoempresas.model.Zona;
import com.miempresa.accesoempresas.model.Socio;
import com.miempresa.accesoempresas.repository.UsuarioRepository;
import com.miempresa.accesoempresas.repository.EmpresaRepository;
import com.miempresa.accesoempresas.service.ConexionDinamicaService;
import com.miempresa.accesoempresas.service.ZonaService;
import com.miempresa.accesoempresas.service.SocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    private Connection conexionEmpresa; // conexión a base remota

    // Muestra formulario login
    @GetMapping("/")
    public String mostrarFormulario() {
        return "login";
    }

    // Procesa login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String rut,
                                @RequestParam String contrasena,
                                Model model) {

        Usuario usuario = usuarioRepo.findByRutAndContrasena(rut, contrasena);

        if (usuario != null) {
            Empresa empresa = empresaRepo.findByCodigo(usuario.getEmpresa());

            if (empresa != null) {
                try {
                    Connection conn = ConexionDinamicaService.conectar(
                            empresa.getIp(),
                            empresa.getNombreBd(),
                            empresa.getUsuarioBd(),
                            empresa.getClaveBd()
                    );

                    this.conexionEmpresa = conn;

                    List<Zona> zonas = ZonaService.obtenerZonas(conn);
                    model.addAttribute("zonas", zonas);

                    return "zonas";

                } catch (Exception e) {
                    model.addAttribute("error", "Error al conectar a la base de datos: " + e.getMessage());
                    return "login";
                }

            } else {
                model.addAttribute("error", "Empresa no encontrada.");
                return "login";
            }

        } else {
            model.addAttribute("error", "RUT o contraseña incorrectos.");
            return "login";
        }
    }

    // Seleccionar zona y mostrar socios
    @PostMapping("/zona/seleccionar")
    public String seleccionarZona(@RequestParam int idZona, Model model) {
        try {
            if (this.conexionEmpresa != null) {
                List<Socio> socios = SocioService.obtenerSociosPorZona(this.conexionEmpresa, idZona);
                model.addAttribute("socios", socios);
                return "socios";
            } else {
                model.addAttribute("error", "Sin conexión a base de datos.");
                return "zonas";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener socios: " + e.getMessage());
            return "zonas";
        }
    }

    // Mostrar formulario para ingresar datos del socio
    @PostMapping("/socio/formulario")
    public String mostrarFormularioSocio(@RequestParam int idSocio, Model model) {
        model.addAttribute("idSocio", idSocio);
        return "formulario";
    }

    // Guardar datos del formulario en la base de datos remota
    @PostMapping("/socio/guardar")
    public String guardarInformacion(@RequestParam int idSocio,
                                     @RequestParam String observacion,
                                     @RequestParam String tipoVisita,
                                     @RequestParam double monto,
                                     Model model) {
        try {
            if (this.conexionEmpresa != null) {
                String sql = "INSERT INTO registros (id_socio, observacion, tipo_visita, monto) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = this.conexionEmpresa.prepareStatement(sql);
                stmt.setInt(1, idSocio);
                stmt.setString(2, observacion);
                stmt.setString(3, tipoVisita);
                stmt.setDouble(4, monto);

                stmt.executeUpdate();
                stmt.close();

                model.addAttribute("mensaje", "Registro guardado correctamente.");
                model.addAttribute("idSocio", idSocio);
                return "formulario";

            } else {
                model.addAttribute("error", "No hay conexión a la base de datos.");
                return "formulario";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            return "formulario";
        }
    }
}
