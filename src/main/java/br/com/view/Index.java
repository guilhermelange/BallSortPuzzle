package br.com.view;

import br.com.model.BallColor;
import br.com.plugins.ArrayStack;
import br.com.util.Util;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Index extends javax.swing.JFrame {
    private File file = null;
    private ArrayList<BallColor> colors = new ArrayList<BallColor>();
    private ArrayList<ArrayStack> stacks = new ArrayList<ArrayStack>();
    
    public Index() {
        initComponents();
        Util.centerFrame(this);
        jLmensagem.setText("Carregue um arquivo de layout");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPtableBall = new javax.swing.JScrollPane();
        jTableBall = new javax.swing.JTable();
        jPfooter = new javax.swing.JPanel();
        jBload = new javax.swing.JButton();
        jBlargura = new javax.swing.JButton();
        jBdepth = new javax.swing.JButton();
        jBabout = new javax.swing.JButton();
        jPheader = new javax.swing.JPanel();
        jLmensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSPtableBall.setPreferredSize(new java.awt.Dimension(452, 180));

        jTableBall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableBall.setAutoscrolls(false);
        jTableBall.setEnabled(false);
        jTableBall.setName(""); // NOI18N
        jTableBall.setTableHeader(null);
        jSPtableBall.setViewportView(jTableBall);

        getContentPane().add(jSPtableBall, java.awt.BorderLayout.CENTER);

        jPfooter.setBackground(new java.awt.Color(224, 224, 224));

        jBload.setText("Carregar");
        jBload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBloadActionPerformed(evt);
            }
        });
        jPfooter.add(jBload);

        jBlargura.setText("Largura");
        jBlargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlarguraActionPerformed(evt);
            }
        });
        jPfooter.add(jBlargura);

        jBdepth.setText("Profundidade");
        jPfooter.add(jBdepth);

        jBabout.setText("Sobre");
        jBabout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaboutActionPerformed(evt);
            }
        });
        jPfooter.add(jBabout);

        getContentPane().add(jPfooter, java.awt.BorderLayout.PAGE_END);

        jLmensagem.setEnabled(false);
        jPheader.add(jLmensagem);

        getContentPane().add(jPheader, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBloadActionPerformed
        JFileChooser jFile = loadFileSelector();
        this.file = jFile.getSelectedFile();
        
        if (file != null && file.exists()) {
            try {
                Util.readFileToStack(file, stacks);
                Util.updateTable(jTableBall, stacks);
                resizeScreenImport();
            } catch (Exception e) {
                Util.message("Erro na leitura: " + e.getMessage());
            }
        } else {
            Util.message("Nenhum arquivo selecionado");
        }
    }//GEN-LAST:event_jBloadActionPerformed
    
    private void resizeScreenImport() {
        jLmensagem.setText("Selecione o método de tentativa");
        Dimension dimension = new Dimension((stacks.size() * 36)+ 36*2, this.getHeight());
        this.setPreferredSize(dimension);
        this.setSize(dimension);
    }

    private JFileChooser loadFileSelector() throws HeadlessException {
        String cwd = new File("").getAbsolutePath().concat("/src/main/java/br/com/config");
        JFileChooser jFile = new JFileChooser(cwd);
        jFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFile.showOpenDialog(this);
        return jFile;
    }
    
    private void jBlarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlarguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBlarguraActionPerformed

    private void jBaboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaboutActionPerformed
        About sobre = new About();
        sobre.setVisible(true);
    }//GEN-LAST:event_jBaboutActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBabout;
    private javax.swing.JButton jBdepth;
    private javax.swing.JButton jBlargura;
    private javax.swing.JButton jBload;
    private javax.swing.JLabel jLmensagem;
    private javax.swing.JPanel jPfooter;
    private javax.swing.JPanel jPheader;
    private javax.swing.JScrollPane jSPtableBall;
    private javax.swing.JTable jTableBall;
    // End of variables declaration//GEN-END:variables

}
