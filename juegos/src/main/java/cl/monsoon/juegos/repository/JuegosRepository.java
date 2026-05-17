package cl.monsoon.juegos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.monsoon.juegos.model.Juegos;

public interface JuegosRepository extends JpaRepository<Juegos, Long> {

    List<Juegos> findByTituloContainingIgnoreCase(String titulo);
    
}
