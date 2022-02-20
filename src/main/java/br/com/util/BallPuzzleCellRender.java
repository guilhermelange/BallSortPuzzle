package br.com.util;

import br.com.model.Ball;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class BallPuzzleCellRender extends DefaultTableCellRenderer implements Icon {
    private static final int SIZE = Config.BALL_WIDTH;
    Color color;

    public BallPuzzleCellRender() {
        this.setIcon(this);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.color = null;
    }

    @Override
    protected void setValue(Object value) {
        if (value != null) {
            Ball ball = (Ball) value;
            this.color = ball.getColor().getColor();
        } else {
            this.color = null;
        }
        setText("");
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.color != null) {
            g2d.setColor(this.color);
            g2d.fillOval(0,0, SIZE, SIZE);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(0,0, SIZE, SIZE);
        }
    }

    @Override
    public int getIconWidth() {
        return SIZE;
    }

    @Override
    public int getIconHeight() {
        return SIZE;
    }
    
}
