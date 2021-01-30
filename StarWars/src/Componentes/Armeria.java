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
public class Armeria extends Componentes{
    public String orientacion;
    public Armeria(String nombre,int valor, int ancho,int largo,int x, int y,String orientacion, JLabel label){
        super(nombre, valor,ancho,largo,x,y,label);
        setOrientacion(orientacion);
        campos();
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
    public void campos(){
        if ("Horizontal".equals(orientacion)){
            this.campos.add(new int []{x,y});
            this.campos.add(new int []{x+1,y});
        }    
        else{
            this.campos.add(new int []{x,y});
            this.campos.add(new int []{x,y+1});
        }
        }
    @Override
    public boolean enFuego(){
        if(this.casillasFuego >1)
            return true;
        return false;
    };
    }