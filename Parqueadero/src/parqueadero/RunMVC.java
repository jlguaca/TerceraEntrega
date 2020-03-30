/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import java.util.HashMap;
import parqueadero.acceso.ServicioServidorCentralSocket;
import parqueadero.negocio.GestorAutenticacion;
import parqueadero.negocio.GestorPrincipal;
import parqueadero.presentacion.*;

/**
 *
 * @author Emmanuel
 */
public class RunMVC {
    HashMap objetos;
    public RunMVC(){
    objetos = new HashMap();
    
    ServicioServidorCentralSocket servicio = ServicioServidorCentralSocket.getServicio();
    
    GestorPrincipal objGU = new GestorPrincipal(servicio);
    GUIMenuAdministrador ObjGUA = new GUIMenuAdministrador(objGU);
    MapaParqueadero mapa = new MapaParqueadero();
    GUIPrincipal objGUIP = new GUIPrincipal(mapa, objGU);
    mapa.setPrincipal(objGUIP);
    GestorAutenticacion objClsInit = new GestorAutenticacion(servicio);
    GUIIniciarSesion objGUInit = new GUIIniciarSesion(objGUIP, ObjGUA, objClsInit);
    ObjGUA.setLogin(objGUInit);
    objGUIP.setLogin(objGUInit);
    objGUInit.setVisible(true);
    
    
    objClsInit.addView(objGUInit);
    
    
    
    objGU.addView(objGUIP);
    
    
    }
    
    
    
    
}
