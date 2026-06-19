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

    @Schema(
        description = "Desarrollador del juego",
        example = "Epic Games",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String desarrollador;

    @Schema(
        description = "Editor del juego",
        example = "Epic Games",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String editor;

    @Schema(
        description = "Fecha de publicación del juego",
        example = "2020-01-01",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Date fechaPublicacion;

    @Schema(
        description = "Género del juego",
        example = "Battle Royale",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String genero;

    @Schema(
        description = "Descripción del juego",
        example = "Juego de lucha en línea",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String descripcion;


    @Schema(
        description = "Precio del juego",
        example = "0.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal precio;
    
}
