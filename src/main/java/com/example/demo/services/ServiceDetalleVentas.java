package com.example.demo.services;

import com.example.demo.repository.RepositoryVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDetalleVentas {
    @Autowired
    RepositoryVentas repositoryVentas;
}
