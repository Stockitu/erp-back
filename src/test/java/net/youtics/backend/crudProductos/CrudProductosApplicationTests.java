package net.youtics.backend.crudProductos;

import net.youtics.backend.crudProductos.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudProductosApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {

	}

	@Test
	void testGetAllProductos() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/productos", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("nombre");
	}

	@Test
	void testCreateProducto() {
		Producto producto = new Producto(null, "Dulce", 10, "MarcaX", "Dulce de prueba", 1500.0);

		ResponseEntity<Producto> response = restTemplate.postForEntity("/api/productos", producto, Producto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getNombre()).isEqualTo("Dulce");
	}
}
