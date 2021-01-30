/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class ThreadCliente extends Thread{
    
    private Socket socketRef;
    public DataInputStream reader;
    public DataOutputStream writer;
    public ObjectOutputStream objWriter;
    public ObjectInputStream objReader;
    private String nombre;
    private boolean running = true;
    private EspacioJF refPantalla;
    ArrayList<JLabel> labels;

    public ThreadCliente(Socket socketRef, EspacioJF refPantalla) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        objWriter = new ObjectOutputStream(socketRef.getOutputStream());
        objReader = new ObjectInputStream(socketRef.getInputStream());
        this.refPantalla = refPantalla;
        labels = new ArrayList<>();
        
    }
    
    public void run (){
        
        int instruccionId = 1;
        while (running){
            try {
                String usuario = "";
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // recibe el turno del jufador 1
                        refPantalla.tienda.actualizarDinero(reader.readInt());
                    break;
                    case 2: // pasan un mensaje por el chat
                        usuario = reader.readUTF();
                        String mensaje = reader.readUTF();
                        //System.out.println("CLIENTE Recibido mensaje: " + mensaje);
                        refPantalla.addMensaje(usuario+">   " + mensaje);
                    break;
                    case 3:
                        int nuevaCantidad = reader.readInt();
                        int opcion = reader.readInt();
                        System.out.println("llega a thread cliente");
                        switch(opcion){
                            case 0:
                                refPantalla.tienda.actualizarDinero(nuevaCantidad);
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("llega al case 4");
                        ArrayList <String> enemigos = (ArrayList <String>) objReader.readObject();
                        refPantalla.actualizarEnemigos(enemigos);
                        break;
                    case 5://pintar fuego en mi pantalla enemigo
                        int x= reader.readInt();
                        int y = reader.readInt();
                        refPantalla.pintarFuego(x, y);
                        System.out.println("llego a pintar fuego");
                        break;
                    case 6://refPantalla.pintarFuegomi pantalla
                        int x6 = reader.readInt();
                        int y6 = reader.readInt();
                        refPantalla.pintarEnemigo(x6,y6);
                        break;
                    case 7:
                        writer.writeInt(labels.size());
                        for (int i = 0; i < labels.size(); i++) {
                            JLabel get = labels.get(i);
                            objWriter.writeObject(get);
                        }
                        
                        break;
                    case 8:
                        ArrayList<JLabel> labels = new ArrayList<>();
                        int cuantos = reader.readInt();
                        for (int i = 0; i < cuantos; i++) {
                            labels.add((JLabel) objReader.readObject());
                        }
                        //refPantalla.pintarEnemigo(labels);
                        break;
                    
                }
            } catch (IOException ex) {
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
}
}
