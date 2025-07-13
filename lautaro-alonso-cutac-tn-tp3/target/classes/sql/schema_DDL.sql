DROP DATABASE IF EXISTS camaras_ya_mismo;
CREATE DATABASE camaras_ya_mismo;
USE camaras_ya_mismo;

DROP TABLE IF EXISTS camaras;
CREATE TABLE camaras (
    id_camara INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    tipo_de_camara ENUM('DSLR', 'MIRRORLESS', 'COMPACTA') NOT NULL,
    megapixeles DOUBLE NOT NULL,
    tipo_de_montura VARCHAR(50),
    lente_kit VARCHAR(50),
    fecha_de_lanzamiento VARCHAR(25),
    descripcion VARCHAR(150),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    email VARCHAR(45) NOT NULL UNIQUE,
    telefono VARCHAR(45) UNIQUE,
    direccion VARCHAR(150) NOT NULL
);

DROP TABLE IF EXISTS ventas;
CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_clienteFK INT NOT NULL,
    id_camaraFK INT NOT NULL,
    precio_total DOUBLE NOT NULL,
    fecha_venta DATE NOT NULL
);

ALTER TABLE ventas ADD CONSTRAINT fk_venta_cliente
FOREIGN KEY (id_clienteFK) REFERENCES clientes(id_cliente);

ALTER TABLE ventas ADD CONSTRAINT fk_venta_camara
FOREIGN KEY (id_camaraFK) REFERENCES camaras(id_camara);