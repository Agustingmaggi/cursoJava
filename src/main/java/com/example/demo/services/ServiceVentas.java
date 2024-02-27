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

    public List<ModeloVentas> findAll(){
        return  repositoryVentas.findAll();
    }
    public void guardarVenta(ModeloVentas venta) {
        // Generar un identificador único para la venta
        String idVentaUnica = UUID.randomUUID().toString();

        // Asignar el mismo identificador único a todas las líneas de la venta
        venta.setId_venta_unica(idVentaUnica);

        // Obtener el nombre del producto y la cantidad desde el cuerpo de la solicitud (request body)
        String nombreProducto = venta.getProducto();
        int cantidad = venta.getCantidad();

        // Buscar el producto por su nombre en el repositorio de productos
        ModeloProducto producto = repositoryProducto.findByDescripcion(nombreProducto);

        if (producto != null) {
            // Asignar el precio del producto y calcular el precio total de la venta
            int precioProducto = producto.getPrecio().intValue();
            int precioTotalVenta = precioProducto * cantidad;

            // Actualizar el precio_total_venta sumando el precio total de esta venta al valor existente
            int precioTotalVentaExistente = venta.getPrecio_total_venta() != null ? venta.getPrecio_total_venta() : 0;
            int precioTotalVentaFinal = precioTotalVentaExistente + precioTotalVenta;

            // Asignar los valores calculados a la venta
            venta.setPrecio_producto(precioProducto);
            venta.setPrecio_total_venta(precioTotalVentaFinal);

            // Guardar la venta en la base de datos
            repositoryVentas.save(venta);

        } else {
            // Manejar la situación donde el producto no existe o no tiene precio
            throw new RuntimeException("El producto con nombre " + nombreProducto + " no existe o no tiene un precio asociado.");
            // Puedes lanzar una excepción específica o manejar el error de otra manera según tus necesidades
        }

    }
        private int actualizarSumaTotalVentas() {
            List<ModeloVentas> ventas = repositoryVentas.findAll();

            // Inicializar la suma en 0
            int sumaTotalVentas = 0;

            // Sumar los valores de valor_total_venta de cada venta
            for (ModeloVentas venta : ventas) {
                Integer valorTotalVenta = venta.getPrecio_total_venta();
                if (valorTotalVenta != null) {
                    sumaTotalVentas += valorTotalVenta;
                }

            }
            sumaTotalVentas = actualizarSumaTotalVentas();
            return sumaTotalVentas;
        }
        }