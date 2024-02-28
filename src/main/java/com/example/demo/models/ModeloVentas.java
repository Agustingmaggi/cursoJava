package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="ventas")
public class ModeloVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private Integer cliente_id;

    @Column
    @Getter @Setter
    private String creacion;

    // Relación con ModeloDetalleVenta
    @OneToMany(mappedBy ="venta", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<ModeloDetalleVenta> detallesVenta = new ArrayList<>();

    @Column
    @Getter @Setter
    private Integer precio_total_venta;

}