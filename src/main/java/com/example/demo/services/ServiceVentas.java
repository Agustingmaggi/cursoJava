package com.example.demo.services;

import com.example.demo.models.ModeloDetalleVenta;
import com.example.demo.models.ModeloProducto;
import com.example.demo.models.ModeloVentas;
import com.example.demo.repository.RepositoryCliente;
import com.example.demo.repository.RepositoryProducto;
import com.example.demo.repository.RepositoryVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class ServiceVentas {

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryProducto repositoryProducto;

    @Autowired
    private RepositoryVentas repositoryVentas;

    @Autowired
    private TimeService timeService;

    public List findAll() {
        return repositoryVentas.findAll();
    }

    public void guardarVenta(ModeloVentas venta) {
        int precioTotalVenta = 0;


        String fechaActual = timeService.getHoraActual();

        venta.setCreacion(fechaActual);

        for (ModeloDetalleVenta detalle : venta.getDetallesVenta()) {
            ModeloProducto producto = repositoryProducto.findById(detalle.getProducto_id()).orElse(null);

            if (producto != null) {
                int precioDetalle = producto.getPrecio().intValue() * detalle.getCantidad();
                System.out.println(precioDetalle); //en esta linea y la siguiente esta el tema, no puedo sumar los 2 precios detalles.
                detalle.setPrecio_final(precioDetalle);
                precioTotalVenta += precioDetalle;
            } else {
                throw new RuntimeException("El producto con ID " + detalle.getProducto_id() + " no existe, no tiene un precio asociado o no hay suficiente stock.");
            }
        }

        venta.setPrecio_total_venta(precioTotalVenta);

        // Guardar la venta y automáticamente se guardarán los detalles debido al CascadeType.ALL
        repositoryVentas.save(venta);
    }
}