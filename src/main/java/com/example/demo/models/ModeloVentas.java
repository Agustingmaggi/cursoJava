package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ventas")
public class ModeloVentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private Integer cliente_id;

    @Column
    @Getter
    @Setter
    private Date creacion;

    @Column
    @Getter
    @Setter
    private String producto;

    @Column
    @Getter
    @Setter
    private Integer cantidad;

    @Column
    @Getter
    @Setter
    private Integer precio_producto;

    @Column
    @Getter
    @Setter
    private Integer precio_total_venta;
}
