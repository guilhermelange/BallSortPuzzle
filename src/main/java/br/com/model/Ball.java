package br.com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends JLabel {
    private BallColor color;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(50, 50, 50, 50);
        g2d.draw(circleShape);
    }    
    
    public Ball(BallColor color) {
        setSize(250, 250);
        setVisible(true);
        this.color = color;
    }

    public BallColor getColor() {
        return color;
    }

    public void setColor(BallColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.getNome();
    }
}
