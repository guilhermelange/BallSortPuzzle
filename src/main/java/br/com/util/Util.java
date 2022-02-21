package br.com.util;

import br.com.exception.AppError;
import br.com.model.Ball;
import br.com.model.BallColor;
import br.com.plugins.ArrayStack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Util {
    public static void updateTable(JTable table, ArrayList<ArrayStack> stacks) {
        DefaultTableModel model = new BallPuzzleTableModel(stacks);
        table.setModel(model);
        table.setTableHeader(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        BallPuzzleCellRender tableCellRender = new BallPuzzleCellRender();
        
        for (int i = 0; i < stacks.size(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(Config.COLUMN_WIDTH);
            column.setWidth(Config.COLUMN_WIDTH);
            column.setCellRenderer(tableCellRender);
        }
        table.setRowHeight(Config.COLUMN_WIDTH);
    }
    
    public static void centerFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }
    
    public static void message(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    // Read file - Begin
    public static ArrayList<ArrayStack> readFileToStack(File file, ArrayList<ArrayStack> stacks) throws Exception {
        ArrayList<BallColor> colors = new ArrayList<>();
        
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
            while(fileReader.hasNext()){
                String data= fileReader.next();
                String[] split = data.split(";");
                
                switch(split[0]) {
                    case "C":
                        colors.add(new BallColor(split[1], split[2]));
                        break;
                    case "T": {
                        ArrayStack stack = new ArrayStack();
                        
                        for (int i = 1; i < (Config.STACK_CAP+1); i++) {
                            try {
                                readField(stack, colors, split[i]);
                            } catch (Exception e) {}
                        }
                        
                        stacks.add(stack);
                        break;
                    }
                    default: 
                        throw new Exception("Registro " + split[0] + " inválido");
                }
            }
            
            ArrayStack stack = new ArrayStack();
            stacks.add(stack);

            stack = new ArrayStack();
            stacks.add(stack);
        } catch (FileNotFoundException ex) {
            throw new AppError("Arquivo não localizado");
        } catch (Exception x) {
            throw new AppError(x.getMessage());
        }
        return stacks;
    }
    
    private static void readField(ArrayStack stack, ArrayList<BallColor> colors, String colorName) {
        BallColor colorByName = getColorByName(colors, colorName);
        if (colorByName == null) {
            throw new AppError("Cor " + colorName + " inválida");
        } else {
            Ball bola = new Ball(colorByName);
            stack.push(bola);
        }
    }
    
    private static BallColor getColorByName(ArrayList<BallColor> colors, String name) {
        return colors.stream()
                    .filter(color -> color.getNome().equalsIgnoreCase(name))
                    .findFirst()
                    .get();
    }
    // Read file - End

}
