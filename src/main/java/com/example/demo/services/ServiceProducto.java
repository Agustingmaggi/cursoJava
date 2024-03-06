package com.example.demo.services;

import com.example.demo.models.ModeloProducto;
import com.example.demo.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProducto {

    @Autowired
    private RepositoryProducto repository;

    public List<ModeloProducto> findAll() { return repository.findAll();}

    public void guardarProducto(ModeloProducto producto) {
        repository.save(producto);
    }
}
