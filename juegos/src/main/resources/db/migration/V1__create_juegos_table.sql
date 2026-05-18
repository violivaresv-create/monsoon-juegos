CREATE TABLE juegos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(40) NOT NULL,
    desarrollador VARCHAR(25) NOT NULL,
    editor VARCHAR(25) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    genero VARCHAR(30) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);