package net.youtics.backend.crudProductos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    private int cantidad;
    private String marca;
    private String descripcion;
    @Min(value = 100, message = "El precio es obligatorio")
    @NotNull(message = "El precio no puede ser nulo")
    private Double precio;


    public Producto() {
        // Constructor vacío necesario para JPA
    }

    public Producto(Long id, String nombre, int cantidad, String marca, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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
}
