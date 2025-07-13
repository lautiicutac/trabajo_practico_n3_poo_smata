package ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Venta;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_VentaDAO;

@Repository
public class VentaDAO implements I_VentaDAO{

    private final DataSource dataSource;

    private static final String SQL_CREATE = 
        "INSERT INTO ventas (id_clienteFK, id_camaraFK, precio_total, fecha_venta) VALUES (?,?,?,?)";
    private static final String SQL_FIND_BY_ID = 
        "SELECT * FROM ventas WHERE id_venta=?";
    private static final String SQL_FIND_ALL =
        "SELECT * FROM ventas";
    private static final String SQL_UPDATE =
        "UPDATE ventas SET id_clienteFK=?, id_camaraFK=?, precio_total=?, fecha_Venta=? WHERE id_venta=?";
    private static final String SQL_DELETE = 
        "DELETE FROM ventas WHERE id_venta=?";

    public VentaDAO(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Override
    public void create(Venta venta) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, venta.getIdClienteFK());
            ps.setInt(2, venta.getIdCamaraFK());
            ps.setDouble(3, venta.getPrecioTotal()); //con name() obtengo el nombre de la constante
            ps.setObject(4, venta.getFechaVenta());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if(keys.next()){
                    venta.setIdVenta(keys.getInt(1));
                }
            } 
        } 
    }

    @Override
    public Venta findById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Venta> findAll() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    @Override
    public int update(Venta venta) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, venta.getIdClienteFK());
            ps.setInt(2, venta.getIdCamaraFK());
            ps.setDouble(3, venta.getPrecioTotal()); //con name() obtengo el nombre de la constante
            ps.setObject(4, venta.getFechaVenta());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        } 
    }

    private Venta mapRow(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setIdVenta(rs.getInt("id_venta"));
        venta.setIdClienteFK(rs.getInt("id_clienteFK"));
        venta.setIdCamaraFK(rs.getInt("id_camaraFK"));
        venta.setPrecioTotal(rs.getDouble("precio_total"));
        venta.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
        return venta;
    }
}
