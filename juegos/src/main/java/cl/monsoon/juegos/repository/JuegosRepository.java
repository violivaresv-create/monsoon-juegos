package cl.monsoon.juegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.monsoon.juegos.model.Juegos;

public interface JuegosRepository extends JpaRepository<Juegos, String>{
    
}
