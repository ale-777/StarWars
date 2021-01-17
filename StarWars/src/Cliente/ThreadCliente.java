/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Alejandra G
 */
public class ThreadCliente extends Thread{
    
    private Socket socketRef;
    public DataInputStream reader;
    public DataOutputStream writer;
    private String nombre;
    private boolean running = true;
    private EspacioJF refPantalla;

    public ThreadCliente(Socket socketRef, EspacioJF refPantalla) throws IOException {
        this.socketRef = socketRef;
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.refPantalla = refPantalla;
    }
    
    public void run (){
        
        int instruccionId = 1;
        while (running){
            try {
                String usuario = "";
                instruccionId = reader.readInt(); // esperar hasta que reciba un entero
                
                switch (instruccionId){
                    case 1: // recibe el turno del jufador 1
                        refPantalla.name = reader.readUTF();
                    break;
                    case 2: // pasan un mensaje por el chat
                        usuario = reader.readUTF();
                        String mensaje = reader.readUTF();
                        //System.out.println("CLIENTE Recibido mensaje: " + mensaje);
                        refPantalla.addMensaje(usuario+">   " + mensaje);
                    break;
                    
                    
                    
                }
            } catch (IOException ex) {
                
            }
    
    }
}
}
