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
import java.util.Arrays;
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
    public void testListaJuegos() {
        List<Juegos> juegosList = Arrays.asList(juego);
        when(juegosService.listaJuegos()).thenReturn(juegosList);

        ResponseEntity<List<Juegos>> result = juegosController.listaJuegos();

        assertNotNull(result);
        assertEquals(1, result.getBody().size());
        assertEquals("Juego test", result.getBody().get(0).getTitulo());
        verify(juegosService, times(1)).listaJuegos();
    }


    @Test
    public boolean testAgregarJuego() {
        when(juegosService.agregarJuego(any(Juegos.class))).thenReturn(true);

        ResponseEntity<Juegos> result = juegosController.agregarJuego(juego);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(juego, result.getBody());
        verify(juegosService, times(1)).agregarJuego(any(Juegos.class));
        return true;
    }

}
