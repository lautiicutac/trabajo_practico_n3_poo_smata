package ar.org.centro8.poo.tp3_venta_camaras.java.tests;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestConnection {
    public static void main(String[] args) {
        Properties props = new Properties(); //cargar application properties desde su ruta y guardarlo en un nuevo objeto del tipo Properties (carga el fichero de configuracion)

        //InputStream = flujo de bytes
        try (InputStream in = TestConnection.class //.class para obtener el objeto de esta clase
                                            .getClassLoader() //obtiene el "cargador de clases y recursos"
                                            .getResourceAsStream("application.properties")){ //busca el archivo pasado por parametros y lo devuelve transformado en un flujo de bytes 
            if (in == null) {
                System.err.println("No se encontró el application.properties.");
                return;
            }
            props.load(in);
            //cargar todas las propiedades con el props (combinaciones clave-valor)
        } catch (Exception e) {
            System.err.println("Ha sucedido un error al cargar las properties: " + e.getMessage());
        }

        //configurar el pool de conexiones de HikariCP
        HikariConfig config = new HikariConfig();
        //la URL
        config.setJdbcUrl(props.getProperty("spring.datasource.url"));
        //el user
        config.setUsername(props.getProperty("spring.datasource.username"));
        //el password
        config.setPassword(props.getProperty("spring.datasource.password"));

        //crear el DataSource con el pool de conexiones y probar la conexion
        try (HikariDataSource ds = new HikariDataSource(config);
                Connection conn = ds.getConnection()) {
            if (conn.isValid(2)) {
                System.out.println("Conectado con exito a: " + conn.getMetaData().getURL());
                //getMetaData obtiene la informacion de la conexion
            } else {
                System.err.println("La conexion no es valida.");
            }
        } catch (Exception e) {
            System.err.println("No se ha podido conectar. " + e.getMessage());
        }
    }
}
