package com.example.demo.services;

import com.example.demo.models.ModeloCliente;
import com.example.demo.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ServiceCliente {

    @Autowired
    private RepositoryCliente repository;

    public List<ModeloCliente> findAll() {
        return repository.findAll();
    }

    public ModeloCliente save(ModeloCliente cliente) {
        return repository.save(cliente);
    }

    public Object obtenerInfoCliente(Long id) {
        ModeloCliente cliente = repository.findById(id).orElse(null);

        if (cliente != null) {
            ClienteInfo clienteInfo = new ClienteInfo(cliente, calcularEdad(cliente.getFechaNacimiento()));
            return clienteInfo; // Devuelve un objeto ClienteInfo
        } else {
            return new ErrorResponse("Cliente no encontrado");
        }
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }

    private static class ErrorResponse {
        private final String mensajeError;

        public ErrorResponse(String mensajeError) {
            this.mensajeError = mensajeError;
        }

        public String getMensajeError() {
            return mensajeError;
        }
    }

    public static class ClienteInfo {
        private final ModeloCliente cliente;
        private final int edad;

        public ClienteInfo(ModeloCliente cliente, int edad) {
            this.cliente = cliente;
            this.edad = edad;
        }

        public ModeloCliente getCliente() {
            return cliente;
        }

        public int getEdad() {
            return edad;
        }
    }
}
