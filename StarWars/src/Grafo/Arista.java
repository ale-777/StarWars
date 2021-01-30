/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.awt.geom.Line2D;

/**
 *
 * @author Alejandra G
 */
public class Arista {
    public Nodo origen;
    public Nodo destino;
    public Line2D.Double linea;
    public Arista(Nodo origen, Nodo destino,Line2D.Double linea) {
        this.origen = origen;
        this.destino = destino;
        this.linea = linea;
    }
 
 
}
