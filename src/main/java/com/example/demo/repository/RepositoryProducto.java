package com.example.demo.repository;

import com.example.demo.models.ModeloProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProducto extends JpaRepository<ModeloProducto, Long> {
    ModeloProducto findByDescripcion(String descripcion);
}
