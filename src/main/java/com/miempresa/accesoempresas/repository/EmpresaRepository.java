package com.miempresa.accesoempresas.repository;

import com.miempresa.accesoempresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    Empresa findByCodigo(String codigo);
}
