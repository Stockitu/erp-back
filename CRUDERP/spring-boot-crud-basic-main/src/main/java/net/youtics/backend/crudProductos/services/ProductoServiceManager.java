package net.youtics.backend.crudProductos.services;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceManager implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto save(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> get() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Producto> delete(Long id) {
        Optional<Producto> producto = getById(id);
        producto.ifPresent(p -> productoRepository.deleteById(id));
        return producto;
    }

    @Override
    @Transactional
    public Optional<Producto> update(Long id, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser nulo");
        }

        return getById(id).map(existingProduct -> {
            existingProduct.setNombre(producto.getNombre());
            existingProduct.setCantidad(producto.getCantidad());
            existingProduct.setMarca(producto.getMarca());
            existingProduct.setDescripcion(producto.getDescripcion());
            existingProduct.setPrecio(producto.getPrecio());
            return productoRepository.save(existingProduct);
        });
    }
}