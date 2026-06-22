package cl.monsoon.juegos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
@Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
                        .info(new Info()
                                .title("Monsoon: Microservicio de juegos")
                                .version("0.0.2")
                                .description("microservicio de compra de juegos, visualizacion de catalogo y busqueda de juegos")
                    );
    }

}
