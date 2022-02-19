package br.com.util;

import br.com.model.Ball;
import br.com.plugins.ArrayStack;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class BallPuzzleTableModel extends DefaultTableModel {
    private final int SIZE = 4;
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public BallPuzzleTableModel(ArrayList<ArrayStack> stacks) {
        addColumnsData(stacks);
    }

    private void addColumnsData(ArrayList<ArrayStack> stacks) {
        stacks.forEach(stack -> {
            Object[] arrayStacks = stack.getArray();
            Ball[] bolas = new Ball[SIZE];
            int count = 0;
            for (int i = SIZE - 1; i >= 0; i--) {
                bolas[count++] = (Ball) arrayStacks[i];
            }
            this.addColumn("", bolas);
        });
        
        this.setRowCount(4);
    }
}