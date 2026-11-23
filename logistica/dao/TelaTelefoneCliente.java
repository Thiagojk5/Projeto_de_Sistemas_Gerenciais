/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JOptionPane;
import logistica.controller.TelefoneClienteController;
import logistica.model.TelefoneClienteModel;
/**
 *
 * @author User
 */
public class TelaTelefoneCliente extends javax.swing.JFrame {

    /**
     * Creates new form TelaTelefoneCliente
     */
    public TelaTelefoneCliente() {
        initComponents();
        limparCampos();
        preencherTabela();
    }
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxDDD.getText().equals(""))
            vazio=true;
        else if(jtxIdCliente.getText().equals(""))
            vazio=true;
        else if(jtxIdTelefone.getText().equals(""))
            vazio=true;
        else if(jtxNumTelefone.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxDDD.setText("");
        jtxIdCliente.setText("");
        jtxIdTelefone.setText("");
        jtxNumTelefone.setText("");
         //Ações dos botões
        jbEditar.addActionListener(evento->{
            if(camposVazios()) JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            else{
                
                limparCampos();
            }
        });
        jbSalvar.addActionListener(evento->{
            if(camposVazios()) JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            else{
                
                limparCampos();
            }
        });
        jbExcluir.addActionListener(evento->{
            jbEditar.setEnabled(false);
            jbSalvar.setEnabled(false);
            jbPesquisar.setEnabled(false);
            if(jtxIdTelefone.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para excluir!");
            else{
                int opcao=JOptionPane.showConfirmDialog(this, "Realmente deseja excluir", "", JOptionPane.YES_NO_OPTION);
                if(opcao==JOptionPane.YES_OPTION){
                    
                    limparCampos();
                }else{
                    jbEditar.setEnabled(true);
                    jbSalvar.setEnabled(true);
                    jbPesquisar.setEnabled(true);
                }
            }
        });
        jbPesquisar.addActionListener(evento->{
            jbEditar.setEnabled(false);
            jbSalvar.setEnabled(false);
            jbExcluir.setEnabled(false);
            if(jtxIdTelefone.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para pesquisar!");
            else{
                int opcao=JOptionPane.showConfirmDialog(this, "Realmente deseja excluir", "", JOptionPane.YES_NO_OPTION);
                if(opcao==JOptionPane.YES_OPTION){
                    
                    limparCampos();
                }else{
                   jbEditar.setEnabled(false);
                   jbSalvar.setEnabled(false);
                   jbExcluir.setEnabled(false); 
                }
            }
        });
        //habilitar botões
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jbSalvar.setEnabled(true);
    }
    public void preencherTabela(){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCliEmail = new javax.swing.JLabel();
        jtxIdTelefone = new javax.swing.JTextField();
        jtxDDD = new javax.swing.JTextField();
        jbEditar = new javax.swing.JButton();
        jtxNumTelefone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTelefoneCliente = new javax.swing.JTable();
        jbSalvar = new javax.swing.JButton();
        jtxIdCliente = new javax.swing.JTextField();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jlCliNome = new javax.swing.JLabel();
        jlCliCPF = new javax.swing.JLabel();
        jlCLiTelefone = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlCliEmail.setText("ID do Cliente:");

        jtxIdTelefone.setText("jTextField1");

        jtxDDD.setText("jTextField2");

        jbEditar.setText("Editar");

        jtxNumTelefone.setText("jTextField3");

        jtTelefoneCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código do cliente", "DDD", "N° do Telefone", "ID do Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTelefoneCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTelefoneClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTelefoneCliente);
        if (jtTelefoneCliente.getColumnModel().getColumnCount() > 0) {
            jtTelefoneCliente.getColumnModel().getColumn(0).setResizable(false);
            jtTelefoneCliente.getColumnModel().getColumn(1).setResizable(false);
            jtTelefoneCliente.getColumnModel().getColumn(2).setResizable(false);
            jtTelefoneCliente.getColumnModel().getColumn(3).setResizable(false);
        }

        jbSalvar.setText("Salvar");

        jtxIdCliente.setText("jTextField4");

        jbPesquisar.setText("Pesquisar");

        jbExcluir.setText("Excluir");

        jlCliNome.setText("Id do Telefone:");

        jlCliCPF.setText("DDD:");

        jlCLiTelefone.setText("Telefone:");

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
                                .addComponent(jlCliNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxIdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCliEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jlCLiTelefone)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtxNumTelefone))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jlCliCPF)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtxDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliNome)
                    .addComponent(jtxIdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliCPF)
                    .addComponent(jtxDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCLiTelefone)
                    .addComponent(jtxNumTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliEmail)
                    .addComponent(jtxIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtTelefoneClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTelefoneClienteMouseClicked
        int linha=jtTelefoneCliente.getSelectedRow();
        jtxIdTelefone.setText(String.valueOf(jtTelefoneCliente.getValueAt(linha, 0)));
        jtxDDD.setText(String.valueOf(jtTelefoneCliente.getValueAt(linha, 1)));
        jtxNumTelefone.setText(String.valueOf(jtTelefoneCliente.getValueAt(linha, 2)));
        jtxIdCliente.setText(String.valueOf(jtTelefoneCliente.getValueAt(linha,3)));
    }//GEN-LAST:event_jtTelefoneClienteMouseClicked

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
            java.util.logging.Logger.getLogger(TelaTelefoneCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTelefoneCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTelefoneCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTelefoneCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTelefoneCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlCLiTelefone;
    private javax.swing.JLabel jlCliCPF;
    private javax.swing.JLabel jlCliEmail;
    private javax.swing.JLabel jlCliNome;
    private javax.swing.JTable jtTelefoneCliente;
    private javax.swing.JTextField jtxDDD;
    private javax.swing.JTextField jtxIdCliente;
    private javax.swing.JTextField jtxIdTelefone;
    private javax.swing.JTextField jtxNumTelefone;
    // End of variables declaration//GEN-END:variables
}
