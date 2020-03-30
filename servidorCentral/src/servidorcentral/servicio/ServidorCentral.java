/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.servicio;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorcentral.negocio.*;
import servidorcentral.utilidades.GestorJson;

/**
 *
 * @author Emmanuel
 */
public class ServidorCentral implements Runnable{

  

    private static ServerSocket ssock;
    private static Socket socket;
    private GestorPrincipalScentral gestor;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 6000;

    /**
     * Constructor
     */
    public ServidorCentral() {
        gestor = new GestorPrincipalScentral();
    }
    /**
     * Logica completa del servidor
     */
    public void iniciar() {
        abrirPuerto();

        while (true) {
            esperarAlCliente();
            lanzarHilo();
        }
    }

    /**
     * Lanza el hilo
     */
    private static void lanzarHilo() {
        new Thread(new ServidorCentral()).start();
    }

    private static void abrirPuerto() {
        try {
            ssock = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void esperarAlCliente() {
        try {
            socket = ssock.accept();
            System.out.println("Cliente conectado");
        } catch (IOException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }

    /**
     * Lee los flujos del socket
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException {
        if (entradaDecorada.hasNextLine()) {
            // Extrae el flujo que envía el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);

        } else {
            salidaDecorada.flush();
            salidaDecorada.println("NO_SE_AGREGO");
        }
    }

    /**
     * Decodifica la petición, extrayeno la acción y los parámetros
     *
     * @param peticion petición completa al estilo
     * "consultarCiudadano,983932814"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException {
        StringTokenizer tokens = new StringTokenizer(peticion, "!");
        String parametros[] = new String[10];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros[1]);

    }

    /**
     * Segun el protocolo decide qué accion invocar
     *
     * @param accion acción a procesar
     * @param parametros parámetros de la acción
     */
    private void procesarAccion(String accion, String json) throws ClassNotFoundException, SQLException {
        boolean bandera = false;
        GestorJson objGJson = new GestorJson();
        String id;
        switch (accion) {
            case "agregarConductor":
                Conductor objCon = new Conductor();
                objCon = objGJson.parseJsonToConductor(json);
                bandera = gestor.agregarConductor(objCon);
                if (bandera) {
                    System.out.println("poraquipaso");
                    salidaDecorada.println("Agregado");
                } else {
                    System.out.println("Noporaquipaso");
                    salidaDecorada.println("NO_AGREGADO");
                }
                break;
                
            case "agregarVehiculo":
                Vehiculo objVeh = new Vehiculo();
                objVeh = objGJson.parseJsonToVehiculo(json);
                bandera = gestor.agregarVehiculoConductor(objVeh);
                if (bandera) {
                    salidaDecorada.println("Agregado");
                } else {
                    salidaDecorada.println("NO_AGREGADO");
                }
                break;
             
            case "BuscarConductor":
                
                
                //id = objGJson.decifrarId(json);
                Conductor objCond = gestor.buscarConductor(json);
                if (objCond == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(objGJson.parseToJson(objCond));
                }
                break;
            
            case "BuscarConductorCarnet":
                id = objGJson.decifrarId(json);
                Conductor objCondu = gestor.buscarConductorCarnet(json);
                if (objCondu == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(objGJson.parseToJson(objCondu));
                }
                break;
             
            case "AgregarVehiculo":
                              
                Vehiculo objVehiculo = new Vehiculo();
                objGJson.parseJsonToVehiculo(json);
                bandera = gestor.agregarVehiculoConductor(objVehiculo);
                if (bandera) {
                    salidaDecorada.println("NO_AGREGADO");
                } else {
                    salidaDecorada.println("Agregado");
                }
                break;
                
            case "autenticacion":
               
                String res = gestor.autenticacion(json);
                
                salidaDecorada.println(res);
              
                break;
                
            case "agregarUsuario":
                Usuario objUsu = new Usuario();
                objUsu = objGJson.parseJsonToUsuario(json);
                bandera = gestor.agregarUsuario(objUsu);
                if (bandera) {
                    System.out.println("poraquipaso");
                    salidaDecorada.println("Agregado");
                } else {
                    System.out.println("Noporaquipaso");
                    salidaDecorada.println("NO_AGREGADO");
                }
                break;
            
            case "BuscarVehiculo":                              
                Vehiculo objVehi = gestor.buscarVehiculo(json);
                if (objVehi == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(objGJson.parseToJson(objVehi));
                }
                break;
                
            case "agregarMulta":
                Multa multa = new Multa();
                multa = objGJson.parseJsonToMulta(json);
                bandera = gestor.agregarMulta(multa);
                if (bandera) {
                    System.out.println("poraquipaso");
                    salidaDecorada.println("Agregado");
                } else {
                    System.out.println("Noporaquipaso");
                    salidaDecorada.println("NO_AGREGADO");
                }
                break;
                
                
                
                
                
                        
        }
    }

    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }
}

   
    
   
    
    
    
    
    

    

