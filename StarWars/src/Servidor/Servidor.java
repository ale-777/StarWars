/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import starwars.JuegoSW;
import starwars.Jugador;

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
        this.juego = new JuegoSW();
        juego.server = this;
        this.refPantalla = refPantalla;
        nombres = new ArrayList<>();
        conexiones = new ArrayList<ThreadServidor>();
        this.refPantalla.server = this;
    }
    public void actualizarEnemigo(String nombre,int index) throws IOException, ClassNotFoundException{
        Jugador jugador = juego.buscarJugador(nombre);
        if(jugador != null){
            ArrayList<JLabel> labels = new ArrayList<>();
            int indice = juego.jugadores.indexOf(jugador);
            ThreadServidor current = conexiones.get(indice);
            current.writer.writeInt(7);
            int cuantos = current.reader.readInt();
            for (int i = 0; i < cuantos; i++) {
                labels.add((JLabel) current.objReader.readObject());
            }
            
            current = conexiones.get(index);
            current.writer.writeInt(9);
            current.writer.writeInt(cuantos);
            for (int i = 0; i < labels.size(); i++) {
                JLabel get = labels.get(i);
                current.objWriter.writeObject(get);
            }
        }
    }

    public void iniciarPartida() {
        this.partidaIniciada = true;
    }
    public void iniciarJuego(){
        
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
    public void empezarPartida() throws IOException{
        for (int i = 0; i < conexiones.size(); i++) {
        ThreadServidor current = conexiones.get(i);
        current.writer.writeInt(4);
        current.objWriter.writeObject(nombres);
    }
    }
    public void setFire(int indexEnemigo,int yo,int x,int y) throws IOException{
        ThreadServidor enemigo = conexiones.get(indexEnemigo);
        ThreadServidor myself = conexiones.get(yo);
        enemigo.writer.writeInt(5);
        enemigo.writer.writeInt(x);
        enemigo.writer.writeInt(y);
 
        myself.writer.writeInt(6);
        myself.writer.writeInt(x);
        myself.writer.writeInt(y);
    }
    public void pintarDesconexo(String enemigo,int yo, ArrayList<JLabel> lbls){
        
    }
    public void mandarArrayFuego(int indice,int x,int y) throws IOException{
        ThreadServidor current = conexiones.get(indice);
        current.writer.writeInt(6);
        current.writer.writeInt(x);
        current.writer.writeInt(y);
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
