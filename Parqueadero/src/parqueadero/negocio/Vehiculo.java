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
public class Vehiculo {
    
    //atributos del vehiculo
    private String id;
    private String idCarnet;
    private String placa;
    private String marca;
    private String tipo;
    

    public Vehiculo(String id, String placa, String tipo, String idCarnet, String marca) {
        this.id = id;
        this.placa = placa;
        this.tipo = tipo;
        this.idCarnet = idCarnet;
        this.marca = marca;
    }

    public Vehiculo() {
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(String idCarnet) {
        this.idCarnet = idCarnet;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
    
    
}
