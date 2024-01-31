package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class ModeloCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String nombre;

    @Column
    @Getter
    @Setter
    private String apellido;

    @Column
    @Getter
    @Setter
    private String documento;

    @Column(name = "fecha_nacimiento")
    @Getter
    @Setter
    private LocalDate fecha_nacimiento;

    public LocalDate getFechaNacimiento() {
        return fecha_nacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
    }
}
