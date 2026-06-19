package cl.monsoon.juegos.controller;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import cl.monsoon.juegos.config.WebMvcLinkBuilder;
import cl.monsoon.juegos.model.Juegos;
import cl.monsoon.juegos.service.JuegosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.Link;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("api/v0/juegos")
public class JuegosController {
    
    @Autowired
    private JuegosService juegosService;

    @Autowired
    private WebMvcLinkBuilder assembler;


    @Operation(summary = "Agregar juego" , description = "Agrega un nuevo juego a la base de datos")
    @PostMapping
    public ResponseEntity<Juegos> agregarJuego(@RequestBody Juegos juego) {
       boolean resultado = juegosService.agregarJuego(juego);
       if(resultado) return ResponseEntity.status(HttpStatus.CREATED).body(juego);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    
    @Operation(summary = "Listar juegos" , description = "Lista todos los juegos disponibles")
    @GetMapping
    public ResponseEntity<List<Juegos>> listaJuegos() {
        List<Juegos> juegos = juegosService.listaJuegos();
        if (juegos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(juegos);
    }
    @Operation(summary = "Buscar juego" , description = "Busca un juego por su nombre")
    @GetMapping("/juego")
    public ResponseEntity<List<Juegos>> buscarJuego(@RequestParam String titulo) {
        List<Juegos> juegos = juegosService.buscarJuego(titulo);
        if (juegos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return ResponseEntity.status(HttpStatus.OK).body(juegos);
    }
    
    @Operation(summary = "Buscar juego por id" , description = "Busca un juego en base a su id")
    @GetMapping("/{id}")
    public EntityModel<ResponseEntity<Juegos>> obtenerJuegoPorId(@PathVariable Long id) {
        return juegosService.obtenerJuegoPorId(id).map(juego -> {EntityModel<Juegos> modelo = EntityModel.of(juego);¿modelo.add(linkTo(methodOn(this.getClass()).obtenerJuegoPorId(id)).withSelfRel());
                return ResponseEntity.ok(modelo);}).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Eliminar juego por id" , description = "Elimina un juego en base a su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable Long id) {
        juegosService.eliminarJuego(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Operation(summary = "Actualizar juego" , description = "Actualiza un juego en base a su id")
    @PutMapping("/{id}")
    public ResponseEntity<Juegos> actualizarJuego(@PathVariable Long id, @RequestBody Juegos juego) {
        return juegosService.obtenerJuegoPorId(id).map(j -> {
            juego.setId(id);
            juegosService.actualizarJuego(juego);
            return ResponseEntity.status(HttpStatus.OK).body(juego);
        }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    private EntityModel<Juegos> addLinks(Juegos juegos) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JuegosController.class).obtenerJuegoPorId(juegos.getId())).withSelfRel();
        Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(JuegosController.class).listaJuegos()).withRel("juegos");
        return EntityModel.of(juegos, selfLink, allLink);
    }
}
