package com.example.demo.controllers;

import com.example.demo.models.Cliente;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repo;


    @GetMapping
    public  String index(){
        return "Conectado";
    }

    @GetMapping("clientes")
    public List<Cliente> getClientes(){
        return  repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente){
        repo.save(cliente);
        return "Guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setEmail(cliente.getEmail());
        repo.save(updateCliente);
        return "Modificado";
    }

    @DeleteMapping("baja/{id}")
    public String delete(@PathVariable Long id){

        Cliente deleteCliente = repo.findById(id).get();
        repo.delete(deleteCliente);
        return "Eliminado";
    }



}