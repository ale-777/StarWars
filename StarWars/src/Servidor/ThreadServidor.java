/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;


import Componentes.Componentes;
import java.awt.geom.Line2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class ThreadServidor extends Thread{
    public String nombre;
    private Socket socketRef;
    private DataInputStream reader;
    public DataOutputStream writer;
    public   ObjectOutputStream objWriter;
    public ObjectInputStream objReader;
    private boolean running = true;
    Servidor server;
    ArrayList<Componentes> componentesDisponibles;
    ArrayList<Componentes> componentesAgregados;

    public ThreadServidor(Socket socketRef, Servidor server) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        objWriter = new ObjectOutputStream(socketRef.getOutputStream());
        objReader = new ObjectInputStream(socketRef.getInputStream());
        this.server = server;
        componentesDisponibles = new ArrayList<Componentes>();
        
    }
    
    public void enviarTurnoInicial() throws IOException{
        writer.writeInt(1);
        writer.writeUTF(server.getTurno());
    }
    
    public void run (){
        
        int instruccionId = 1;
        while (running){
            try {
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // pasan el nombre del usuario
                        nombre = reader.readUTF();
                        server.nombres.add(nombre);
                        server.juego.addJugador(nombre);
                        writer.writeInt(1);
                        writer.writeInt(server.juego.jugadores.get(server.conexiones.indexOf(this)).dinero);
                    break;
                    case 2: // pasan un mensaje por el chat
                        String mensaje = reader.readUTF();
                        
                        for (int i = 0; i < server.conexiones.size(); i++) {
                            ThreadServidor current = server.conexiones.get(i);
                            current.writer.writeInt(2);
                            current.writer.writeUTF(nombre);
                            current.writer.writeUTF(mensaje);
                        }
                    break;
                    case 3:
                        int monto = reader.readInt();
                        server.juego.jugadores.get(server.conexiones.indexOf(this)).dinero -= monto;
                        writer.writeInt(3);
                        writer.writeInt(server.juego.jugadores.get(server.conexiones.indexOf(this)).dinero);
                        writer.writeInt(0);
                        break;
                    case 4:
                        ArrayList<String> datos = (ArrayList<String>) objReader.readObject();
                        JLabel label = (JLabel) objReader.readObject();
                        server.juego.comproComponente(datos,label,server.conexiones.indexOf(this));
                        break;
                    case 5:
                        int indexConec = reader.readInt();
                        int indexComp = reader.readInt();
                        Line2D.Double linea = (Line2D.Double) objReader.readObject();
                        server.juego.unirComponentes(indexConec, indexComp, server.conexiones.indexOf(this), linea);
                        break;
                    case 6:
                        String nombreDestino = reader.readUTF();
                        String tipo = reader.readUTF();
                        int x = reader.readInt();
                        int y = reader.readInt();
                        System.out.println("Nombre enemigo: "+ nombreDestino + " tipo arma "+ tipo+ " casillas "+x+","+y);
                        server.juego.impacto(nombreDestino,server.conexiones.indexOf(this),x,y,tipo);
                        break;
                    case 7:
                        System.out.println("Esta llegando al case 7 server");
                        int indexMandar = reader.readInt();
                        ArrayList<JLabel> labels = (ArrayList<JLabel>)objReader.readObject();
                        server.mandarArrayFuego(indexMandar,labels);
                        break;
                        
                        
                }
            } catch (IOException ex) {
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
