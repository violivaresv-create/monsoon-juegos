CREATE TABLE juegos (
    titulo VARCHAR(255) PRIMARY KEY,
    desarrollador VARCHAR(255) NOT NULL,
    editor VARCHAR(255) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    genero VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);