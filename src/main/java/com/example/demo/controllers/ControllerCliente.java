package com.example.demo.controllers;

import com.example.demo.models.ModeloCliente;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes") // Añadido para agrupar los endpoints relacionados con 'clientes'
public class ControllerCliente {

    @Autowired
    private RepositoryCliente repo;

    @Autowired
    private ServiceCliente serviceCliente;

    @GetMapping
    public List<ModeloCliente> getClientes() {
        return repo.findAll();
    }

    @PostMapping
    public ModeloCliente postCliente(@RequestBody ModeloCliente cliente) {
        return repo.save(cliente);
    }

    @PutMapping("/{id}")
    public ModeloCliente updateCliente(@PathVariable Long id, @RequestBody ModeloCliente cliente) {
        return repo.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(cliente.getNombre());
                    clienteExistente.setApellido(cliente.getApellido());
                    clienteExistente.setDocumento(cliente.getDocumento());
                    clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
                    return repo.save(clienteExistente);
                })
                .orElseGet(() -> {
                    cliente.setId(id);
                    return repo.save(cliente);
                });
    }

    //El siguiente put lo hgo para probar algo que se ve en la clase workshop dsp de la clase 8

    @PutMapping("prueba/{id}")
    public ModeloCliente update(@PathVariable Long id, @RequestBody ModeloCliente cliente){
        ModeloCliente updateClient = repo.findById(id).get();//por alguna razon usamos repo para buscar por id
        System.out.println(updateClient);
        updateClient.setNombre(cliente.getNombre()); //por alguna razon aca se usa cliente para llegar al modelo (sigue abajo)
        repo.save(updateClient); // mientras que aca se usa repo para guardar data en el modelo.
        return updateClient;
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}/info")
    public Object getClienteInfo(@PathVariable Long id) {
        Object response = serviceCliente.obtenerInfoCliente(id);
        if (response instanceof ServiceCliente.ClienteInfo) {
            ServiceCliente.ClienteInfo clienteInfo = (ServiceCliente.ClienteInfo) response;
            return new ClienteInfoResponse(
                    clienteInfo.getCliente().getNombre(),
                    clienteInfo.getCliente().getApellido(),
                    clienteInfo.getEdad()
            );
        } else {
            return response; // Esto podría ser un mensaje de error
        }
    }

    private static class ClienteInfoResponse {
        private final String nombre;
        private final String apellido;
        private final int años;

        public ClienteInfoResponse(String nombre, String apellido, int años) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.años = años;
        }

        // Getters
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public int getAños() { return años; }
    }
}