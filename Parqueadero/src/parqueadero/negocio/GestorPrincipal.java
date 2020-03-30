/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import java.sql.SQLException;
import java.util.Properties;
import com.google.gson.Gson;
import mvcf.*;
import org.hsqldb.server.Servlet;
import parqueadero.acceso.ServicioServidorCentralSocket;
import parqueadero.utilidades.GestorJson;

/**
 *
 * @author Emmanuel
 */
public class GestorPrincipal extends AModel {
    
   
    private ServicioServidorCentralSocket servicio;
    /**atributo de tipo GestorJson para la conversion de los objetos a tipo
     * json para su envio al servidor
     */
    private GestorJson gson;
    
    //Constructores

    public GestorPrincipal(ServicioServidorCentralSocket servicio) {
        
        this.servicio = servicio;
        gson = new GestorJson();
    }
   
    //Metodos a utilizar
    
    /**
     * agregar un condutor:
     * agrega un conductor a la base de datos que se encuetra en el servidor
     * central
     * @param pConductor, objeto de tipo Conductor donde se encuentra la
     * informacion que sera enviada al servidor
     * @return boolean, retorna true si se agrego y false en caso contrario
     */
    public boolean agregarConductor(Conductor pConductor)
    {
        boolean bandera = false;
        String res;
        String json = gson.parseToJson(pConductor);
        System.out.println(json);
        bandera = servicio.agregarConductor(json);
        return bandera;
        
    }
    
    /**
     * agregar un vehiculo a un conductor:
     * asocia un vehiculo con un conductor que se encuentre en la base de datos en el
     * servidor central
     * @param pVeh, objeto de tipo Vehiculo donde se encuentra la
     * informacion que sera enviada al servidor para asociar al conductor
     * @return boolean, retorna true si se agrego y false en caso contrario
     */
    public boolean agregarVehiculoAConductor(Vehiculo pVeh)
    {
        boolean bandera = false;
        Gson gson = new Gson();
        String json = gson.toJson(pVeh);
        bandera = servicio.agregarVehiculo(json);
        return bandera;
        
    }
    /**Bucar Usuario por Identificacion o Carnet:
     * @param id a buscar
     * usa un servicio que se conectara mediante sockets con el servidor central
     * donde se ecuentra la base de datos de los usuarios que usan el parqueadero 
     * y este devuelve un obj Usuario con la informacion del usuario
     * @return un objeto de tipo Conductor con la informacion encontrada en el
     * servidor
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
    */
    public Conductor buscarConductorPorId (String id) throws ClassNotFoundException
    {
        String jsonCon;
        Conductor RespuestaCon = null;
        if(!"NO_ENCONTRADO".equals(servicio.BucarConductor(id))){
        jsonCon = servicio.BucarConductor(id);
        RespuestaCon=gson.parseJsonToConductor(jsonCon);
        }
        return RespuestaCon;
    }
    
    public Conductor buscarConductorPorIdCarnet (String idCarnet) throws ClassNotFoundException
    {
        String jsonCon;
        Conductor RespuestaCon = new Conductor();
        jsonCon = servicio.BucarConductor(idCarnet);
        RespuestaCon = gson.parseJsonToConductor(jsonCon);
        return RespuestaCon;
    }
    
    public Vehiculo buscarVehiculo (String placa) throws ClassNotFoundException
    {
        String jsonVeh;
        Vehiculo Respuesta = null;
        if(!"NO_ENCONTRADO".equals(servicio.BucarVehiculo(placa))){
        jsonVeh = servicio.BucarVehiculo(placa);
        Respuesta=gson.parseJsonToVehiculo(jsonVeh);
        }
        return Respuesta;
    }
    
    public boolean agregarUsuario (Usuario pUsu)
    {
        boolean bandera = false;
        String res;
        String json = gson.parseToJson(pUsu);
        System.out.println(json);
        bandera = servicio.agregarUsuario(json);
        return bandera;
    }

    public boolean agregarMulta(Multa multa) {
       boolean bandera = false;
        String res;
        String json = gson.parseToJson(multa);
        System.out.println(json);
        bandera = servicio.agregarMulta(json);
        return bandera;
    }
}

