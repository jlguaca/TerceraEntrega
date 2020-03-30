/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.main;

import servidorcentral.servicio.ServidorCentral;

/**
 *
 * @author Emmanuel
 */
public class Principal {
    
    public static void main(String[] args) {
        // TODO code application logic here
        //RunMVC run = new RunMVC();
        ServidorCentral servidor = new ServidorCentral();
        servidor.iniciar();
    
}
}
