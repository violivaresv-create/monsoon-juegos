package cl.monsoon.juegos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.service.JuegosService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v2/Juegos")
public class JuegosController {
    
    @Autowired
    private JuegosService juegosService;
    
    @PostMapping
    public void agregarJuego(@RequestBody Juegos juego) {
        juegosService.agregarJuego(juego);
    }
    

}
