package cl.monsoon.juegos.model;

import java.math.BigDecimal;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Modelo de un juego")
public class Juegos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador unico de un juego",
        example = "1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long id; 

    @Schema(
        description = "Titulo del juego",
        example = "Fortnite",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String titulo;

    private String desarrollador;

    private String editor;

    private Date fechaPublicacion;

    private String genero;

    private String descripcion;

    private BigDecimal precio;
    
}
