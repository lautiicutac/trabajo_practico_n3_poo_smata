package ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Camara;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.enums.TipoCamara;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_CamaraDAO;

@Repository
public class CamaraDAO implements I_CamaraDAO{
    private final DataSource dataSource;

    private static final String SQL_CREATE = 
        "INSERT INTO camaras (marca, modelo, tipo_de_camara, megapixeles, tipo_de_montura, lente_kit, fecha_de_lanzamiento, descripcion, precio, stock) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = 
        "SELECT * FROM camaras WHERE id_camara=?";
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM camaras";
    private static final String SQL_UPDATE = 
        "UPDATE camaras SET marca=?, modelo=?, tipo_de_camara=?, megapixeles=?, tipo_de_montura=?, lente_kit=?, fecha_de_lanzamiento=?, descripcion=?, precio=?, stock=? WHERE id_camara=?";
    private static final String SQL_DELETE =
        "DELETE FROM camaras WHERE id_camara=?" ;
    private static final String SQL_FIND_BY_MARCA = 
        "SELECT * FROM camaras WHERE marca= ?";

    public CamaraDAO(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    @Override
    public void create(Camara camara) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_CREATE, java.sql.Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, camara.getMarca());
            ps.setString(2, camara.getModelo());
            ps.setObject(3, camara.getTipoDeCamara().name());
            ps.setDouble(4, camara.getMegaPixeles());
            ps.setString(5, camara.getTipoDeMontura());
            ps.setString(6, camara.getLenteKit());
            ps.setString(7, camara.getFechaDeLanzamiento());
            ps.setString(8, camara.getDescripcion());
            ps.setDouble(9, camara.getPrecio());
            ps.setInt(10, camara.getStock());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    camara.setIdCamara(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Camara findById(int id) throws SQLException {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Camara> findAll() throws SQLException {
        List<Camara> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    @Override
    public int update(Camara camara) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, camara.getMarca());
            ps.setString(2, camara.getModelo());
            ps.setObject(3, camara.getTipoDeCamara().name());
            ps.setDouble(4, camara.getMegaPixeles());
            ps.setString(5, camara.getTipoDeMontura());
            ps.setString(6, camara.getLenteKit());
            ps.setString(7, camara.getFechaDeLanzamiento());
            ps.setString(8, camara.getDescripcion());
            ps.setDouble(9, camara.getPrecio());
            ps.setInt(10, camara.getStock());
            ps.setInt(11, camara.getIdCamara());
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

    @Override
    public List<Camara> findByMarca(String marca) throws SQLException {
        List<Camara> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_MARCA)) {
            ps.setString(1, marca);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    private Camara mapRow(ResultSet rs) throws SQLException{
        Camara camara = new Camara();
        camara.setIdCamara(rs.getInt("id_camara"));
        camara.setMarca(rs.getString("marca"));
        camara.setModelo(rs.getString("modelo"));
        camara.setTipoDeCamara(TipoCamara.valueOf(rs.getString("tipo_de_camara")));
        camara.setMegaPixeles(rs.getDouble("megapixeles"));
        camara.setTipoDeMontura(rs.getString("tipo_de_montura"));
        camara.setLenteKit(rs.getString("lente_kit"));
        camara.setFechaDeLanzamiento(rs.getString("fecha_de_lanzamiento"));
        camara.setDescripcion(rs.getString("descripcion"));
        camara.setPrecio(rs.getDouble("precio"));
        camara.setStock(rs.getInt("stock"));
        return camara;
    }
    
}
