package br.com.view;

import br.com.algorithm.BallSortPuzzle;
import br.com.plugins.Nodo;
import br.com.util.Util;
import java.awt.Dimension;
import java.util.ArrayList;
import br.com.plugins.Busca;

public class Result extends javax.swing.JFrame {
    private final int MIN_WIDTH = 420;
    private ArrayList<BallSortPuzzle> solutionStacks = new ArrayList();
    private int indexSolution = -1;
    private int sizeSolution = 0;
    
    public Result(Busca<BallSortPuzzle> search, BallSortPuzzle inicialSet) {
        initComponents();
        Util.centerFrame(this);
        jLmensagem.setText("Carregando solução");
        long timeInit = System.currentTimeMillis();
        Nodo result = null;
        try {
            result = search.busca(inicialSet);
        } catch (Exception ex) {
            Util.message("Não foi possível localizar uma solução");
        }
        long milisseconds = (System.currentTimeMillis() - timeInit);
        verifyExecution(result, milisseconds);
        verifyButtonsVisibility();
    }
    
    private void verifyExecution(Nodo result, long milisseconds) {
        double seconds = milisseconds / 1000;
        String timeStringComp = (seconds > 0) ? seconds + "s" : milisseconds + "ms";
        if (result == null) {
            
            jLmensagem.setText("Solução não localizada - Tempo: " + timeStringComp);
        } else {
            jLmensagem.setText("Profundidade: " + result.getProfundidade() + " - Tempo: " + timeStringComp);
            Nodo w = result;
            while (w != null) {
                BallSortPuzzle th = (BallSortPuzzle) w.getEstado();
                solutionStacks.add(0, th);
                w = w.getPai();
            }
            indexSolution = 0;
            sizeSolution = solutionStacks.size();
            Util.updateTable(jTableBall, solutionStacks.get(indexSolution).getStacks());
            resizeScreen();
        }
    }
    
    private void resizeScreen() {
        int columnWidth = jTableBall.getColumnModel().getColumn(0).getWidth();
        int columnCount = jTableBall.getColumnModel().getColumnCount();
        int newWidth = (columnWidth * columnCount) + 20;
        newWidth = (newWidth < MIN_WIDTH) ? MIN_WIDTH : newWidth;
        Dimension dimension = new Dimension(newWidth, this.getHeight());
        this.setPreferredSize(dimension);
        this.setSize(dimension);
    }
    
    private void verifyButtonsVisibility() {
        if (indexSolution == -1) {
            jBback.setEnabled(false);
            jBfirst.setEnabled(false);
            jBlast.setEnabled(false);
            jBnext.setEnabled(false);
        } else if (indexSolution == 0) {
            jBback.setEnabled(false);
            jBfirst.setEnabled(false);
            jBlast.setEnabled(true);
            jBnext.setEnabled(true);
        } else if (indexSolution == (sizeSolution-1)) {
            jBback.setEnabled(true);
            jBfirst.setEnabled(true);
            jBlast.setEnabled(false);
            jBnext.setEnabled(false);
        } else {
            jBback.setEnabled(true);
            jBfirst.setEnabled(true);
            jBlast.setEnabled(true);
            jBnext.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSPtableBall = new javax.swing.JScrollPane();
        jTableBall = new javax.swing.JTable();
        jPfooter = new javax.swing.JPanel();
        jBfirst = new javax.swing.JButton();
        jBback = new javax.swing.JButton();
        jBnext = new javax.swing.JButton();
        jBlast = new javax.swing.JButton();
        jPheader = new javax.swing.JPanel();
        jLmensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jBfirst.setText("|<<");
        jBfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBfirstActionPerformed(evt);
            }
        });
        jPfooter.add(jBfirst);

        jBback.setText("<<");
        jBback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbackActionPerformed(evt);
            }
        });
        jPfooter.add(jBback);

        jBnext.setText(">>");
        jBnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnextActionPerformed(evt);
            }
        });
        jPfooter.add(jBnext);

        jBlast.setText(">>|");
        jBlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlastActionPerformed(evt);
            }
        });
        jPfooter.add(jBlast);

        getContentPane().add(jPfooter, java.awt.BorderLayout.PAGE_END);

        jLmensagem.setEnabled(false);
        jPheader.add(jLmensagem);

        getContentPane().add(jPheader, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBfirstActionPerformed
        indexSolution = 0;
        Util.updateTable(jTableBall, solutionStacks.get(indexSolution).getStacks());
        verifyButtonsVisibility();
    }//GEN-LAST:event_jBfirstActionPerformed
    
    private void jBbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbackActionPerformed
        if (indexSolution-1 >= 0) {
            indexSolution--;
            Util.updateTable(jTableBall, solutionStacks.get(indexSolution).getStacks());
        }
        
        verifyButtonsVisibility();
    }//GEN-LAST:event_jBbackActionPerformed

    private void jBlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlastActionPerformed
        indexSolution = sizeSolution-1;
        Util.updateTable(jTableBall, solutionStacks.get(indexSolution).getStacks());
        verifyButtonsVisibility();
    }//GEN-LAST:event_jBlastActionPerformed

    private void jBnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnextActionPerformed
        if (indexSolution+1 < sizeSolution) {
            indexSolution++;
            Util.updateTable(jTableBall, solutionStacks.get(indexSolution).getStacks());
        }
        verifyButtonsVisibility();
    }//GEN-LAST:event_jBnextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBback;
    private javax.swing.JButton jBfirst;
    private javax.swing.JButton jBlast;
    private javax.swing.JButton jBnext;
    private javax.swing.JLabel jLmensagem;
    private javax.swing.JPanel jPfooter;
    private javax.swing.JPanel jPheader;
    private javax.swing.JScrollPane jSPtableBall;
    private javax.swing.JTable jTableBall;
    // End of variables declaration//GEN-END:variables

}
