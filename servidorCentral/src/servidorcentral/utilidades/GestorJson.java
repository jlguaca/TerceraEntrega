/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.utilidades;

import com.google.gson.Gson;
import servidorcentral.negocio.Conductor;
import servidorcentral.negocio.Multa;
import servidorcentral.negocio.Usuario;
import servidorcentral.negocio.Vehiculo;

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
    
    public Usuario parseJsonToUsuario(String json)
    {
        Gson gson = new Gson();
        Usuario usu = new Usuario();
        usu = gson.fromJson(json, usu.getClass());
        return usu;
        
    }

    public Multa parseJsonToMulta(String json) {
       Gson gson = new Gson();
       Multa multa = new Multa();
       multa = gson.fromJson(json, multa.getClass());
       return multa;
    }
    
    
    
}
