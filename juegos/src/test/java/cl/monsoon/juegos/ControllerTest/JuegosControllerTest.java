package cl.monsoon.juegos.ControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.monsoon.juegos.controller.JuegosController;
import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.service.JuegosService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JuegosControllerTest {

    @Mock
    private JuegosService juegosService;

    @InjectMocks
    private JuegosController juegosController;

    private Juegos juego;
    
    @BeforeEach
    public void setUp() {
        juego = new Juegos();
        juego.setId(1L);
        juego.setTitulo("Fortnite");
        juego.setDesarrollador("Epic Games");
        juego.setEditor("Epic Games");
        juego.setFechaPublicacion(Date.valueOf("2020-01-01"));
        juego.setGenero("Battle Royale");
        juego.setPrecio((BigDecimal.valueOf(0.00)));
        juego.setDescripcion("Consigue ser el ultimo en pie");
    }

    @Test
    public void testAgregarJuego() {
        when(juegosService.agregarJuego(any(Juegos.class))).thenReturn(juego);

        ResponseEntity<EntityModel<Juegos>> result = juegosController.agregarJuego(juego);
        
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(juegosService, times(1)).agregarJuego(juego);
    }


    @Test
    public void testAgregarJuegoFallido() {
        when(juegosService.agregarJuego(any(Juegos.class))).thenReturn(null);

        ResponseEntity<EntityModel<Juegos>> result = juegosController.agregarJuego(juego);
        
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(juegosService, times(1)).agregarJuego(juego);
    }

    @Test
    public void testMostrarTodosLosJuegosDisponibles() {
        when(juegosService.listaJuegos()).thenReturn(List.of(juego));

        ResponseEntity<List<EntityModel<Juegos>>> result = juegosController.listaJuegos();
        
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isEmpty());
        verify(juegosService, times(1)).listaJuegos();
    }

    @Test
    public void testBuscarJuegoPorTitulo() {
        when(juegosService.buscarJuego("Fortnite")).thenReturn(List.of(juego));

        ResponseEntity<List<EntityModel<Juegos>>> result = juegosController.buscarJuego("Fortnite");
        
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody().isEmpty());
        verify(juegosService, times(1)).buscarJuego("Fortnite");
    }

    @Test
    public void testBuscarJuegoPorTituloNoEncontrado() {
        when(juegosService.buscarJuego("JuegoInexistente")).thenReturn(List.of());

        ResponseEntity<List<EntityModel<Juegos>>> result = juegosController.buscarJuego("JuegoInexistente");
        
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(juegosService, times(1)).buscarJuego("JuegoInexistente");
    }

    @Test
    public void testObtenerJuegoPorId() {
        when(juegosService.obtenerJuegoPorId(1L)).thenReturn(java.util.Optional.of(juego));

        ResponseEntity<EntityModel<Juegos>> result = juegosController.obtenerJuegoPorId(1L);
        
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(juegosService, times(1)).obtenerJuegoPorId(1L);
    }

    @Test
    public void testObtenerJuegoPorIdNoEncontrado() {
        when(juegosService.obtenerJuegoPorId(2L)).thenReturn(java.util.Optional.empty());

        ResponseEntity<EntityModel<Juegos>> result = juegosController.obtenerJuegoPorId(2L);
        
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(juegosService, times(1)).obtenerJuegoPorId(2L);
    }

    @Test
    public void testEliminarJuego() {
        when(juegosService.eliminarJuego(1L)).thenReturn(true);

        ResponseEntity<EntityModel<Juegos>> result = juegosController.eliminarJuego(1L);
        
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(juegosService, times(1)).eliminarJuego(1L);
    }

    @Test
    public void testEliminarJuegoFallido() {
        when(juegosService.eliminarJuego(2L)).thenReturn(false);

        ResponseEntity<EntityModel<Juegos>> result = juegosController.eliminarJuego(2L);
        
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(juegosService, times(1)).eliminarJuego(2L);
    }

    @Test
    public void testActualizarJuego() {
        doNothing().when(juegosService).actualizarJuego(any(Juegos.class));

        ResponseEntity<EntityModel<Juegos>> result = juegosController.actualizarJuego(1L, juego);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(juegosService, times(1)).actualizarJuego(any(Juegos.class));
    }

}
