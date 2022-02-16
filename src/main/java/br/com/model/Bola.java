package br.com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bola extends JLabel {
    private Color color;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(100, 100, 100, 100);
        g2d.draw(circleShape);
    }    
    
    public Bola(Color color) {
        setSize(250, 250);
        setVisible(true);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bola: " + color.getNome();
    }
}
