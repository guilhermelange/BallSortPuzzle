package br.com.ballsort;

import br.com.model.Bola;
import br.com.model.Cor;
import br.com.plugins.ArrayStack;
import br.com.util.Util;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Home extends javax.swing.JFrame {
    private File file = null;
    private ArrayList<Cor> colors = new ArrayList<Cor>();
    private ArrayList<ArrayStack> stacks = new ArrayList<ArrayStack>();
    
    public Home() {
        initComponents();
        Util.centralizarFrame(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jBcarregar = new javax.swing.JButton();
        jBlargura = new javax.swing.JButton();
        jBprofundidade = new javax.swing.JButton();
        jBsobre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableDados.setAutoscrolls(false);
        jTableDados.setEnabled(false);
        jTableDados.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTableDados);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(224, 224, 224));

        jBcarregar.setText("Carregar");
        jBcarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcarregarActionPerformed(evt);
            }
        });
        jPanel1.add(jBcarregar);

        jBlargura.setText("Largura");
        jBlargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlarguraActionPerformed(evt);
            }
        });
        jPanel1.add(jBlargura);

        jBprofundidade.setText("Profundidade");
        jPanel1.add(jBprofundidade);

        jBsobre.setText("Sobre");
        jBsobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsobreActionPerformed(evt);
            }
        });
        jPanel1.add(jBsobre);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcarregarActionPerformed
        JFileChooser jFile = carregaSeletorDeArquivos();
        this.file = jFile.getSelectedFile();
        
        if (file != null && file.exists()) {
            try {
                lerArquivo();
            } catch (Exception e) {
                Util.mensagem("Erro na leitura: " + e.getMessage());
            }
            
        } else {
            Util.mensagem("Nenhum arquivo selecionado");
        }
    }//GEN-LAST:event_jBcarregarActionPerformed

    private void lerArquivo() throws HeadlessException {
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
            while(fileReader.hasNext()){
                String data= fileReader.next();
                String[] split = data.split(";");
                
                switch(split[0]) {
                    case "C" -> colors.add(new Cor(split[1], split[2]));
                    case "T" -> {
                        ArrayStack stack = new ArrayStack();
                        lerCampo(stack, split[1]);
                        lerCampo(stack, split[2]);
                        lerCampo(stack, split[3]);
                        lerCampo(stack, split[4]);
                        
                        this.stacks.add(stack);
                    }
                    default -> throw new Exception("Registro " + split[0] + " inválido");
                }
            }
            Util.renderTable(jTableDados, jScrollPane1, stacks);
        } catch (FileNotFoundException ex) {
            Util.mensagem("Arquivo não localizado");
        } catch (Exception x) {
            Util.mensagem("Erro na leitura: " + x.getMessage());
        }
    }

    private JFileChooser carregaSeletorDeArquivos() throws HeadlessException {
        String cwd = new File("").getAbsolutePath().concat("/src/main/java/br/com/config");
        JFileChooser jFile = new JFileChooser(cwd);
        jFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFile.showOpenDialog(this);
        return jFile;
    }

    private void lerCampo(ArrayStack stack, String cor) {
        Cor colorByName = getColorByName(cor);
        if (colorByName == null) {
            throw new Error("Cor " + cor + " inválida");
        } else {
            Bola bola = new Bola(colorByName);
            stack.push(bola);
        }
    }

    private Cor getColorByName(String name) {
        Optional<Cor> findFirst = colors.stream().filter(color -> color.getNome().equalsIgnoreCase(name)).findFirst();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDados;
    // End of variables declaration//GEN-END:variables

}
