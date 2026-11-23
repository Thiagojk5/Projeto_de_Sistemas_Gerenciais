/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projetofinalsistemasgerenciais;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TelaVeiculo extends javax.swing.JFrame {
    
    
    public TelaVeiculo() {
        initComponents();
        limparCampos();
        jcbTipo.removeAllItems();
        jcbTipo.addItem("Caminhonete");
        jcbTipo.addItem("Caminhão");
        jcbTipo.addItem("Carreta");
        jcbTipo.addItem("Reboque");
        jcbTipo.addItem("Semirreboque");
        jcbTipo.addItem("Bitrem");
        preencherTabela();
    }
    private void aviso(){JOptionPane.showMessageDialog(this, "Preencha todos os campos!");}
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxAno.getText().equals(""))
            vazio=true;
        else if(jtxCapacidade.getText().equals(""))
            vazio=true;
        else if(jtxIdVeiculo.getText().equals(""))
            vazio=true;
        else if(jtxModelo.getText().equals(""))
            vazio=true;
        else if(jtxPlaca.getText().equals(""))
            vazio=true;
        else if(jtxStatus.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxAno.setText("");
        jtxCapacidade.setText("");
        jtxIdVeiculo.setText("");
        jtxModelo.setText("");
        jtxPlaca.setText("");
        jtxStatus.setText("");
        //habilitar botões
        JbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        jblPesquisar.setEnabled(true);
        jbSalvar.setEnabled(true);
    }
    public void preencherTabela(){
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlPlaca = new javax.swing.JLabel();
        jlModelo = new javax.swing.JLabel();
        jlTipo = new javax.swing.JLabel();
        jlCapacidade = new javax.swing.JLabel();
        jlAno = new javax.swing.JLabel();
        jbSalvar = new javax.swing.JButton();
        jtxIdVeiculo = new javax.swing.JTextField();
        JbEditar = new javax.swing.JButton();
        jtxPlaca = new javax.swing.JTextField();
        jbExcluir = new javax.swing.JButton();
        jtxModelo = new javax.swing.JTextField();
        jtxCapacidade = new javax.swing.JTextField();
        jlIdVeiculo = new javax.swing.JLabel();
        jtxAno = new javax.swing.JTextField();
        jlStatus = new javax.swing.JLabel();
        jtxStatus = new javax.swing.JTextField();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtVeiculo = new javax.swing.JTable();
        jcbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlPlaca.setText("Placa:");

        jlModelo.setText("Modelo:");

        jlTipo.setText("Tipo:");

        jlCapacidade.setText("Capacidade de Carga em Tn:");

        jlAno.setText("Ano:");

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jtxIdVeiculo.setText("jTextField1");

        JbEditar.setText("Editar");
        JbEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JbEditarMouseClicked(evt);
            }
        });

        jtxPlaca.setText("jTextField2");

        jbExcluir.setText("Excluir");
        jbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbExcluirMouseClicked(evt);
            }
        });

        jtxModelo.setText("jTextField3");

        jtxCapacidade.setText("jTextField1");

        jlIdVeiculo.setText("Id do Veículo");

        jtxAno.setText("jTextField1");

        jlStatus.setText("Status: ");

        jtxStatus.setText("jTextField1");

        jblPesquisar.setText("Pesquisar");
        jblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblPesquisarMouseClicked(evt);
            }
        });

        jtVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id do Veículo", "Placa", "Modelo", "Tipo", "Capacidade de carga em T", "Ano", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtVeiculoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtVeiculo);
        if (jtVeiculo.getColumnModel().getColumnCount() > 0) {
            jtVeiculo.getColumnModel().getColumn(0).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(1).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(2).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(3).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(4).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(5).setResizable(false);
            jtVeiculo.getColumnModel().getColumn(6).setResizable(false);
        }

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlCapacidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jblPesquisar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlIdVeiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlIdVeiculo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPlaca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlModelo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTipo)
                            .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAno)
                    .addComponent(jtxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlStatus)
                    .addComponent(jtxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        if(camposVazios()) aviso();
        else{
            limparCampos();
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jtVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVeiculoMouseClicked
        int lin=jtVeiculo.getSelectedRow();
        jtxIdVeiculo.setText(String.valueOf(jtVeiculo.getValueAt(lin, 0)));
        jtxPlaca.setText(String.valueOf(jtVeiculo.getValueAt(lin, 1)));
        jtxModelo.setText(String.valueOf(jtVeiculo.getValueAt(lin, 2)));
        //jtxTipo.setText(String.valueOf(jtVeiculo.getValueAt(lin, 3)));
        jtxCapacidade.setText(String.valueOf(jtVeiculo.getValueAt(lin, 4)));
        jtxAno.setText(String.valueOf(jtVeiculo.getValueAt(lin, 5)));
        jtxStatus.setText(String.valueOf(jtVeiculo.getValueAt(lin, 6)));
    }//GEN-LAST:event_jtVeiculoMouseClicked

    private void JbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbEditarMouseClicked
        if(camposVazios()) aviso();
        else{
            
            limparCampos();
        }
    }//GEN-LAST:event_JbEditarMouseClicked
    
    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        if(camposVazios()) aviso();
        else{
            
            limparCampos();
        }
    }//GEN-LAST:event_jbExcluirMouseClicked

    private void jblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblPesquisarMouseClicked
        if(camposVazios()) aviso();
        else{
            
            limparCampos();
        }
    }//GEN-LAST:event_jblPesquisarMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlAno;
    private javax.swing.JLabel jlCapacidade;
    private javax.swing.JLabel jlIdVeiculo;
    private javax.swing.JLabel jlModelo;
    private javax.swing.JLabel jlPlaca;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jlTipo;
    private javax.swing.JTable jtVeiculo;
    private javax.swing.JTextField jtxAno;
    private javax.swing.JTextField jtxCapacidade;
    private javax.swing.JTextField jtxIdVeiculo;
    private javax.swing.JTextField jtxModelo;
    private javax.swing.JTextField jtxPlaca;
    private javax.swing.JTextField jtxStatus;
    // End of variables declaration//GEN-END:variables
}
