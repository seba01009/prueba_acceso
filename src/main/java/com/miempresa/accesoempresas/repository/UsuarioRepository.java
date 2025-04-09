package com.miempresa.accesoempresas.repository;

import com.miempresa.accesoempresas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Buscar por RUT y contraseña
    Usuario findByRutAndContrasena(String rut, String contrasena);
}
