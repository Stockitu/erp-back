package net.youtics.backend.crudProductos.services;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceManager productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto(1L, "Mouse", 15, "Genius", "Mouse óptico", 1200.0);
    }

    @Test
    void testGuardarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.save(producto);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testGuardarProductoNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.save(null);
        });

        assertEquals("El producto no puede ser nulo", exception.getMessage());
    }

    @Test
    void testObtenerTodosLosProductos() {
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.get();

        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerProductoPorIdExistente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.getById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Mouse", resultado.get().getNombre());
    }

    @Test
    void testObtenerProductoPorIdInvalidoLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.getById(0L);
        });

        assertEquals("ID inválido", exception.getMessage());
    }

    @Test
    void testEliminarProductoExistente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        doNothing().when(productoRepository).deleteById(1L);

        Optional<Producto> resultado = productoService.delete(1L);

        assertTrue(resultado.isPresent());
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testActualizarProductoExistente() {
        Producto actualizado = new Producto(null, "Mouse nuevo", 20, "Genius", "Mouse actualizado", 1300.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(actualizado);

        Optional<Producto> resultado = productoService.update(1L, actualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Mouse nuevo", resultado.get().getNombre());
    }

    @Test
    void testActualizarProductoNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.update(1L, null);
        });

        assertEquals("Producto no puede ser nulo", exception.getMessage());
    }
}
