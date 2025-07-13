package ar.org.centro8.poo.tp3_venta_camaras.java.models.entities;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.enums.TipoCamara;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Camara {
    private int idCamara;
    private String marca;
    private String modelo;
    private TipoCamara tipoDeCamara;
    private double megaPixeles;
    private String tipoDeMontura;
    private String lenteKit;
    private String fechaDeLanzamiento;
    private String descripcion;
    private double precio;
    private int stock;
}
