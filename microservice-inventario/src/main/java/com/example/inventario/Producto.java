package com.example.inventario;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidad que representa un producto dentro del inventario")
public class Producto {

    // ID autogenerado por la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del producto", example = "1")
    private Long id;

    // Nombre del producto (ej: Maceta, Tierra abonada)
    @Schema(description = "Nombre del producto", example = "Maceta de barro")
    private String nombre;

    // Precio del producto en pesos
    @Schema(description = "Precio unitario del producto", example = "4500.00")
    private Double precio;

    // Stock disponible
    @Schema(description = "Cantidad disponible en inventario", example = "12")
    private Integer cantidad;

    // Getters y setters (acceso a los atributos)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
