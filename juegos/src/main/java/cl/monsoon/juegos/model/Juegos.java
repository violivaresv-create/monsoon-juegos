package cl.monsoon.juegos.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Juegos {
    
    @Id
    private String titulo;

    private String desarrollador;

    private String editor;

    private Date fechaPublicacion;

    private String genero;

    private String descripcion;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double precio;
    
}
