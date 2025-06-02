package net.youtics.backend.crudProductos.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTest {

    private Validator validator;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setDni("12345678");
        empleado.setEmail("juan@example.com");
        empleado.setUsuario("jperez");
        empleado.setContrasena("password123");
    }

    @Test
    void empleadoValido_NoValidationErrors() {
        var violations = validator.validate(empleado);
        assertTrue(violations.isEmpty());
    }

    @Test
    void empleadoSinNombre_ValidationFails() {
        empleado.setNombre(null);
        var violations = validator.validate(empleado);
        assertFalse(violations.isEmpty());
    }

    @Test
    void empleadoEmailInvalido_ValidationFails() {
        empleado.setEmail("no-es-un-email");
        var violations = validator.validate(empleado);
        assertFalse(violations.isEmpty());
    }

    @Test
    void empleadoSinContrasena_ValidationFails() {
        empleado.setContrasena(null);
        var violations = validator.validate(empleado);
        assertFalse(violations.isEmpty());
    }
}