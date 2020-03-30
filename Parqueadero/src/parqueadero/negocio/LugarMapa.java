/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import javax.swing.JPanel;

/**
 *
 * @author familia
 */
public class LugarMapa {
    
    private Conductor objCon;
    private Vehiculo objVeh;
    private JPanel lugar;

    public LugarMapa(Conductor objCon, JPanel lugar,Vehiculo objVehiculo) {
        this.objCon = objCon;
        this.lugar = lugar;
        this.objVeh = objVehiculo;
        
    }

    public LugarMapa() {
    }
    

    public Conductor getObjCon() {
        return objCon;
    }

    public void setObjCon(Conductor objCon) {
        this.objCon = objCon;
    }

    public JPanel getLugar() {
        return lugar;
    }

    public void setLugar(JPanel lugar) {
        this.lugar = lugar;
    }

    public Vehiculo getObjVeh() {
        return objVeh;
    }

    public void setObjVeh(Vehiculo objVeh) {
        this.objVeh = objVeh;
    }
    
    
    
    
}
