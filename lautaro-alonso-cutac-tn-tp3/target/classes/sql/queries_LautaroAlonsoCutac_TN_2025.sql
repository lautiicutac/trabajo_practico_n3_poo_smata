-- ventas por cliente
SELECT c.nombre, c.apellido, c.email, ca.marca, ca.modelo, 
    v.precio_total, v.fecha_venta
FROM ventas v
JOIN clientes c ON v.id_clienteFK = c.id_cliente
JOIN camaras ca ON v.id_camaraFK = ca.id_camara
ORDER BY v.fecha_venta DESC;

-- total de ventas por marca
SELECT c.marca,
    COUNT(v.id_venta) cantidad_ventas,
    SUM(v.precio_total) total_vendido
FROM ventas v
JOIN camaras c ON v.id_camaraFK = c.id_camara
GROUP BY c.marca
ORDER BY total_vendido DESC;

-- camaras con poco stock (menor que 5)
SELECT marca, modelo, precio, stock
FROM camaras
WHERE stock < 5
ORDER BY stock ASC;

-- clientes con mas compras
SELECT 
    c.nombre, c.apellido, c.email,
    COUNT(v.id_venta) AS compras_realizadas,
    SUM(v.precio_total) AS total_gastado
FROM ventas v
JOIN clientes c ON v.id_clienteFK = c.id_cliente
GROUP BY c.id_cliente
ORDER BY total_gastado DESC
LIMIT 5;

-- camaras por tipo de camara 'Mirrorles'
SELECT marca, modelo, tipo_camara, megapixeles, precio
FROM camaras
WHERE tipo_camara = 'Mirrorless'
ORDER BY precio ASC;

-- cantidad de ventas e ingresos mensuales ordenados por año y mes (actual a mas an
SELECT 
    DATE_FORMAT(fecha_venta, '%m-%Y') AS mes,
    COUNT(id_venta) AS cantidad_ventas,
    SUM(precio_total) AS ingresos
FROM ventas
GROUP BY mes
ORDER BY mes DESC;