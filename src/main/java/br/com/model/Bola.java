package br.com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Bola extends JLabel {
    private Cor color;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(50, 50, 50, 50);
        g2d.draw(circleShape);
    }    
    
    public Bola(Cor color) {
        setSize(250, 250);
        setVisible(true);
        this.color = color;
    }

    public Cor getColor() {
        return color;
    }

    public void setColor(Cor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bola: " + color.getNome();
    }

    @Override
    public int hashCode() {
        Random Random = new Random();
        return Random.ints().findFirst().getAsInt();
    }
}
