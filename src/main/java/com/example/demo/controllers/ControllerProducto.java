package com.example.demo.controllers;

import com.example.demo.models.ModeloProducto;
import com.example.demo.services.ServiceCliente;
import com.example.demo.services.ServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ControllerProducto {
@Autowired
    private ServiceProducto serviceProducto;

    @GetMapping
    public List<ModeloProducto> getProductos() {
        return serviceProducto.findAll();
    }
}
