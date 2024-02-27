package com.example.demo.services;

import com.example.demo.models.ModeloProducto;
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

    public List<ModeloVentas> findAll(){
        return  repositoryVentas.findAll();
    }
    public void guardarVenta(ModeloVentas venta) {
        // Obtener el precio del producto por su nombre
        String nombreProducto = venta.getProducto();

        // Buscar el producto por su nombre en el repositorio de productos
        ModeloProducto producto = repositoryProducto.findByNombre(nombreProducto);

        if (producto != null) {
            // Asignar el precio del producto a la venta
            venta.setPrecio_producto(producto.getPrecio().intValue());

            // Calcular el precio total de la venta (ejemplo)
            int cantidadProductos = 1;  // Puedes ajustar esto según tus necesidades
            int precioTotalVenta = producto.getPrecio().intValue() * cantidadProductos;
            venta.setPrecio_total_venta(precioTotalVenta);

            // Guardar la venta en la base de datos
            repositoryVentas.save(venta);
        } else {
            // Manejar la situación donde el producto no existe o no tiene precio
            throw new RuntimeException("El producto con nombre " + nombreProducto + " no existe o no tiene un precio asociado.");
            // Puedes lanzar una excepción específica o manejar el error de otra manera según tus necesidades
        }
    }
    }
