package ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Camara;

public interface I_CamaraDAO {
    void create(Camara camara) throws SQLException;
    Camara findById(int id) throws SQLException;
    List<Camara> findAll() throws SQLException;
    int update(Camara camara) throws SQLException;
    int delete(int id) throws SQLException;
    List<Camara> findByMarca(String marca) throws SQLException;
}
