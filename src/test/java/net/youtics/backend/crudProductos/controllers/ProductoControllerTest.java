package net.youtics.backend.crudProductos.controllers;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.services.ProductoServiceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoServiceManager productoServiceManager;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1L, "Laptop", 5, "Dell", "Laptop de alta gama", 999.99);
    }

    @Test
    void listarProductos_ReturnsListOfProducts() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        when(productoServiceManager.get()).thenReturn(productos);

        // Act
        ResponseEntity<List<Producto>> response = productoController.listarProductos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Laptop", response.getBody().get(0).getNombre());
    }

    @Test
    void obtenerProductoId_WhenProductExists_ReturnsProduct() {
        // Arrange
        when(productoServiceManager.getById(1L)).thenReturn(Optional.of(producto));

        // Act
        ResponseEntity<Producto> response = productoController.obtenerProductoId(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Laptop", response.getBody().getNombre());
    }

    @Test
    void obtenerProductoId_WhenProductNotExists_ReturnsNotFound() {
        // Arrange
        when(productoServiceManager.getById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Producto> response = productoController.obtenerProductoId(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void crearProducto_ReturnsCreatedProduct() {
        // Arrange
        when(productoServiceManager.save(any(Producto.class))).thenReturn(producto);

        // Act
        ResponseEntity<Producto> response = productoController.crearProducto(producto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void actualizarProducto_WhenProductExists_ReturnsUpdatedProduct() {
        // Arrange
        Producto updatedProducto = new Producto(1L, "Laptop Pro", 10, "Dell", "Laptop profesional", 1299.99);
        when(productoServiceManager.update(1L, updatedProducto)).thenReturn(Optional.of(updatedProducto));

        // Act
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, updatedProducto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Laptop Pro", response.getBody().getNombre());
        assertEquals(1299.99, response.getBody().getPrecio());
    }

    @Test
    void eliminarProducto_WhenProductExists_ReturnsNoContent() {
        // Arrange
        when(productoServiceManager.delete(1L)).thenReturn(Optional.of(producto));

        // Act
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}