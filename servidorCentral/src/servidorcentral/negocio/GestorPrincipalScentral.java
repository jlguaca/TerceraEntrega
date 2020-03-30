/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;

import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 *
 * @author Emmanuel
 */
public class GestorPrincipalScentral {
    
    private final ConectorJdbc conector;
    
    public GestorPrincipalScentral()
    {
        conector = new ConectorJdbc();
    }
    
    /*metodo por el cual se registra un conductor en la base de datos */
    public boolean agregarConductor(Conductor objCon) throws ClassNotFoundException, SQLException {
        
        conector.conectarse();
        conector.crearConsulta("insert into conductor (id, idcarnet, nombres, apellidos, rol, genero, fechan) values ('"+objCon.getId()+"','"+objCon.getIdCarnet()+"','"+objCon.getNombres()+"','"+objCon.getApellidos()+"','"+objCon.getRol()+"','"+objCon.getGenero()+"','"+objCon.getFechaNacimiento()+"')");
        conector.desconectarse();
        return true;
    }
    

    /*metodo por el cual se agrega un vehiculo a la base de datos y se asocia a
    *un conductor por medio de la foreign key
    */
    public boolean agregarVehiculoConductor(Vehiculo objVehiculo) throws SQLException, ClassNotFoundException
    {
        conector.conectarse();
        conector.crearConsulta("insert into vehiculo (id, idcarnet, marca, tipo, placa) values ('"+objVehiculo.getId()+"','"+objVehiculo.getIdCarnet()+"','"+objVehiculo.getMarca()+"','"+objVehiculo.getTipo()+"','"+objVehiculo.getPlaca()+"')");
        conector.desconectarse();
        return true;
        
    }
    
    /*metodo por el cual se busca un conductor registrado en la base de datos
    **apartir del @param id
    */
    
    public Conductor buscarConductor (String id) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM Conductor Where id='" + id + "'");

        Conductor objCon = null;
        if (conector.getResultado().next()) {
            System.out.println(conector.getResultado().getString("nombres"));
            objCon = new Conductor(conector.getResultado().getString("id"), conector.getResultado().getString("idCarnet"), conector.getResultado().getString("nombres"), conector.getResultado().getString("apellidos"), conector.getResultado().getString("rol"),conector.getResultado().getString("genero"), conector.getResultado().getString("fechan"));
        }
        
        conector.crearConsulta("select * from vehiculo where id='" + id + "'");
        Vehiculo objVehiculo = null;
        while(conector.getResultado().next())
        {
            objVehiculo = new Vehiculo(conector.getResultado().getString("id"),conector.getResultado().getString("placa"),conector.getResultado().getString("tipo"),conector.getResultado().getString("idcarnet"),conector.getResultado().getString("marca"));
            objCon.getVehiculos().add(objVehiculo);
        }
        conector.desconectarse();
        return objCon;    
    }
    
    /*metodo por el cual se busca un conductor registrado en la base de datos
    **apartir del @param idCarnet
    */
    public Conductor buscarConductorCarnet (String idCarnet) throws ClassNotFoundException, SQLException
    {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM Conductor Where idCarnet='" + idCarnet + "'");

        Conductor objCon = null;
        if (conector.getResultado().next()) {
            System.out.println(conector.getResultado().getString("nombres"));
            objCon = new Conductor(conector.getResultado().getString("id"), conector.getResultado().getString("idCarnet"), conector.getResultado().getString("nombres"), conector.getResultado().getString("apellidos"), conector.getResultado().getString("rol"), conector.getResultado().getString("genero"), conector.getResultado().getString("fechan"));
        }
        
        conector.crearConsulta("select * from vehiculo where idCarnet='" + idCarnet + "'");
        Vehiculo objVehiculo = null;
        if(conector.getResultado().next())
        {
            objVehiculo = new Vehiculo(conector.getResultado().getString("id"),conector.getResultado().getString("placa"),conector.getResultado().getString("tipo"),conector.getResultado().getString("idcarnet"),conector.getResultado().getString("marca"));
            objCon.getVehiculos().add(objVehiculo);
        }
        conector.desconectarse();
        return objCon;
    }
    
    public String autenticacion (String datos) throws SQLException, ClassNotFoundException
    {
        conector.conectarse();

        String bandera = "";
        StringTokenizer tokens = new StringTokenizer(datos, ",");
        String usuario = tokens.nextToken();
        String password = tokens.nextToken();
        System.out.println(usuario);
        System.out.println(password);
        conector.crearConsulta("select * from usuario where id='" + usuario + "' and password='"+password+"'");
        if(conector.getResultado().next())
        {
            bandera = conector.getResultado().getString("rol");
            
        }
        
        conector.desconectarse();
        return bandera;
    }

    public boolean agregarUsuario(Usuario objUsu) throws SQLException, ClassNotFoundException {
        conector.conectarse();
        conector.crearConsulta("insert into usuario (id,nombres,apellidos,rol, password)values('"+objUsu.getId()+"','"+objUsu.getNombres()+"','"+objUsu.getApellidos()+"','"+objUsu.getRol()+"','"+objUsu.getContraseña()+"')");
        conector.desconectarse();
        return true;
       
    }

    public Vehiculo buscarVehiculo(String json) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        
        conector.crearConsulta("select * from vehiculo where placa='" + json + "'");
        Vehiculo objVehiculo = null;
        if(conector.getResultado().next())
        {
            objVehiculo = new Vehiculo(conector.getResultado().getString("id"),conector.getResultado().getString("placa"),conector.getResultado().getString("tipo"),conector.getResultado().getString("idcarnet"),conector.getResultado().getString("marca"));
            
        }
        conector.desconectarse();
        return objVehiculo;    
    }

    public boolean agregarMulta(Multa multa) throws SQLException, ClassNotFoundException {
        conector.conectarse();
        conector.crearConsulta("insert into multa (id,placa,fecha,tipo)values('"+multa.getIdCon()+"','"+multa.getPlaca()+"','"+multa.getFecha()+"','"+multa.getTipo()+"')");
        conector.desconectarse();
        return true;
    }
   
}
