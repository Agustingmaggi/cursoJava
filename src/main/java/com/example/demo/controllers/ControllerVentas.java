package com.example.demo.controllers;

import com.example.demo.models.ModeloProducto;
import com.example.demo.models.ModeloVentas;
import com.example.demo.services.ServiceVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class ControllerVentas {
    @Autowired
    private ServiceVentas serviceVentas;

    @GetMapping
    public List<ModeloVentas> getVentas(){
        return serviceVentas.findAll();
    }
    @PostMapping
    public ResponseEntity<String> crearVenta(@RequestBody List<ModeloVentas> ventas) {
        try {
            for (ModeloVentas venta : ventas) {
                serviceVentas.guardarVenta(venta);
            }
            return ResponseEntity.ok("Ventas creadas exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear las ventas: " + e.getMessage());
        }
    }
}
