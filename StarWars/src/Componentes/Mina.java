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
public class Mina extends Componentes{
    public Mina(String nombre,int valor, int ancho,int largo,int x,int y,String orientacion,JLabel label){
        super(nombre, valor,ancho,largo,x,y,label);
        setOrientacion(orientacion);

    }
    public void setOrientacion(String orientacion){
        if (orientacion == "Horizontal")
            this.orientacion = orientacion;
        else{
            this.orientacion = orientacion;
            int tmp = this.ancho;
            this.ancho = this.largo;
            this.largo = tmp;
        }
    }
   
        @Override
    public boolean enFuego(){
        if(this.casillasFuego >1)
            return true;
        return false;
    };
}
