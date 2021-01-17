/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starwars;

import java.util.ArrayList;

/**
 *
 * @author Alejandra G
 */
public class JuegoSW {
    public int cantJugadores;
    public ArrayList<Jugador> listaJugadores;
    
    public JuegoSW(int cantJugadores,ArrayList<String> nombres){
        this.cantJugadores = cantJugadores;
        listaJugadores = new ArrayList();
        startJuego(nombres);
    }
    public void startJuego(ArrayList<String> nombres){
        for (int i = 0; i < cantJugadores; i++) {
            listaJugadores.add(new Jugador(nombres.get(i)));
        }
    }
}
