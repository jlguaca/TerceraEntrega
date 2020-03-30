/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.acceso;

/**
 *
 * @author Emmanuel
 */
public interface IServidorCentral {
    public boolean agregarConductor(String jsonCon);
    public boolean agregarVehiculo(String jsonVeh);
    public String BucarConductor(String id);
    public String BucarConductorCarnet(String id);
    public String autenticacion(String password);
    
    
}
