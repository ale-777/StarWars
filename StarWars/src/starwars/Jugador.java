/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starwars;

import Componentes.Componentes;
import Componentes.Mercado;
import Componentes.Mundo;
import Grafo.Grafo;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Alejandra G
 */
public class Jugador {
    public String nombre;
    public int dinero;
    public int hierro;
    public Grafo grafo; 
    public int [][] matriz;
    public ArrayList<Componentes> componentesDisponibles;
    public ArrayList<Componentes> componentesAgregados;
    public ArrayList<Componentes> conectoresAgregados;
   
    public Jugador(String nombre){
        this.nombre = nombre;
        dinero = 4000;
        hierro = 0;
        componentesDisponibles = new ArrayList<>();
        componentesAgregados = new ArrayList<>();
        conectoresAgregados = new ArrayList<>();
        grafo = new Grafo();
        matriz = new int[15][15];
        crearMatriz();
    }
    public void crearMatriz(){   
        for (int i = 0; i < 15; i++) {
          for (int j = 0; j < 15 ; j++) {
            matriz [i][j] = 0;    
          }
      }
    }
    public void ocuparEspacio(String Nombre,int x,int y,String lado){
        if (nombre == "Mundo"){
            matriz[x][y] = 1;
            matriz[x+1][y]= 1;
            matriz[x][y+1]= 1;
            matriz[x+1][y+1]= 1;
        }
        else if(nombre == "Conector"){
            matriz[x][y]= 1;
        }
        else{ 
            if(lado == "Horizontal"){
             matriz[x][y]= 1;
             matriz[x+1][y]= 1;
            }
            else if (lado == "Vertical"){
                matriz[x][y]= 1;
                matriz[x][y+1]= 1;
            }
    }
    }
    public Componentes buscarComponente(int x,int y){
        for (int i = 0; i < componentesAgregados.size(); i++) {
            Componentes get = componentesAgregados.get(i);
            if("Mundo".equals(get.nombre)){
                if(x == get.x && (y == get.y || y == get.y+1) ){
                    return get;
                }
                else if(x == get.x+1 && (y == get.y || y == get.y+1)){
                    return get;
                }
            }
            else if("Conector".equals(get.nombre)){
                if(x == get.x && y == get.y)
                    return get;
            }
            else{
                if("Horizontal".equals(get.orientacion)){
                if(y == get.y && (x == get.x || x == get.x+1))
                        return get;
                }
                else if(x == get.x && (y == get.y || y == get.y+1))
                        return get;
                    
            }
        }
        return null;
    }
    public Componentes impactaron(int x,int y){
        if(matriz[x][y] == 1){
            Componentes comp = buscarComponente(x,y);   
            if(comp != null){
                comp.casillasFuego++; 
                return comp;
            }
        }
        return null;
    }
}
