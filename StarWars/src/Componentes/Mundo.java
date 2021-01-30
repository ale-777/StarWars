/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class Mundo extends Componentes {
    public Mundo(String nombre,int valor, int ancho,int largo,int x, int y,JLabel label){
        super(nombre, valor,ancho,largo,x,y,label);
        
    }
        @Override
    public boolean enFuego(){
        if(this.casillasFuego >3)
            return true;
        return false;
    };
}
