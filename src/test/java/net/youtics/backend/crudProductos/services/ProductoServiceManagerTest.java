package net.youtics.backend.crudProductos.services;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceManagerTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceManager productoServiceManager;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1L, "Laptop", 5, "Dell", "Laptop de alta gama", 999.99);
    }

    @Test
    void save_ValidProduct_ReturnsSavedProduct() {
        // Arrange
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // Act
        Producto result = productoServiceManager.save(producto);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void save_NullProduct_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoServiceManager.save(null);
        });
    }

    @Test
    void get_ReturnsAllProducts() {
        // Arrange
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> result = productoServiceManager.get();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
    }

    @Test
    void getById_ValidId_ReturnsProduct() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        Optional<Producto> result = productoServiceManager.getById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getNombre());
    }

    @Test
    void getById_InvalidId_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoServiceManager.getById(0L);
        });
    }

    @Test
    void delete_ExistingId_ReturnsDeletedProduct() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        doNothing().when(productoRepository).deleteById(1L);

        // Act
        Optional<Producto> result = productoServiceManager.delete(1L);

        // Assert
        assertTrue(result.isPresent());
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_ValidProduct_ReturnsUpdatedProduct() {
        // Arrange
        Producto updatedProducto = new Producto(1L, "Laptop Pro", 10, "Dell", "Laptop profesional", 1299.99);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(updatedProducto);

        // Act
        Optional<Producto> result = productoServiceManager.update(1L, updatedProducto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Laptop Pro", result.get().getNombre());
        assertEquals(1299.99, result.get().getPrecio());
    }
}