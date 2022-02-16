package br.com.ballsort;

import br.com.model.Bola;
import br.com.model.Color;
import br.com.plugins.ArrayStack;
import br.com.util.TableLayout;
import br.com.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends javax.swing.JFrame {
    private File file = null;
    private ArrayList<Color> colors = new ArrayList<Color>();
    private ArrayList<ArrayStack> stacks = new ArrayList<ArrayStack>();
    
    public Home() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBcarregar = new javax.swing.JButton();
        jBlargura = new javax.swing.JButton();
        jBprofundidade = new javax.swing.JButton();
        jBsobre = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBcarregar.setText("Carregar");
        jBcarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcarregarActionPerformed(evt);
            }
        });

        jBlargura.setText("Largura");
        jBlargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlarguraActionPerformed(evt);
            }
        });

        jBprofundidade.setText("Profundidade");

        jBsobre.setText("Sobre");
        jBsobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsobreActionPerformed(evt);
            }
        });

        jTableDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableDados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBcarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBlargura, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBprofundidade, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBsobre, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcarregar)
                    .addComponent(jBlargura)
                    .addComponent(jBprofundidade)
                    .addComponent(jBsobre))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcarregarActionPerformed
        String cwd = new File("").getAbsolutePath().concat("/src/main/java/br/com/config");
        JFileChooser jFile = new JFileChooser(cwd);
       
        jFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFile.showOpenDialog(this);
        this.file = jFile.getSelectedFile();
        
        if (file != null && file.exists()) {
            Scanner fileReader;
            try {
                fileReader = new Scanner(file);
                while(fileReader.hasNext()){
                    String data= fileReader.next();
                    String[] split = data.split(";");
                    
                    switch(split[0]) {
                        case "C":
                            colors.add(new Color(split[1], split[2]));
                            break;
                        case "T":
                            ArrayStack stack = new ArrayStack();
                            
                            lerCampo(stack, split[1]);
                            lerCampo(stack, split[2]);
                            lerCampo(stack, split[3]);
                            lerCampo(stack, split[4]);
                            
                            this.stacks.add(stack);
                            
                            break;
                        default:
                            throw new Exception("Registro " + split[0] + " inválido");
                    }
                }
                Util.renderTable(jTableDados, jScrollPane1, stacks);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Arquivo não localizado");
            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "Erro na leitura: " + x.getMessage());
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado");
        }
    }//GEN-LAST:event_jBcarregarActionPerformed

    private void lerCampo(ArrayStack stack, String cor) {
        Color colorByName = getColorByName(cor);
        if (colorByName == null) {
            throw new Error("Cor " + cor + " inválida");
        } else {
            Bola bola = new Bola(colorByName);
            stack.push(bola);
        }
    }

    private Color getColorByName(String name) {
        Optional<Color> findFirst = colors.stream().filter(color -> color.getNome().equalsIgnoreCase(name)).findFirst();
        return findFirst.get();
    }
    
    private void jBlarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlarguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBlarguraActionPerformed

    private void jBsobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsobreActionPerformed
        Sobre sobre = new Sobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_jBsobreActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcarregar;
    private javax.swing.JButton jBlargura;
    private javax.swing.JButton jBprofundidade;
    private javax.swing.JButton jBsobre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDados;
    // End of variables declaration//GEN-END:variables

}
