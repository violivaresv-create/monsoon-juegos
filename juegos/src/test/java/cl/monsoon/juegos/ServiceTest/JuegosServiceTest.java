package cl.monsoon.juegos.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.repository.JuegosRepository;
import cl.monsoon.juegos.service.JuegosService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JuegosServiceTest {

    @Mock
    private JuegosRepository juegosRepository;

    @InjectMocks
    private JuegosService juegosService;

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
        when(juegosRepository.save(any(Juegos.class))).thenReturn(juego);

        juegosService.agregarJuego(juego);
    
        verify(juegosRepository, times(1)).save(juego);
    }

    @Test
    public void testListaJuegos() {
        List<Juegos> juegos = Arrays.asList(juego);
        when(juegosRepository.findAll()).thenReturn(juegos);

        List<Juegos> result = juegosService.listaJuegos();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(juego, result.get(0));
        verify(juegosRepository, times(1)).findAll();
    }
    
    @Test
    public void testBuscarJuego() {
        List<Juegos> juegos = Arrays.asList(juego);
        when(juegosRepository.findByTituloContainingIgnoreCase("fortnite")).thenReturn(juegos);

        List<Juegos> result = juegosService.buscarJuego("fortnite");
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(juego, result.get(0));
        verify(juegosRepository, times(1)).findByTituloContainingIgnoreCase("fortnite");
    }

    @Test
    public void testObtenerJuegoPorId() {
        when(juegosRepository.findById(1L)).thenReturn(java.util.Optional.of(juego));

        Optional<Juegos> result = juegosService.obtenerJuegoPorId(1L);
        
        assertNotNull(result);
        assertEquals(juego, result.get());
        verify(juegosRepository, times(1)).findById(1L);
    }

    @Test
    public void testObtenerJuegoPorIdNoEncontrado() {
        when(juegosRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Optional<Juegos> result = juegosService.obtenerJuegoPorId(1L);
        
        assertNull(result);
        verify(juegosRepository, times(1)).findById(1L);
    }

    @Test
    public void testEliminarJuego() {
        doNothing().when(juegosRepository).deleteById(1L);

        juegosService.eliminarJuego(1L);
        
        verify(juegosRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testActualizarJuego() {
        when(juegosRepository.save(any(Juegos.class))).thenReturn(juego);

        juegosService.actualizarJuego(juego);

        verify(juegosRepository, times(1)).save(juego);
    }
}
