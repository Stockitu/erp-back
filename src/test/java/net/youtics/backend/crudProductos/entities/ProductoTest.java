package net.youtics.backend.crudProductos.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductoTest {
    private Validator validator;
    private Producto producto;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setCantidad(10);
        producto.setMarca("Dell");
        producto.setDescripcion("Laptop de alta gama");
        producto.setPrecio(999.99);
    }

    @Test
    void productoValido_NoValidationErrors() {
        var violations = validator.validate(producto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void productoSinNombre_ValidationFails() {
        producto.setNombre(null);
        var violations = validator.validate(producto);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    void productoPrecioInvalido_ValidationFails() {
        producto.setPrecio(50.0); // Menor que 100
        var violations = validator.validate(producto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void productoPrecioNulo_ValidationFails() {
        producto.setPrecio(null);
        var violations = validator.validate(producto);
        assertFalse(violations.isEmpty());
    }
}
