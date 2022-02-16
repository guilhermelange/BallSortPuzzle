package br.com.util;

public class CustomTableModel extends javax.swing.table.DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
