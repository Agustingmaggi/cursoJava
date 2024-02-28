package com.example.demo.controllers;

import com.example.demo.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/hora")
public class ControllerTime {
    @Autowired
    private TimeService timeService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<?> obtenerHoraActual(){
        String hora = timeService.getHoraActual();
        return ResponseEntity.ok(hora);
    }

}
