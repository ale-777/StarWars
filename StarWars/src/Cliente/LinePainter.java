/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Alejandra G
 */
public class LinePainter extends JComponent{

    ArrayList<Line2D.Double> lines;

    public LinePainter(int width, int height)  {
        super();
        lines = new ArrayList<Line2D.Double>();
        setPreferredSize(new Dimension(width,height));
    }
    public void addLine(int x1,int y1,int x2,int y2) {
        Line2D.Double line = new Line2D.Double(x1,y1,x2,y2);
        lines.add(line);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, 10, 10);
        //Dimension d = getPreferredSize();
        g.setColor(Color.black);
        for (int i = 0; i < lines.size(); i++) {
            Line2D.Double line = lines.get(i);
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2()
                );
        }
        }

}

