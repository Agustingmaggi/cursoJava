package com.example.demo.repository;

import com.example.demo.models.ModeloVentas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryVentas extends JpaRepository<ModeloVentas, Long> {
}
