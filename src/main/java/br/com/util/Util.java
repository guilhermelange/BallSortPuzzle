package br.com.util;

import br.com.model.Bola;
import br.com.model.Color;
import br.com.plugins.ArrayStack;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Util {
    public static DefaultTableModel getDefaultTableMode() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    public static DefaultTableCellRenderer getImageCellRender() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Bola bola = (Bola) value;
                
                
//                try {
//                    BufferedImage bimg = ImageIO.read(new File(filename));
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                ImageIcon imageIcon = new ImageIcon(
//                        new ImageIcon(filename)
//                                .getImage()
//                                .getScaledInstance(160, 240, Image.SCALE_SMOOTH)
//                );;

                return bola;
            }
        };
    }
    
    public static void renderTable(JTable table, 
                                         JScrollPane scroll, 
                                         ArrayList<ArrayStack> stacks) {
        DefaultTableModel model = getDefaultTableMode();

        for (ArrayStack stack : stacks) {
            Object[] array = stack.getArray();
            Bola[] bolas = new Bola[array.length];;
            for (int i = 0; i < array.length; i++) {
                bolas[i] = (Bola) array[i];
            }
            
            model.addColumn("", bolas);
        }
        
        table.setModel(model);
        table.setTableHeader(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredSize(new Dimension(1320, 1000));
        scroll.setPreferredSize(new Dimension(1200, 1000));
        scroll.setBorder(null);
    }
}
