package com.example.demo.controllers;

import com.example.demo.models.ModeloCliente;
import com.example.demo.models.ModeloProducto;
import com.example.demo.repository.RepositoryProducto;
import com.example.demo.services.ServiceCliente;
import com.example.demo.services.ServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ControllerProducto {
    @Autowired
    private ServiceProducto serviceProducto;

    @Autowired
    private RepositoryProducto repo;

    @GetMapping
    public List<ModeloProducto> getProductos() {
        return serviceProducto.findAll();
    }

    @PostMapping
    public ModeloProducto postProducto(@RequestBody ModeloProducto producto) {
        return repo.save(producto);
    }
}

