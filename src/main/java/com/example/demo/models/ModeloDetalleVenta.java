package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ventasDetalle")
public class ModeloDetalleVenta {
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
    private Integer precio_final;
}
