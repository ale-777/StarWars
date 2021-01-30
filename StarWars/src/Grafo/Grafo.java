/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import Componentes.Componentes;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Alejandra G
 */
public class Grafo {
    public ArrayList<Nodo> nodos;
    public ArrayList<Nodo> nodosC;
    public ArrayList<Arista> aristasCamino;
    public Nodo mundo;
 
    public Grafo(){
        nodos = new ArrayList<>();
        nodosC = new ArrayList<>();
        aristasCamino = new ArrayList<>();
    }
    public void addConexion(int nodoC,int nodo,Line2D.Double linea){
        Nodo nodoConector = nodosC.get(nodoC);
        Nodo nodoComponente = nodos.get(nodo);
        Arista arista = new Arista(nodoConector,nodoComponente,linea);
        nodoConector.addArista(arista);
        nodoComponente.aristasMA.add(arista);
    }
    public Nodo buscarNodo(Componentes comp){
        for (int i = 0; i < nodos.size(); i++) {
            Nodo get = nodos.get(i);
            if(get.comp == comp){
                return get;
            }     
        }
        return null;
    }
   /* public boolean buscarMundo(Nodo nodo){
        for (int i = 0; i < nodo.aristas.size(); i++) {
            Arista get = nodo.aristas.get(i);
            if(get.destino == this.mundo)
            return true;            
            }
        return false;
    }*/

    public boolean borrarNodoAux(Stack pila,ArrayList<Nodo> nodosRevisar){ 
        while(!pila.empty()){
            Nodo actual = (Nodo) pila.pop();
            if(!nodosRevisar.contains(actual))
                nodosRevisar.add(actual);
            if(actual == this.mundo)
                return true;
            else{
                for (int i = 0; i < actual.aristas.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristas.get(i).destino))
                        pila.push(actual.aristas.get(i).destino);    
                }
                for (int i = 0; i < actual.aristasMA.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristasMA.get(i).origen))
                        pila.push(actual.aristasMA.get(i).origen);    
                }
            }
            
        }
        return false;
    }
    
    public void borrarNodo(Componentes comp){
        Nodo nodo = buscarNodo(comp);
        if (nodo != null){
            ArrayList<Nodo> nodosAlFrente = new ArrayList<>();
            ArrayList<Nodo> nodosAtras = new ArrayList<>();
            for (int i = 0; i < nodo.aristas.size(); i++) {
                Arista arista = nodo.aristas.get(i);
                Nodo nodo1 = nodo.aristas.get(i).destino;
                nodosAlFrente.add(nodo);
                nodo1.aristas.remove(arista);
            }
            for (int i = 0; i < nodo.aristasMA.size(); i++) {
                Arista arista = nodo.aristasMA.get(i);
                Nodo nodo2 = arista.origen;
                nodosAtras.add(nodo);
                nodo2.aristas.remove(arista);
            }
            nodo.aristas.clear();
            this.nodos.remove(nodo);
            ArrayList<Nodo> nodosRevisarAdelante= new ArrayList<>();
            ArrayList<Nodo> nodosRevisarAtras= new ArrayList<>();
            Stack pila = new Stack();
            for (int i = 0; i < nodosAlFrente.size(); i++) {
                pila.push(nodosAlFrente.get(i));
            }
            boolean adelante = borrarNodoAux(pila,nodosRevisarAdelante);
            pila.clear();
             for (int i = 0; i < nodosAtras.size(); i++) {
                pila.push(nodosAtras.get(i));
            }
            boolean atras = borrarNodoAux(pila,nodosRevisarAtras);                       
    }
    }
}
