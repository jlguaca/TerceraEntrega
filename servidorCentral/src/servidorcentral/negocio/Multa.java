/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;

/**
 *
 * @author familia
 */
public class Multa {
    
    private String IdCon;
    private String tipo;
    private String fecha;
    private String placa;
    
    public Multa()
    {}

    public String getIdCon() {
        return IdCon;
    }

    public void setIdCon(String IdCon) {
        this.IdCon = IdCon;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    
}
