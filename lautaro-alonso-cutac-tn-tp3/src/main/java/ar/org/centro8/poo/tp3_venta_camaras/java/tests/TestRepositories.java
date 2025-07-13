package ar.org.centro8.poo.tp3_venta_camaras.java.tests;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Camara;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Cliente;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.entities.Venta;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.enums.TipoCamara;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.CamaraDAO;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.ClienteDAO;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.VentaDAO;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_CamaraDAO;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_ClienteDAO;
import ar.org.centro8.poo.tp3_venta_camaras.java.models.repositories.interfaces.I_VentaDAO;

@SpringBootApplication(scanBasePackages = "ar.org.centro8.poo.tp3_venta_camaras.java")

public class TestRepositories {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(TestRepositories.class,args);) {
            I_CamaraDAO camaraDAO = context.getBean(CamaraDAO.class);
            I_ClienteDAO clienteDAO = context.getBean(ClienteDAO.class);
            I_VentaDAO ventaDAO = context.getBean(VentaDAO.class);



            // TEST DE CAMARAS
            //EXPERIMENTO TEST 001: Crear una nueva camara
            System.out.println("\n---   Test 1: Creando una nueva cámara... \n");
            Camara camaraNueva = new Camara(0, "Canon", "R7", TipoCamara.MIRRORLESS, 23.2, "RF", "18-155mm", "Febrero 2024", "Cámara Mirrorless con crop x1.6", 3266000, 29);
            camaraDAO.create(camaraNueva);
            if (camaraNueva.getIdCamara()>0) {
                System.out.println("\n ## Cámara correctamente añadida al inventario. Id: " + camaraNueva.getIdCamara() + "\n");
                System.out.println(camaraNueva + "\n");
            }else{
                System.err.println(" ||| ERROR - No se pudo crear el objeto cámara en el sistema. ||| ");
            }

            //EXPERIMENTO TEST 002: Buscar una camara por ID (EXISTENTE)
            System.out.println("\n--- Test 2: Buscando cámara por ID " + camaraNueva.getIdCamara() + "... .. .\n");
            Camara camaraExistente = camaraDAO.findById(camaraNueva.getIdCamara());
            if(camaraExistente!=null){
                System.out.println(" ## Cámara encontrada: " + camaraExistente + "\n");
            } else {
                System.err.println(" ||| No se encontró ninguna cámara con el ID " + camaraNueva.getIdCamara() + " ||| \n");
            }   

            //EXPERIMENTO TEST 003: Intentando buscar una camara por ID (INEXISTENTE)
            System.out.println("\n--- Test 3: Buscando cámara con el ID inexistente 990\n ");
            Camara camaraInexistente = camaraDAO.findById(990);
            if (camaraInexistente != null) {
                System.out.println(" ## La camara se encuentra en el inventario: " + camaraInexistente);
            }else{
                System.err.println(" ||| ERROR - No existe ninguna cámara con ID 990 ||| \n");
            }

            //EXPERIMENTO TEST 004: Actualizando una camara
            System.out.println("\n--- Test 4: Actualizando la cámara " +  camaraNueva.getIdCamara() + "...\n ");
            camaraNueva.setLenteKit("Solo Body");
            camaraNueva.setPrecio(2599000);
            camaraNueva.setStock(58);
            int filasAfectadas = camaraDAO.update(camaraNueva);
            if (filasAfectadas==1) {
                System.out.println(" ## La cámara "+ camaraNueva.getIdCamara() + " ha sido actualizada.");
                System.out.println(" ## Comprobando actualización: " + camaraDAO.findById(camaraNueva.getIdCamara()) + "\n");
            } else {
                System.err.println("|n ||| ERROR - No se ha podido actualizar la cámara ||| \n");
            }

            //EXPERIMENTO TEST 005: Listar todas las cámaras
            System.out.println("\n--- Test 5: Listando todas las cámaras...\n ");
            List<Camara> todasLasCamaras = camaraDAO.findAll();
            if (!todasLasCamaras.isEmpty()) {
                System.out.println(" ## Cámaras encontradas: " + todasLasCamaras.size() );
                todasLasCamaras.forEach(c -> System.out.println("    > " + c) );
                System.out.println("\n");
            } else {
                System.err.println(" ||| No se encontró ninguna cámara. ||| \n");
            }

            //EXPERIMENTO TEST 006: Eliminar una cámara
            System.out.println("\n--- Test 6: Eliminando la cámara " + camaraNueva.getIdCamara() + "\n ");
            int filasAfectadasDelete = camaraDAO.delete(camaraNueva.getIdCamara());
            if (filasAfectadasDelete==1) {
                System.out.println(" ## Cámara " + camaraNueva.getIdCamara() + " eliminada efectivamente.\n");
                System.out.println("## Comprobando la eliminación..." + camaraDAO.findById(camaraNueva.getIdCamara()) + "\n");
            }else{
                System.err.println(" ||| ERRORE - No se pudo eliminar la cámara ||| \n");
            }

            //EXPERIMENTO TEST 007: Buscar por marca
            System.out.println("\n--- Test 7: Buscar cámara por marca (Sony)\n");

            List<Camara> sonys = camaraDAO.findByMarca("Sony");
            if (!sonys.isEmpty()) {
                System.out.println(" ## Se encontraron " + sonys.size() +            " cámaras Sony:");
                sonys.forEach(c -> System.out.println("     > " + c.getMarca() + " - " + c.getModelo() + " (" + c.getTipoDeCamara() + ")"));
            } else {
                System.err.println("||| No se encontraron cámaras Sony |||");
            }


            // TEST DE CLIENTES !!!
            // Test Clientes 08

            System.out.println("\n--- Test 8: Creando un nuevo cliente... \n");
            Cliente clienteNuevo = new Cliente(0, "Juan", "Medina", "12470055", "juanme_donna@mail.com", "1528375455", "Esperanza 551");
            clienteDAO.create(clienteNuevo);
            if (clienteNuevo.getIdCliente() > 0) {
                System.out.println("\n ## Cliente correctamente registrado. Id: " + clienteNuevo.getIdCliente() + "\n");
                System.out.println(clienteNuevo + "\n");
            } else {
                System.err.println(" ||| ERROR - No se pudo registrar el cliente ||| ");
            }

            // TEST 09: Buscar un cliente por ID (EXISTENTE)
            System.out.println("\n--- Test 9: Buscando cliente por ID " + clienteNuevo.getIdCliente() + "... .. .\n");
            Cliente clienteExistente = clienteDAO.findById(clienteNuevo.getIdCliente());
            if (clienteExistente != null) {
                System.out.println(" ## Cliente encontrado: " + clienteExistente + "\n");
            } else {
                System.err.println(" ||| No se encontró ningún cliente con el ID " + clienteNuevo.getIdCliente() + " ||| \n");
            }

            // TEST 10: Buscar un cliente por ID (INEXISTENTE)
            System.out.println("\n--- Test 10: Buscando cliente con ID inexistente 999\n ");
            Cliente clienteInexistente = clienteDAO.findById(999);
            if (clienteInexistente != null) {
                System.out.println(" ## El cliente se encuentra registrado: " + clienteInexistente);
            } else {
                System.err.println(" ||| ERROR - No existe ningún cliente con ID 999 ||| \n");
            }

            // TEST 11: Actualizar un cliente
            System.out.println("\n--- Test 11: Actualizando el cliente " + clienteNuevo.getIdCliente() + "...\n ");
            clienteNuevo.setEmail("nuevoemail@mail.com");
            clienteNuevo.setTelefono("19565585");
            int filasAfectadasCliente = clienteDAO.update(clienteNuevo);
            if (filasAfectadasCliente == 1) {
                System.out.println(" ## Cliente " + clienteNuevo.getIdCliente() + " ha sido actualizado.");
                System.out.println(" ## Comprobando actualización: " + clienteDAO.findById(clienteNuevo.getIdCliente()) + "\n");
            } else {
                System.err.println("\n ||| ERROR - No se ha podido actualizar el cliente ||| \n");
            }

            // TEST 12: Listar todos los clientes
            System.out.println("\n--- Test 12: Listando todos los clientes...\n ");
            List<Cliente> todosLosClientes = clienteDAO.findAll();
            if (!todosLosClientes.isEmpty()) {
                System.out.println(" ## Clientes registrados: " + todosLosClientes.size());
                todosLosClientes.forEach(c -> System.out.println("    > " + c));
                System.out.println("\n");
            } else {
                System.err.println(" ||| No se encontró ningún cliente registrado. ||| \n");
            }

            // TEST 13: Eliminar un cliente
            System.out.println("\n--- Test 13: Eliminando el cliente " + clienteNuevo.getIdCliente() + "\n ");
            int filasAfectadasDeleteCliente = clienteDAO.delete(clienteNuevo.getIdCliente());
            if (filasAfectadasDeleteCliente == 1) {
                System.out.println(" ## Cliente " + clienteNuevo.getIdCliente() + " eliminado correctamente.\n");
                System.out.println("## Comprobando la eliminación... " + clienteDAO.findById(clienteNuevo.getIdCliente()) + "\n");
            } else {
                System.err.println(" ||| ERROR - No se pudo eliminar el cliente ||| \n");
            }


            // CREACION DE CLIENTE Y CAMARA PARA LA NUEVA VENTA:
            // Creando otro cliente
            Cliente clienteVenta = new Cliente(0, "Norberto", "Mejía", "31475055", "MEJOBERTO@hotmail.com", "1648763464", "Alemania 151");
            clienteDAO.create(clienteVenta);
            if (clienteVenta.getIdCliente() > 0) {
                System.out.println("\n ## Cliente correctamente registrado. Id: " + clienteVenta.getIdCliente() + "\n");
                System.out.println(clienteVenta + "\n");
            } else {
                System.err.println(" ||| ERROR - No se pudo registrar el cliente ||| ");
            }
            // Creando otra cámara
            Camara camaraVenta=new Camara(0, "Canon", "R10", TipoCamara.MIRRORLESS, 24.5, "RF", "18-55mm 1.8", "Octubre 2023", "Cámara de sensor recortado x1.6 para principiantes.", 1799000.0,61);
            camaraDAO.create(camaraVenta);
            if (camaraVenta.getIdCamara() >0) {
                System.out.println("\n ## Cámara correctamente creada. ID: " + camaraVenta.getIdCamara() + "\n");
                System.out.println(camaraVenta + "\n");
            }else{
                System.err.println(" ||| ERROR - No se pudo crear la cámara ||| ");
            }


            // TESTS DE VENTAS
            // TEST 14: Creando una nueva venta
            System.out.println("\n--- Test 14: Registrar una nueva venta... \n");
            Venta nuevaVenta = new Venta(0, clienteVenta.getIdCliente(), camaraVenta.getIdCamara(), camaraVenta.getPrecio(), LocalDate.now());
            ventaDAO.create(nuevaVenta);
            if (nuevaVenta.getIdVenta() >0) {
                System.out.println("\n ## Venta registrada! ID: " + nuevaVenta.getIdVenta() + "\n" + nuevaVenta + "\n" );
            }else{
                System.err.println(" ||| ERROR - No se ha podido registrar la venta |||");
            }

            // TEST 15: Buscar venta por ID
            System.out.println("\n--- Test 15: Buscando venta por ID " + nuevaVenta.getIdVenta() + "...\n");
            Venta ventaExistente = ventaDAO.findById(nuevaVenta.getIdVenta());
            if (ventaExistente != null) {
                System.out.println(" ## Venta encontrada: " + ventaExistente + "\n");
            } else {
                System.err.println(" ||| No se encontró la venta con ID " + nuevaVenta.getIdVenta() + " ||| \n");
            }

            // TEST 16: Listar todas las ventas
            System.out.println("\n--- Test 16: Listando todas las ventas...\n ");
            List<Venta> todasLasVentas = ventaDAO.findAll();
            if (!todasLasVentas.isEmpty()) {
                System.out.println(" ## Ventas registradas: " + todasLasVentas.size());
                todasLasVentas.forEach(v -> System.out.println("    > " + v));
                System.out.println("\n");
            } else {
                System.err.println(" ||| No se encontraron ventas registradas ||| \n");
            }

            // TEST 17: Eliminar una venta
            System.out.println("\n--- Test 17: Eliminando la venta " + nuevaVenta.getIdVenta() + "\n ");
            int filasAfectadasDeleteVenta = ventaDAO.delete(nuevaVenta.getIdVenta());
            if (filasAfectadasDeleteVenta == 1) {
                System.out.println(" ## Venta " + nuevaVenta.getIdVenta() + " eliminada correctamente.\n");
            } else {
                System.err.println(" ||| ERROR - No se pudo eliminar la venta ||| \n");
            }
             
        } catch (Exception e) {
            System.err.println("\n /|/ ERROR EN LA BASE DE DATOS /|/ ");
            e.printStackTrace();
        } finally {
            System.out.println("<<< Pruebas finalizadas con exito >>>");
            System.out.println("<<< Contexto de Spring boot cerrado >>>");
        }
    }
}
