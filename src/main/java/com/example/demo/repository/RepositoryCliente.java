package com.example.demo.repository;

import com.example.demo.models.ModeloCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<ModeloCliente, Long> {
}
