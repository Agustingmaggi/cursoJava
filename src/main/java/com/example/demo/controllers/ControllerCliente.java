package com.example.demo.controllers;

import com.example.demo.models.ModeloCliente;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCliente {

    @Autowired
    private RepositoryCliente repo;

    @Autowired
    private ServiceCliente serviceCliente;


    @GetMapping
    public  String index(){
        return "Conectado";
    }

    @GetMapping("clientes")
    public List<ModeloCliente> getClientes(){
        return  repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody ModeloCliente cliente){
        repo.save(cliente);
        return "Guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody ModeloCliente cliente){
        ModeloCliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setApellido(cliente.getApellido());
        repo.save(updateCliente);
        return "Modificado";
    }

    @DeleteMapping("baja/{id}")
    public String delete(@PathVariable Long id){

        ModeloCliente deleteCliente = repo.findById(id).get();
        repo.delete(deleteCliente);
        return "Eliminado";
    }

    @GetMapping("/{id}/info")
    public Object getClienteInfo(@PathVariable Long id) {
        return ServiceCliente.obtenerInfoCliente(id);
    }


}