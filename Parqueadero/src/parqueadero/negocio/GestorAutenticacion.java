/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import mvcf.AModel;
import parqueadero.acceso.ServicioServidorCentralSocket;

/**
 *
 * @author familia
 */
public class GestorAutenticacion extends AModel {
    //objetos y atributos
    private ServicioServidorCentralSocket servicio;
    public GestorAutenticacion(ServicioServidorCentralSocket servicio)
    {
        this.servicio = servicio;
    }
    public String ValidarDatos(String datos)
    {
        
        String res = servicio.autenticacion(datos);
        
        
        return res;
    }
    
}
