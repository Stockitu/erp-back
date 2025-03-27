package net.youtics.backend.crudProductos.repositories;

import net.youtics.backend.crudProductos.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
