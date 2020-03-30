/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emmanuel
 */
public class ServicioServidorCentralSocket implements IServidorCentral{

    
    
    private Socket socket = null;
    private Scanner entradaDesdeServidor;
    private PrintStream salidaAServidor;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 6000;
    private static ServicioServidorCentralSocket servicio;

    /** 
     * Conecta con el servidor
     * @param address recibe la direccion donde se encuentra alojado el servidor
     * @param port puerto por el cual se encuentra escuchando el servidor
     */
    private ServicioServidorCentralSocket()
    {}
    
    public static ServicioServidorCentralSocket getServicio()
    {
        if(servicio == null)
        {
            servicio = new ServicioServidorCentralSocket();
        }
        return servicio;
    }
    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }
    /**
     * termina la conexion con el servidor
     */
    
    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    /**
     * Envia la informacion del conductor en formato json
     *
     * @param jsonCon, json que contiene un objeto de tipo conductor con la informacion ha agregar
     * @return boolean, devuelve true si se agrego y false en caso contrario
     */
    @Override
    public boolean agregarConductor (String jsonCon)
    {
        boolean bandera=false;
        String peticion = "agregarConductor";
        String respuesta = null;
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, jsonCon);
            
            if(respuesta.equals("Agregado")){
            bandera =true;}
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return bandera;
    }
    
    /**
     * Envia la informacion del Vehiculo en formato json
     *
     * @param jsonVeh, json que contiene un objeto de tipo conductor con la informacion ha agregar
     * @return boolean, devuelve true si se agrego y false en caso contrario
     */
    
    @Override
    public boolean agregarVehiculo(String jsonVeh)
    {
        boolean bandera = false;
        String peticion = "agregarVehiculo";
        String respuesta = null;
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, jsonVeh);
            System.out.println(respuesta);
            if(respuesta.equals("Agregado")){
            bandera = true;}
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bandera;
    }
    
    /**
     * Obtiene el registro de un usuario en formato Json
     * @param id identificador del Conductor
     * @return json con el registro del Conductor
     */
    @Override
    public String BucarConductor(String id) {
       String peticion = "BuscarConductor";
       String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, id);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Obtiene el registro de un usuario en formato Json
     * @param id identificador de carnet del Conductor
     * @return json con el registro del Conductor
     */
    @Override
    public String BucarConductorCarnet(String id) {
       String peticion = "BuscarConductorCarnet";
       String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, id);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    /**
     * envia la informacion suministrada como contraseña al servidor
     * para que se verifique en la base de datos en la tabla usuarios
     * si esta registrado y el rol que tiene en el sistema (admin, vigilante)
     * @param password contraseña suministrada en la GUIIniciarSesion
     * @return String, en caso de que este registrado en la base de datos,
     * se devuelve el rol que tiene asociado para la activacion de las funciones 
     * que puede ejecutar, en caso de no estar registrado retorna "".
     */
    
    @Override
    public String autenticacion(String datos) {
        String peticion = "autenticacion";
        String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, datos);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

   
    /**
     * envia la peticion al servidor y recibe la respuesta que se genera
     * @param peticion, mensaje con la accion que se desea realizar en el servidor
     * @param json, datos para procesar la accion en el servidor
     * @return respuesta por parte del servidor
     * 
     */
    private String leerFlujoEntradaSalida(String peticion, String json) throws IOException {
        String respuesta = "";
        entradaDesdeServidor = new Scanner(socket.getInputStream());
        salidaAServidor = new PrintStream(socket.getOutputStream());
        salidaAServidor.flush();
        // Usando el protocolo de comunicación
        salidaAServidor.println(peticion + "!" + json);
        if (entradaDesdeServidor.hasNextLine()) {
            respuesta = entradaDesdeServidor.nextLine();
        }
        return respuesta;
    }

    private void cerrarFlujos() {
        salidaAServidor.close();
        entradaDesdeServidor.close();
    }

    public boolean agregarUsuario(String jsonUsu) {
        boolean bandera=false;
        String peticion = "agregarUsuario";
        String respuesta = null;
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, jsonUsu);
            
            if(respuesta.equals("Agregado")){
            bandera =true;}
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return bandera;
        
    }

    public String BucarVehiculo(String placa) {
        String peticion = "BuscarVehiculo";
       String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, placa);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public boolean agregarMulta(String json) {
       boolean bandera=false;
        String peticion = "agregarMulta";
        String respuesta = null;
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(peticion, json);
            
            if(respuesta.equals("Agregado")){
            bandera =true;}
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidorCentralSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return bandera;
    }

    
  


    
    
    
    
}
