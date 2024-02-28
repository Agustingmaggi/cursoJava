package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="ventasDetalle")
public class ModeloDetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name ="venta_id", referencedColumnName ="id")
    @Getter @Setter
    private ModeloVentas venta;

    @Column
    @Getter @Setter
    private Long producto_id;

    @Column
    @Getter @Setter
    private Integer cantidad;

    @Column
    @Getter @Setter
    private Integer precio_final;
}