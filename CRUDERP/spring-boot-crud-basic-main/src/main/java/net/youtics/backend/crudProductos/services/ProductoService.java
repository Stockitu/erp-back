package net.youtics.backend.crudProductos.services;

import net.youtics.backend.crudProductos.entities.Producto;
import net.youtics.backend.crudProductos.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public Producto save(Producto producto);
    public List<Producto> get();
    public Optional <Producto> getById(Long id);

    public Optional <Producto> delete(Long id);
    public Optional <Producto> update( Long id, Producto producto);
}
