package cl.monsoon.juegos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.monsoon.juegos.model.Juegos;
@Repository
public interface JuegosRepository extends JpaRepository<Juegos, Long> {
    List<Juegos> findByTituloContainingIgnoreCase(String titulo);
    
}
