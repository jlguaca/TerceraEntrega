/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

/**
 *
 * @author familia
 */
public class Usuario {
    
    private String nombres;
    private String apellidos;
    private String rol;
    private String contraseña;
    private String id;
    
    public Usuario ()
    {}
    public Usuario(String nombres, String apellidos, String rol, String contraseña, String id) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.contraseña = contraseña;
        this.id = id;
    }

    public String getNombres() {
        return nombres;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
}
