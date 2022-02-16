package br.com.util;

import br.com.model.Bola;
import br.com.plugins.ArrayStack;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Util {
    public static void renderTable(JTable table, 
                                         JScrollPane scroll, 
                                         ArrayList<ArrayStack> stacks) {
        DefaultTableModel model = new CustomTableModel();
        
        for (ArrayStack stack : stacks) {
            Object[] array = stack.getArray();
            Bola[] bolas = new Bola[array.length];
            for (int i = 0; i < array.length; i++) {
                bolas[i] = (Bola) array[i];
            }
            model.addColumn("", bolas);
        }
        model.addColumn("");
        model.addColumn("");
        model.setRowCount(4);
        
        table.setModel(model);
        table.setTableHeader(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scroll.setBorder(null);
        TableCellRender tableCellRender = new TableCellRender();
        for (int i = 0; i < stacks.size() + 2; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(35);
            column.setWidth(35);
            column.setCellRenderer(tableCellRender);
        }
        
        table.setRowHeight(35);
    }
    
    public static void centralizarFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }
    
    public static void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
