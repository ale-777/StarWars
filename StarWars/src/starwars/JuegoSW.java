/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starwars;

import Componentes.Armeria;
import Componentes.Componentes;
import Componentes.Conector;
import Componentes.Mercado;
import Componentes.Mina;
import Componentes.Mundo;
import Componentes.Templo;
import Grafo.Grafo;
import Grafo.Nodo;
import Servidor.Servidor;
import java.awt.geom.Line2D;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class JuegoSW {
    public ArrayList<Jugador> jugadores;
    public Servidor server;
    public JuegoSW (){
        jugadores = new ArrayList<>();
    }
    public void addJugador(String nombre){
        jugadores.add(new Jugador(nombre));
    }
    public Jugador buscarJugador(String name){
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador get = jugadores.get(i);
            if (get.nombre.equals(name))
                return get;
        }
        return null;
    }
    public void bloquearCasillas(Jugador current,int x,int y,String orientacion){
        current.matriz[x][y] = 1;
        if ("Horizontal".equals(orientacion))
            current.matriz[x+1][y] = 1;
        else current.matriz[x][y+1] = 1;
    }
    public void comproComponente(ArrayList<String>datos,JLabel label,int index){
        String nombre = datos.get(0);
        int x = parseInt(datos.get(1));
        int y = parseInt(datos.get(2));
        String orientacion = datos.get(3);
        Jugador current = jugadores.get(index);
        System.out.println("Entra al servidor "+ nombre+"a");
        switch (nombre) {
            case "Mundo":
                Mundo mundo = new Mundo("Mundo",12000,2,2,x,y,label);
                current.componentesAgregados.add(mundo);
                current.grafo.mundo = new Nodo(mundo);
                current.grafo.nodos.add(current.grafo.mundo);
                current.matriz[x][y] = 1;current.matriz[x+1][y] = 1;current.matriz[x][y+1] = 1;current.matriz[x+1][y+1] = 1;
                break;
            case "Conector":
                Conector conector = new Conector("Conector",100,1,1,x,y,label);
                current.componentesAgregados.add(conector);
                current.grafo.nodos.add(new Nodo(conector));
                System.out.println("Entra a conector");
                current.matriz[x][y] = 1;
                break;
            case "Mercado":
                Mercado mercado = new Mercado("Mercado",2000,2,1,x,y,orientacion,label);
                current.componentesAgregados.add(mercado);
                current.grafo.nodos.add(new Nodo(mercado));
                bloquearCasillas(current,x,y,orientacion);
                break;
            case "Mina":
                Mina mina = new Mina("Mina",1000,2,1,x,y,orientacion,label);
                current.componentesAgregados.add(mina);
                current.grafo.nodos.add(new Nodo(mina));
                bloquearCasillas(current,x,y,orientacion);
                break;
            case "Templo":
                Templo templo = new Templo("Templo",2500,2,1,x,y,orientacion,label);
                current.componentesAgregados.add(templo);
                current.grafo.nodos.add(new Nodo(templo));
                bloquearCasillas(current,x,y,orientacion);
                break;
            default:
                System.out.println("LLega a armeria "+ nombre);
                Armeria armeria = new Armeria(nombre,1500,2,1,x,y,orientacion,label);
                current.componentesAgregados.add(armeria);
                current.grafo.nodos.add(new Nodo(armeria));
                bloquearCasillas(current,x,y,orientacion);
                break;
        }
        current.ocuparEspacio(nombre, x, y, orientacion);
    }
    public void unirComponentes(int indexConector,int indexComp,int indexJugador,Line2D.Double linea){
        Jugador current = jugadores.get(indexJugador);
        current.grafo.addConexion(indexConector, indexComp,linea);
    }
    
    public void borrarArista(Jugador enemigo, Jugador yo,Componentes comp,int x,int y){
        enemigo.grafo.borrarNodo(comp);
    }
    public void impacto(String nombreEnemigo,int indiceMio,int x,int y,String tipo) throws IOException{
        Jugador enemigo = buscarJugador(nombreEnemigo);
        Jugador yo = jugadores.get(indiceMio);
        if(enemigo != null && yo != null){
            Componentes comp = enemigo.impactaron(x, y);
            if (comp != null){
                server.setFire(jugadores.indexOf(enemigo),jugadores.indexOf(yo),x,y);
                if(comp.enFuego()){
                    System.out.println("Esta en fuego");
                    borrarArista(enemigo,yo,comp,x,y);
                }
            }
        }
        
    }

}

