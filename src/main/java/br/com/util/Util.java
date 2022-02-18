package br.com.util;

import br.com.model.Ball;
import br.com.model.BallColor;
import br.com.plugins.ArrayStack;
import java.awt.HeadlessException;
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
        DefaultTableModel model = new BallPuzzleTableModel(stacks);;
        table.setModel(model);
        table.setTableHeader(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        BallPuzzleCellRender tableCellRender = new BallPuzzleCellRender();
        
        for (int i = 0; i < stacks.size() + 2; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(35);
            column.setWidth(35);
            column.setCellRenderer(tableCellRender);
        }
        table.setRowHeight(35);
    }
    
    public static void centerFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }
    
    public static void message(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    // Read file - Begin
    public static ArrayList<ArrayStack> readFileToStack(File file, ArrayList<ArrayStack> stacks) throws HeadlessException {
        ArrayList<BallColor> colors = new ArrayList<>();
        
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
            while(fileReader.hasNext()){
                String data= fileReader.next();
                String[] split = data.split(";");
                
                switch(split[0]) {
                    case "C" -> colors.add(new BallColor(split[1], split[2]));
                    case "T" -> {
                        ArrayStack stack = new ArrayStack();
                        readField(stack, colors, split[1]);
                        readField(stack, colors, split[2]);
                        readField(stack, colors, split[3]);
                        readField(stack, colors, split[4]);
                        
                        stacks.add(stack);
                    }
                    default -> throw new Exception("Registro " + split[0] + " inválido");
                }
            }
        } catch (FileNotFoundException ex) {
            throw new Error("Arquivo não localizado");
        } catch (Exception x) {
            throw new Error(x.getMessage());
        }
        return stacks;
    }
    
    private static void readField(ArrayStack stack, ArrayList<BallColor> colors, String colorName) {
        BallColor colorByName = getColorByName(colors, colorName);
        if (colorByName == null) {
            throw new Error("Cor " + colorName + " inválida");
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
