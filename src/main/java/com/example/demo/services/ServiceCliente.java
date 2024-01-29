package com.example.demo.services;

import com.example.demo.models.ModeloCliente;
import com.example.demo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public class ServiceCliente {
    @Autowired
    private RepositoryCliente repository;

    public List findAll() {
        return repository.findAll();
    }

    public ModeloCliente save(ModeloCliente cliente) {
        return repository.save(cliente);
    }

    public ClienteInfo obtenerInfoCliente(Long id) {
        return null;
    }
}