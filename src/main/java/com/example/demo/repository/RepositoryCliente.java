package com.example.demo.repository;

import com.example.demo.controllers.ControllerCliente;
import com.example.demo.models.ModeloCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<ControllerCliente, Long> {
}
