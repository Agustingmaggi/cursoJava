package com.example.demo.repository;

import com.example.demo.models.ModeloDetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryVentasDetalle extends JpaRepository<ModeloDetalleVenta, Long> {
}
