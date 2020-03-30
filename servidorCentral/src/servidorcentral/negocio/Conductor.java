/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;

import java.sql.Array;
import java.util.ArrayList;


/**
 *
 * @author Emmanuel
 */
public class Conductor {
    
    private String id;
    private String IdCarnet;
    private String nombres;
    private String apellidos;
    private String rol;
    private String genero;
    private String FechaNacimiento;
    private ArrayList<Vehiculo> vehiculos;
    
    
    //Constructores
    public Conductor ()
    {}
    
    public Conductor(String id, String IdCarnet, String nombres, String apellidos, String rol, String genero, String fechaN) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.IdCarnet = IdCarnet;
        this.FechaNacimiento = fechaN;
        vehiculos = new ArrayList<>();
    }
    //Setters and Getters
    public String getNombres() {
        return nombres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCarnet() {
        return IdCarnet;
    }

    public void setIdCarnet(String IdCanet) {
        this.IdCarnet = IdCanet;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    
}
    
    
    
    
    

