package br.com.util;

import br.com.model.Bola;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRender extends DefaultTableCellRenderer implements Icon {
    private static final int SIZE = 32;
    private static final int HALF = SIZE / 2;
    Color color;
    Color defaultColor = new Color(255,255,255);

    public TableCellRender() {
        this.setIcon(this);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.color = null;
    }

    @Override
    protected void setValue(Object value) {
        if (value != null) {
            Bola bola = (Bola) value;
            this.color = Color.decode(bola.getColor().getHexCode());
        } else {
            this.color = null;
        }
        setText("");
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color colorIcon = (this.color != null) ? this.color : defaultColor;
        g2d.setColor(colorIcon);
        int d = SIZE;
        int r = d / 2;
        g2d.fillOval(x + HALF - r, y + HALF - r, 28, 28);
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
