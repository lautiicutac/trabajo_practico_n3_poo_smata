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

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Cliente;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_ClienteDAO;

@Repository
public class ClienteDAO implements I_ClienteDAO{

    private final DataSource dataSource;

    private static final String SQL_CREATE =
        "INSERT INTO clientes (nombre, apellido, dni, email, telefono, direccion) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID =
        "SELECT * FROM clientes WHERE id_cliente=?";
    private static final String SQL_FIND_ALL = 
        "SELECT * FROM clientes";
    private static final String SQL_UPDATE =
        "UPDATE clientes SET nombre=?, apellido=?, dni=?, email=?, telefono=?, direccion=? WHERE id_cliente=?";
    private static final String SQL_DELETE = 
        "DELETE FROM clientes WHERE id_cliente=?";

    public ClienteDAO(DataSource dataSource){
        this.dataSource= dataSource;
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if (keys.next()) {
                    cliente.setIdCliente(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Cliente findById(int id) throws SQLException {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
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
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
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
    public int update(Cliente cliente) throws SQLException {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion());
            ps.setInt(7, cliente.getIdCliente());
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

    private Cliente mapRow(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setDni(rs.getString("dni"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDireccion(rs.getString("direccion"));
        return cliente;
    }    
}
