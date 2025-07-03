package com.example.compra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompraServiceMockitoTest {

    private CompraRepository compraRepository;
    private CompraService compraService;

    @BeforeEach
    void setUp() {
        // Simula el comportamiento del repositorio con Mockito
        compraRepository = mock(CompraRepository.class);
        compraService = new CompraService(compraRepository);
    }

    @Test
    void testGuardarCompra() {
        Compra compra = new Compra();
        compra.setFecha(LocalDate.now());
        compra.setTotal(9900.0);
        compra.setId(1L); // Simulación de ID asignado

        when(compraRepository.save(any(Compra.class))).thenReturn(compra);

        Compra resultado = compraService.guardar(compra);

        assertNotNull(resultado.getId());
        assertEquals(9900.0, resultado.getTotal());
        verify(compraRepository, times(1)).save(compra);
    }

    @Test
    void testListarCompras() {
        Compra c1 = new Compra();
        c1.setId(1L);
        c1.setFecha(LocalDate.now());
        c1.setTotal(5000.0);

        Compra c2 = new Compra();
        c2.setId(2L);
        c2.setFecha(LocalDate.now());
        c2.setTotal(10000.0);

        List<Compra> listaSimulada = Arrays.asList(c1, c2);

        when(compraRepository.findAll()).thenReturn(listaSimulada);

        List<Compra> resultado = compraService.listar();

        assertEquals(2, resultado.size());
        assertEquals(5000.0, resultado.get(0).getTotal());
        assertEquals(10000.0, resultado.get(1).getTotal());
        verify(compraRepository, times(1)).findAll();
    }

    @Test
    void testEliminarCompra() {
        Long idCompra = 1L;

        doNothing().when(compraRepository).deleteById(idCompra);

        compraService.eliminar(idCompra);

        verify(compraRepository, times(1)).deleteById(idCompra);
    }

    @Test
    void testObtenerPorId() {
        Compra compra = new Compra();
        compra.setId(1L);
        compra.setFecha(LocalDate.now());
        compra.setTotal(4500.0);

        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));

        Compra resultado = compraService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(4500.0, resultado.getTotal());
        verify(compraRepository, times(1)).findById(1L);
    }
}
