package com.example.compra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private CompraService compraService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Compra c1 = new Compra();
        c1.setId(1L);
        c1.setFecha(LocalDate.now());
        c1.setTotal(100.0);

        Compra c2 = new Compra();
        c2.setId(2L);
        c2.setFecha(LocalDate.now());
        c2.setTotal(200.0);

        when(compraRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Compra> compras = compraService.listar();

        assertEquals(2, compras.size());
        assertEquals(100.0, compras.get(0).getTotal());
        assertEquals(200.0, compras.get(1).getTotal());
        verify(compraRepository, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        Compra compra = new Compra();
        compra.setFecha(LocalDate.now());
        compra.setTotal(150.0);

        Compra saved = new Compra();
        saved.setId(1L);
        saved.setFecha(compra.getFecha());
        saved.setTotal(compra.getTotal());

        when(compraRepository.save(compra)).thenReturn(saved);

        Compra result = compraService.guardar(compra);

        assertNotNull(result.getId());
        assertEquals(150.0, result.getTotal());
        verify(compraRepository, times(1)).save(compra);
    }

    @Test
    void testEliminar() {
        Long id = 1L;

        doNothing().when(compraRepository).deleteById(id);

        compraService.eliminar(id);

        verify(compraRepository, times(1)).deleteById(id);
    }

    @Test
    void testObtenerPorId() {
        Long id = 1L;
        Compra compra = new Compra();
        compra.setId(id);
        compra.setFecha(LocalDate.now());
        compra.setTotal(12345.0);

        when(compraRepository.findById(id)).thenReturn(Optional.of(compra));

        Compra result = compraService.obtenerPorId(id);

        assertNotNull(result);
        assertEquals(12345.0, result.getTotal());
        verify(compraRepository, times(1)).findById(id);
    }
}
