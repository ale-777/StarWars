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
public abstract class Componentes {
    public String nombre;
    public int valor;
    public int ancho;
    public int largo;
    public int x;
    public int y;
    public JLabel label;
    public ArrayList<int []> campos;
    public int casillasFuego;
    public Componentes(){
        
    }
    public Componentes(String nombre,int valor, int ancho,int largo,int x,int y,JLabel label){
        this.nombre = nombre;
        this.valor = valor;
        this.ancho = ancho;
        this.largo = largo;
        this.x = x;
        this.y = y;
        this.label = label;
        campos = new ArrayList<>();
        casillasFuego = 0;
    }
    public void campos(){
        
    };
    public boolean enFuego(){
        return false;
    };
}
