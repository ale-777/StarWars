/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starwars;

import Cliente.EspacioJF;

/**
 *
 * @author Alejandra G
 */
public class StarWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EspacioJF primero = new EspacioJF();
        EspacioJF segundo = new EspacioJF();
        primero.ponerJF(segundo.panelEspacio);
        primero.setVisible(true);
    }
    
}
