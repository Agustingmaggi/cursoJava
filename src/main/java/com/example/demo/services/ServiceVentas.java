package com.example.demo.services;

import com.example.demo.models.ModeloVentas;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.repository.RepositoryProducto;
import com.example.demo.repository.RepositoryVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVentas {
    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryProducto repositoryProducto;

    @Autowired
    private RepositoryVentas repositoryVentas;

    public List<ModeloVentas> findAll(){return  repositoryVentas.findAll();}

}
