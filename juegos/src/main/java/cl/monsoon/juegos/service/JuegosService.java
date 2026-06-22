package cl.monsoon.juegos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.repository.JuegosRepository;

@Service
public class JuegosService {

    @Autowired
    private JuegosRepository juegosRepository;

    public Juegos agregarJuego(Juegos juego){
        return juegosRepository.save(juego);
    }

    public List<Juegos> listaJuegos(){
        return juegosRepository.findAll();
    }

    public List<Juegos> buscarJuego(String titulo){
        return juegosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Optional<Juegos> obtenerJuegoPorId(Long id) {
        return juegosRepository.findById(id);
    }
    public boolean eliminarJuego(Long id) {
        juegosRepository.deleteById(id);
        return true;
    }

    public Juegos actualizarJuego(Juegos juego) {
        return juegosRepository.save(juego);
    }
}
