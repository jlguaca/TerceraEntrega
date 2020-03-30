/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.utilidades;

import com.google.gson.Gson;
import parqueadero.negocio.Conductor;
import parqueadero.negocio.Vehiculo;

/**
 *
 * @author familia
 */
public class GestorJson {
   
    public String parseToJson(Object obj)
    {
        Gson objJSon = new  Gson();
        
        return objJSon.toJson(obj);
    }
    
    public Conductor parseJsonToConductor(String json)
    {
        Gson gson = new Gson();
        Conductor con = new Conductor();
        con = gson.fromJson(json, con.getClass());
        return con;
    }
    
    public Vehiculo parseJsonToVehiculo(String json)
    {
        Gson gson = new Gson();
        Vehiculo veh = new Vehiculo();
        veh = gson.fromJson(json, veh.getClass());
        return veh;
    }
    
    public String decifrarId(String json)
    {
        Gson gson = new Gson();
        String id = gson.fromJson(json, String.class);
        return id;
    }
    
    
    
}
