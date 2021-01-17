/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import starwars.JuegoSW;

/**
 *
 * @author Alejandra G
 */
public class Servidor {   
    public JuegoSW juego;
    PantallaServidor refPantalla;
    public ArrayList<ThreadServidor> conexiones;
    public ArrayList<String> nombres;
    private boolean running = true;
    private ServerSocket srv;
    private int turno = 0;
    private boolean partidaIniciada = false;

    public Servidor(PantallaServidor refPantalla) {
        this.refPantalla = refPantalla;
        conexiones = new ArrayList<ThreadServidor>();
        this.refPantalla.server = this;
    }

    public void iniciarPartida() {
        this.partidaIniciada = true;
    }
    public void iniciarJuego(){
        juego = new JuegoSW(conexiones.size(),nombres);
    }
    
    public void stopserver(){
        running = false;
    }
    
    public String getNextTurno(){
        if ( ++turno >= conexiones.size())
            turno = 0;
        
        return conexiones.get(turno).nombre;
    }
    
    public String getTurno(){
        return conexiones.get(turno).nombre;
    }
    
    public void runServer(){
        int contadorDeConexiones = 0;
        try{
            srv = new ServerSocket(35577);
            while (running){
                refPantalla.addMensaje("::Esperando conexión ...");
                Socket nuevaConexion = srv.accept();
                if (!partidaIniciada){ 
                    contadorDeConexiones++;
                    refPantalla.addMensaje(":Conexión " + contadorDeConexiones + "aceptada");

                    // nuevo thread
                    ThreadServidor newThread = new ThreadServidor(nuevaConexion, this);
                    conexiones.add(newThread);
                    newThread.start();
                }
                else{
                    // OutputStream socket para poder hacer un writer
                    refPantalla.addMensaje(":Conexión denegada: partida iniciada");
                }
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
  
}
