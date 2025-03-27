package net.youtics.backend.crudProductos.controllers;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.services.ProductoServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Habilitar CORS
public class ProductoController {

    @Autowired
    private ProductoServiceManager productoServiceManager;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoServiceManager.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoId(@PathVariable Long id) {
        return productoServiceManager.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoServiceManager.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,
                                                       @RequestBody Producto producto) {
        return productoServiceManager.update(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productoServiceManager.delete(id)
                .map(p -> ResponseEntity.noContent().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }
}