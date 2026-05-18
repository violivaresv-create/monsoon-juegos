package cl.monsoon.juegos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.service.JuegosService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/v0/juegos")
public class JuegosController {
    
    @Autowired
    private JuegosService juegosService;
    
    @PostMapping
    public void agregarJuego(@RequestBody Juegos juego) {
        juegosService.agregarJuego(juego);
    }
    
    @GetMapping
    public ResponseEntity<List<Juegos>> listaJuegos() {
        return ResponseEntity.ok(juegosService.listaJuegos());
    }

    @GetMapping("/juego")
    public ResponseEntity<List<Juegos>> buscarJuego(@RequestParam String titulo) {
        return ResponseEntity.ok(juegosService.buscarJuego(titulo));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Juegos> obtenerJuegoPorId(@PathVariable Long id) {
        return juegosService.obtenerJuegoPorId(id).map(juego -> ResponseEntity.ok(juego)).orElse(ResponseEntity.notFound().build());
    }
}
