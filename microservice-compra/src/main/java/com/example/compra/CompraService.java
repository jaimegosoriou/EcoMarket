package com.example.compra;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que gestiona las operaciones de negocio relacionadas con las compras.
 */
@Service
public class CompraService {

    private final CompraRepository compraRepository;

    /**
     * Constructor con inyección del repositorio.
     * No se necesita @Autowired cuando hay un solo constructor.
     */
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    /**
     * Retorna una lista con todas las compras existentes en la base de datos.
     */
    public List<Compra> listar() {
        return compraRepository.findAll();
    }

    /**
     * Guarda una nueva compra en la base de datos.
     * @param compra Objeto Compra a guardar
     * @return Compra guardada con ID generado
     */
    public Compra guardar(Compra compra) {
        return compraRepository.save(compra);
    }

    /**
     * Elimina una compra según su ID.
     * @param id Identificador de la compra a eliminar
     */
    public void eliminar(Long id) {
        compraRepository.deleteById(id);
    }

    /**
     * Busca una compra por su ID.
     * @param id Identificador de la compra
     * @return Compra encontrada o null si no existe
     */
    public Compra obtenerPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }
}
