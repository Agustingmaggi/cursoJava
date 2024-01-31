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
        ServiceCliente.ClienteInfo clienteInfo = serviceCliente.obtenerInfoCliente(id);

        if (clienteInfo != null) {
            // Crear un objeto JSON con la estructura deseada
            return new ClienteInfoResponse(
                    clienteInfo.getCliente().getNombre(),
                    clienteInfo.getCliente().getApellido(),
                    clienteInfo.getEdad()
            );
        } else {
            return "Cliente no encontrado";
        }
    }

    // Clase para representar la estructura del JSON de respuesta
    private static class ClienteInfoResponse {
        private final String nombre;
        private final String apellido;
        private final int años;

        public ClienteInfoResponse(String nombre, String apellido, int años) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.años = años;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public int getAños() {
            return años;
        }
    }
}