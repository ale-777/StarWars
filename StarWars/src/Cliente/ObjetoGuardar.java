/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Alejandra G
 */
public class ObjetoGuardar{
    public String name;
    public ArrayList<int[]> lista;
    public ArrayList<JLabel> labels;
    public ObjetoGuardar(){
        lista = new ArrayList();
        labels = new ArrayList();
    }
    public void agregarNum(int x,int y){
        int [] nuevo = new int[2];
        nuevo[0] = x;
        nuevo[1] = y;
        lista.add(nuevo);
    }
    public void agregarLabel(JLabel label){
        labels.add(label);
    }
}
