package com.example.compra;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controlador de Compra", description = "Operaciones relacionadas con la gestión de compras")
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService service;

    /**
     * Lista todas las compras almacenadas en el sistema.
     * @return lista de compras
     */
    @Operation(summary = "Listar todas las compras")
    @GetMapping
    public List<Compra> listar() {
        return service.listar();
    }

    /**
     * Guarda una nueva compra en la base de datos.
     * @param compra objeto de tipo Compra
     * @return la compra guardada
     */
    @Operation(summary = "Guardar una nueva compra")
    @PostMapping
    public Compra guardar(@RequestBody Compra compra) {
        return service.guardar(compra);
    }

    /**
     * Busca una compra por su ID.
     * @param id identificador de la compra
     * @return la compra encontrada
     */
    @Operation(summary = "Buscar compra por ID")
    @GetMapping("/{id}")
    public Compra buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    /**
     * Elimina una compra según su ID.
     * @param id identificador de la compra
     */
    @Operation(summary = "Eliminar una compra por ID")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
