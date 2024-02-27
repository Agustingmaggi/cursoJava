package com.example.demo.services;

import com.example.demo.models.ModeloProducto;
import com.example.demo.models.ModeloVentas;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.repository.RepositoryProducto;
import com.example.demo.repository.RepositoryVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceVentas {

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryProducto repositoryProducto;

    @Autowired
    private RepositoryVentas repositoryVentas;

    public List<ModeloVentas> findAll() {
        return repositoryVentas.findAll();
    }


    public void guardarVenta(ModeloVentas venta) {

        // Obtener el nombre del producto y la cantidad desde el cuerpo de la solicitud (request body)
        String nombreProducto = venta.getProducto();
        int cantidad = venta.getCantidad();

        // Buscar el producto por su nombre en el repositorio de productos
        ModeloProducto producto = repositoryProducto.findByDescripcion(nombreProducto);

        if (producto != null) {
            // Asignar el precio del producto y calcular el precio total de la venta
            int precioProducto = producto.getPrecio().intValue();
            int precioTotalVenta = precioProducto * cantidad;

            venta.setPrecio_total_venta(precioTotalVenta);

            // Guardar la venta en la base de datos
            repositoryVentas.save(venta);


        } else {
            // Manejar la situación donde el producto no existe o no tiene precio
            throw new RuntimeException("El producto con nombre " + nombreProducto + " no existe o no tiene un precio asociado.");
            // Puedes lanzar una excepción específica o manejar el error de otra manera según tus necesidades
        }
    }
    private void actualizarSumaTotalVentas() {
        // Obtener la lista completa de ventas desde el repositorio
        List<ModeloVentas> todasLasVentas = repositoryVentas.findAll();

        // Calcular la suma total de todas las ventas
        int sumaTotalVentas = todasLasVentas.stream()
                .mapToInt(ModeloVentas::getPrecio_total_venta)
                .sum();
        // Asignar la suma total al último objeto de venta (puedes ajustar según tu lógica)
        if (!todasLasVentas.isEmpty()) {
            ModeloVentas ultimaVenta = todasLasVentas.get(todasLasVentas.size() - 1);
            ultimaVenta.setPrecio_total_venta(sumaTotalVentas);
            repositoryVentas.save(ultimaVenta); // Guardar la actualización en la base de datos
        }
    }
}