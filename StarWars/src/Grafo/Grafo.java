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
import javax.swing.JLabel;

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
        Nodo nodoConector = nodos.get(nodoC);
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

    public boolean borrarNodoAux(Stack pila){ 
        ArrayList<Nodo> nodosRevisar = new ArrayList<>();
        while(!pila.empty()){
            Nodo actual = (Nodo) pila.pop();
            System.out.println("Revisando "+actual.comp.nombre+"["+actual.comp.x+","+actual.comp.y+"]");
            if(!nodosRevisar.contains(actual))
                nodosRevisar.add(actual);
            if(actual == this.mundo || actual.comp.nombre == "Mundo"){
              
                System.out.println("Es mundo "+actual.comp.nombre);
                return true;
            }
            else{
                for (int i = 0; i < actual.aristas.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristas.get(i).destino))
                        pila.push(actual.aristas.get(i).destino);    
                }
                for (int i = 0; i < actual.aristasMA.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristasMA.get(i).origen)){
                        System.out.println("Sigue metiendo la arista que leimine");
                        pila.push(actual.aristasMA.get(i).origen);    
                    }
                }
            }
            
        }
        System.out.println("False");
        return false;
    }
    public void traerLabels(Nodo nodo,ArrayList<JLabel> labels){
        ArrayList<Nodo> nodosRevisar = new ArrayList<>();
        Stack pila = new Stack();
        pila.push(nodo);
        while(!pila.empty()){
            Nodo actual = (Nodo) pila.pop();
            if(!nodosRevisar.contains(actual)){
                nodosRevisar.add(actual);
                labels.add(actual.comp.label);
            }
            else{
                for (int i = 0; i < actual.aristas.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristas.get(i).destino))
                        pila.push(actual.aristas.get(i).destino);    
                }
                for (int i = 0; i < actual.aristasMA.size(); i++) {
                    if(!nodosRevisar.contains(actual.aristasMA.get(i).origen)){
                        pila.push(actual.aristasMA.get(i).origen);    
                    }
                }
            }
    }
    }
    public ArrayList<JLabel> borrarNodo(String enemigo,int yo,Componentes comp){
        Nodo nodo = buscarNodo(comp);
        if (nodo != null){
            ArrayList<Nodo> nodosAdyacentes = new ArrayList<>();
            System.out.println("los que estan alfrente");
            for (int i = 0; i < nodo.aristas.size(); i++) {
                Arista arista = nodo.aristas.get(i);
                Nodo nodo1 = nodo.aristas.get(i).destino;
                System.out.println(nodo1.comp.nombre);
                nodosAdyacentes.add(nodo1);
                nodo1.aristasMA.remove(arista);
            }
            System.out.println("los que estan atras");
            for (int i = 0; i < nodo.aristasMA.size(); i++) {
                Arista arista = nodo.aristasMA.get(i);
                Nodo nodo2 = arista.origen;
                System.out.println(nodo2.comp.nombre);
                nodosAdyacentes.add(nodo2);
                nodo2.aristas.remove(arista);
            }
            nodo.aristas.clear();
            this.nodos.remove(nodo);
            ArrayList<Boolean> revisar= new ArrayList<>();
            ArrayList<JLabel> lbls= new ArrayList<>();
            Stack pila = new Stack();
            for (int i = 0; i < nodosAdyacentes.size(); i++) {
                pila.push(nodosAdyacentes.get(i));
                boolean revisando = borrarNodoAux(pila);
                pila.clear();
                revisar.add(revisando);
            }
            for (int i = 0; i < revisar.size(); i++) {
                boolean get = revisar.get(i);
                if (!get){
                    System.out.println("Quedó disconexa "+nodosAdyacentes.get(i).comp.nombre);
                    traerLabels(nodosAdyacentes.get(i),lbls);
                }
            }
            if (lbls.size()>0)
                lbls.add(nodo.comp.label);
            return lbls;
    }
       return null; 
    }
}
