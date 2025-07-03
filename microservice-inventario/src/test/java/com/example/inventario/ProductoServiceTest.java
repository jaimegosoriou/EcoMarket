package com.example.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase prueba el comportamiento del servicio ProductoService
 * usando Spring Boot y una base de datos real (no en memoria).
 */
@SpringBootTest(properties = "spring.config.name=ProductoServiceTest")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductoServiceTest {

    // Inyección del servicio a probar
    @Autowired
    private ProductoService service;

    // Inyección del repositorio real para verificar datos
    @Autowired
    private ProductoRepository repository;

    /**
     * Este método se ejecuta antes de cada prueba.
     * Borra todos los datos del repositorio y crea 2 productos de ejemplo.
     */
    @BeforeEach
    void setUp() {
        repository.deleteAll(); // Limpia base de datos antes de cada prueba

        // Producto 1
        Producto p1 = new Producto();
        p1.setNombre("Producto1");
        p1.setPrecio(10.0);
        p1.setCantidad(5);
        service.guardar(p1);

        // Producto 2
        Producto p2 = new Producto();
        p2.setNombre("Producto2");
        p2.setPrecio(15.0);
        p2.setCantidad(3);
        service.guardar(p2);
    }

    /**
     * Prueba que el método listar() retorne los 2 productos creados.
     */
    @Test
    void testListar() {
        List<Producto> productos = service.listar();
        assertEquals(2, productos.size()); // Esperamos 2 productos
    }

    /**
     * Prueba que guardar() cree un nuevo producto correctamente.
     */
    @Test
    void testGuardar() {
        Producto nuevo = new Producto();
        nuevo.setNombre("Producto3");
        nuevo.setPrecio(20.0);
        nuevo.setCantidad(2);

        Producto saved = service.guardar(nuevo);

        // Verifica que el producto tenga ID asignado
        assertNotNull(saved.getId());

        // Verifica que ahora existan 3 productos
        assertEquals(3, repository.count());
    }

    /**
     * Prueba que eliminar() borre un producto correctamente.
     */
    @Test
    void testEliminar() {
        // Obtener todos los productos
        List<Producto> productos = service.listar();
        Long idAEliminar = productos.get(0).getId();

        // Eliminar uno
        service.eliminar(idAEliminar);

        // Verificar que solo quede 1 producto
        assertEquals(1, repository.count());
    }
}
