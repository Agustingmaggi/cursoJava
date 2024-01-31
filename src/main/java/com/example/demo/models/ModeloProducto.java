package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto")
public class ModeloProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String descripcion;

    @Column
    @Getter
    @Setter
    private String codigo;

    @Column
    @Getter
    @Setter
    private Long stock;

    @Column
    @Getter
    @Setter
    private Long precio;


}
