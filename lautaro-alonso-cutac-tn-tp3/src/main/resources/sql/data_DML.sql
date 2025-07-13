INSERT INTO camaras (marca, modelo, tipo_de_camara, megapixeles, tipo_de_montura, lente_kit, fecha_de_lanzamiento, descripcion, precio, stock) VALUES
('Canon', 'EOS R5', 'Mirrorless', 45.0, 'RF', 'RF 24-105mm f/4L', '2020-07-09', 'Cámara profesional full frame con 8K RAW', 3799999.99, 5),
('Sony', 'A7 IV', 'Mirrorless', 33.0, 'E-mount', 'FE 28-70mm f/3.5-5.6', '2021-10-21', 'Excelente relación calidad-precio', 2499999.99, 8),
('Nikon', 'D850', 'DSLR', 45.7, 'F-mount', 'AF-S 24-120mm f/4G', '2017-07-25', 'DSLR profesional con sensor de alto rango', 2999999.99, 3),
('Fujifilm', 'X-T4', 'Mirrorless', 26.1, 'X-mount', 'XF 18-55mm f/2.8-4', '2020-02-26', 'Ideal para foto y video', 1699999.99, 7),
('Panasonic', 'Lumix GH6', 'Mirrorless', 25.2, 'Micro Four Thirds', 'Lumix G 12-60mm f/3.5-5.6', '2022-02-22', 'Enfoque en video 4K/120p', 2199999.99, 4),
('Olympus', 'OM-D E-M10 Mark IV', 'Mirrorless', 20.3, 'Micro Four Thirds', 'M.Zuiko 14-42mm f/3.5-5.6', '2020-08-04', 'Compacta para viajes', 999999.99, 10),
('Canon', 'EOS Rebel T8i', 'DSLR', 24.1, 'EF-S', 'EF-S 18-55mm f/4-5.6', '2020-02-13', 'Para principiantes', 899999.99, 12),
('Sony', 'ZV-1', 'Compacta', 20.1, 'Fija', '24-70mm f/1.8-2.8 (integrado)', '2020-05-26', 'Compacta para vlogging', 749999.99, 15),
('Nikon', 'Z50', 'Mirrorless', 20.9, 'Z-mount', 'Nikkor Z 16-50mm f/3.5-6.3', '2019-10-10', 'Mirrorless de entrada', 999996.99, 6),
('Fujifilm', 'X100V', 'Compacta', 26.1, 'Fija', '23mm f/2 (integrado)', '2020-02-05', 'Premium compacta', 1399999.99, 2);



INSERT INTO clientes (nombre, apellido, dni, email, telefono, direccion) VALUES
('Juan', 'Pérez', '12345678', 'juan.perez@email.com', '1122334455', 'Av. Corrientes 5705'),
('María', 'Gómez', '23456789', 'maria.gomez@email.com', '1155667788', 'Av. Forest 1573'),
('Carlos', 'López', '34567890', 'carlos.lopez@email.com', '1144556677', 'Pringles 775'),
('Ana', 'Martínez', '45678901', 'ana.martinez@email.com', '1166778899', 'Humboldt 344'),
('Luis', 'Rodríguez', '56789012', 'luis.rodriguez@email.com', '1133445566', 'Fitz Roy 412'),
('Sofía', 'Fernández', '67890123', 'sofia.fernandez@email.com', '1188990011', 'Av. Libertador 3580'),
('Pedro', 'García', '78901234', 'pedro.garcia@email.com', null, 'Av. Corrientes 3245'),
('Laura', 'Díaz', '89012345', 'laura.diaz@email.com', '1155667688', 'Av. Santa Fe 2456'),
('Diego', 'Sánchez', '90123456', 'diego.sanchez@email.com', null, 'Av. Cabildo 2345'),
('Marta', 'Torres', '01234567', 'marta.torres@email.com', '1166778599', 'Av. Rivadavia 5678');

INSERT INTO ventas (id_clienteFK, id_camaraFK, precio_total, fecha_venta) VALUES
(1, 3, 2999999.99, '2025-01-15'),
(2, 1, 3799999.99, '2025-01-20'),
(3, 5, 2199999.99, '2025-03-10'),
(4, 2, 2499999.99, '2025-03-18'),
(5, 7, 899999.99, '2025-04-05'),
(6, 4, 1699999.99, '2025-04-22'),
(7, 8, 749999.99, '2025-05-03'),
(8, 6, 999999.99, '2025-05-15'),
(9, 9, 999996.99, '2025-06-01'),
(10, 10, 1399999.99, '2025-06-10'),
(1, 2, 2499999.99, '2024-11-18'),
(3, 1, 3799999.99, '2024-11-05'),
(5, 3, 2999999.99, '2024-12-20'),
(7, 4, 1699999.99, '2024-12-02'),
(9, 5, 2199999.99, '2024-12-15');