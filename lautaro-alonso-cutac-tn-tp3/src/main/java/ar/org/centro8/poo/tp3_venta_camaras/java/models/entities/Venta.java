package ar.org.centro8.poo.tp3_venta_camaras.java.models.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venta {
    private int idVenta;
    private int idClienteFK;
    private int idCamaraFK;
    private double precioTotal;
    private LocalDate fechaVenta;
}
