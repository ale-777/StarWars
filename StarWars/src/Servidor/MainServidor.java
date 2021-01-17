/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author Alejandra G
 */
public class MainServidor {
    
    public static void main(String[] args) {
        PantallaServidor pantalla = new PantallaServidor();
        Servidor srv = new Servidor(pantalla);
        pantalla.setVisible(true);
        srv.runServer();
        
    }
}
