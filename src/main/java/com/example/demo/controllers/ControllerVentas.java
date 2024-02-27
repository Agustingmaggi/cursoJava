package com.example.demo.controllers;

import com.example.demo.models.ModeloProducto;
import com.example.demo.models.ModeloVentas;
import com.example.demo.services.ServiceVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class ControllerVentas {
    @Autowired
    private ServiceVentas serviceVentas;

    @GetMapping
    public List<ModeloVentas> getVentas(){return serviceVentas.findAll();}
}
