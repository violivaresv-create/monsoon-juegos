package cl.monsoon.juegos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.repository.JuegosRepository;

@Service
public class JuegosService {

    @Autowired
    private JuegosRepository juegosRepository;

    public void agregarJuego(Juegos juego){
        juegosRepository.save(juego);
    }
}
