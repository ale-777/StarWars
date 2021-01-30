/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import Componentes.Componentes;
import java.util.ArrayList;

/**
 *
 * @author Alejandra G
 */
public class Nodo {
    public Componentes comp;
    public ArrayList<Arista> aristas;
    public ArrayList<Arista> aristasMA;
 
    public Nodo(Componentes comp) {
        this.comp = comp;
        aristas = new ArrayList<>();
        aristasMA = new ArrayList<>();
    }

 
    public void addArista(Arista arista) {
        aristas.add(arista);
    }

}
