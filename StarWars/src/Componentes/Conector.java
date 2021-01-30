/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class Conector extends Componentes{
    public Conector(String nombre,int valor, int ancho,int largo,int x,int y, JLabel label){
        super(nombre, valor,ancho,largo,x,y,label);

    }
    @Override
    public boolean enFuego(){
        if(this.casillasFuego >0)
            return true;
        return false;
    };
}
