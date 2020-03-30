/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import parqueadero.presentacion.MapaParqueadero;

/**
 *
 * @author familia
 */
public class GestionMapa {
    
    
    private static HashMap lugaresOcupados;
    

    public GestionMapa() {
        lugaresOcupados = new HashMap();
    }

   
    public boolean asginarLugar (LugarMapa pLugar, String idLugar)
    {
        boolean bandera=false;
        if(lugaresOcupados.get(idLugar)==null)
        {
            lugaresOcupados.put(idLugar,pLugar);
            bandera = true;
            System.out.println("id que se guardo en asignar:"+idLugar +"id con:"+pLugar.getObjCon().getId());
        }
        return bandera;
    }
    public static LugarMapa liberarLugar (String idLugar)
    {
        System.out.println("id que llego para liberar:"+idLugar);
        boolean bandera=false;
        LugarMapa lugarLiberado =null;
        
        if(lugaresOcupados.get(idLugar)!=null)
        {
             lugarLiberado = (LugarMapa)lugaresOcupados.get(idLugar);
             System.out.println("poraqui paso");
            lugaresOcupados.remove(idLugar);
        }
        return lugarLiberado;
    }
    
}
