/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandra G
 */
public class Cliente {
    private Socket socketRef;
    public EspacioJF refPantalla;
    public ThreadCliente hiloCliente;

    public Cliente(EspacioJF refPantalla) {
        this.refPantalla = refPantalla;
        refPantalla.refCliente = this;
    }
    
    public void conectar(){
 
        try{
        
            socketRef = new Socket("localhost", 35577);
            hiloCliente = new ThreadCliente(socketRef, refPantalla);
            hiloCliente.start();
            String nombre = JOptionPane.showInputDialog("Introduzca un Nick:");
            refPantalla.setTitle(nombre);
            hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
}
