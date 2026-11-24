/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TelaOcorrencia extends javax.swing.JFrame {

    
    public TelaOcorrencia() {
        initComponents();
        limparCampos();
        preencherTabela();
        jcbDescricao.removeAllItems();
        jcbDescricao.addItem("Atraso");
        jcbDescricao.addItem("Avaria");
        jcbDescricao.addItem("Extravio");
    }
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxDataOcorrencia.getText().equals(""))
            vazio=true;
        else if(jtxIdOcorrencia.getText().equals(""))
            vazio=true;
        else if(jtxNoEntrega.getText().equals(""))
            vazio=true;
        else if(jtxTipoOcorrencia.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxDataOcorrencia.setText("");
        jtxIdOcorrencia.setText("");
        jtxNoEntrega.setText("");
        jtxTipoOcorrencia.setText("");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxIdOcorrencia = new javax.swing.JTextField();
        jtxDataOcorrencia = new javax.swing.JTextField();
        jtxTipoOcorrencia = new javax.swing.JTextField();
        jtxNoEntrega = new javax.swing.JTextField();
        jcbDescricao = new javax.swing.JComboBox<>();
        jbSalvar = new javax.swing.JButton();
        JbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtOcorrencia = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Id da Ocorrência:");

        jLabel2.setText("Data:");

        jLabel3.setText("Tipo:");

        jLabel4.setText("Descrição:");

        jLabel5.setText("N° da Entrrega:");

        jtxIdOcorrencia.setText("jTextField1");

        jtxDataOcorrencia.setText("jTextField2");

        jtxNoEntrega.setText("jTextField5");

        jcbDescricao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbSalvar.setText("Salvar");
        jbSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSalvarMouseClicked(evt);
            }
        });

        JbEditar.setText("Editar");
        JbEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JbEditarMouseClicked(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbExcluirMouseClicked(evt);
            }
        });

        jblPesquisar.setText("Pesquisar");
        jblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblPesquisarMouseClicked(evt);
            }
        });

        jtOcorrencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id da Ocorrência", "Data", "Tipo", "N° da Entrega", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOcorrencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtOcorrenciaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtOcorrencia);
        if (jtOcorrencia.getColumnModel().getColumnCount() > 0) {
            jtOcorrencia.getColumnModel().getColumn(0).setResizable(false);
            jtOcorrencia.getColumnModel().getColumn(1).setResizable(false);
            jtOcorrencia.getColumnModel().getColumn(2).setResizable(false);
            jtOcorrencia.getColumnModel().getColumn(3).setResizable(false);
            jtOcorrencia.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxTipoOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtxDataOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxIdOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxNoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir)
                                .addGap(12, 12, 12)
                                .addComponent(jblPesquisar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jtxIdOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxNoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtxDataOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jtxTipoOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSalvarMouseClicked
        JOptionPane.showMessageDialog(this, "Ocorrência ainda não tem tabela, Model nem Controller no projeto. Crie-os para ligar esta tela.");
    }//GEN-LAST:event_jbSalvarMouseClicked

    private void JbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbEditarMouseClicked
        JOptionPane.showMessageDialog(this, "Ocorrência ainda não tem tabela, Model nem Controller no projeto. Crie-os para ligar esta tela.");
    }//GEN-LAST:event_JbEditarMouseClicked

    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        JOptionPane.showMessageDialog(this, "Ocorrência ainda não tem tabela, Model nem Controller no projeto. Crie-os para ligar esta tela.");
    }//GEN-LAST:event_jbExcluirMouseClicked

    private void jblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblPesquisarMouseClicked
        JOptionPane.showMessageDialog(this, "Ocorrência ainda não tem tabela, Model nem Controller no projeto. Crie-os para ligar esta tela.");
    }//GEN-LAST:event_jblPesquisarMouseClicked

    private void jtOcorrenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtOcorrenciaMouseClicked
        int lin = jtOcorrencia.getSelectedRow();
        if(lin < 0){
            return;
        }
        jtxIdOcorrencia.setText(String.valueOf(jtOcorrencia.getValueAt(lin, 0)));
        jtxDataOcorrencia.setText(String.valueOf(jtOcorrencia.getValueAt(lin, 1)));
        jtxTipoOcorrencia.setText(String.valueOf(jtOcorrencia.getValueAt(lin, 2)));
        jtxNoEntrega.setText(String.valueOf(jtOcorrencia.getValueAt(lin, 3)));
        jcbDescricao.setSelectedItem(String.valueOf(jtOcorrencia.getValueAt(lin, 4)));
    }//GEN-LAST:event_jtOcorrenciaMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOcorrencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
    private javax.swing.JComboBox<String> jcbDescricao;
    private javax.swing.JTable jtOcorrencia;
    private javax.swing.JTextField jtxDataOcorrencia;
    private javax.swing.JTextField jtxIdOcorrencia;
    private javax.swing.JTextField jtxNoEntrega;
    private javax.swing.JTextField jtxTipoOcorrencia;
    // End of variables declaration//GEN-END:variables
}
