package ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Venta;

public interface I_VentaDAO {
    void create(Venta venta) throws SQLException;
    Venta findById(int id) throws SQLException;
    List<Venta> findAll() throws SQLException;
    int update(Venta venta) throws SQLException;
    int delete(int id) throws SQLException;
}
