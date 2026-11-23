/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JOptionPane;
import logistica.controller.EmailClienteController;
import logistica.model.EmailClienteModel;
/**
 *
 * @author User
 */
public class TelaEmailCliente extends javax.swing.JFrame {

    /**
     * Creates new form TelaEmailCliente
     */
    public TelaEmailCliente() {
        initComponents();
        limparCampos();
        preencherTabela();
    }
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxEmail.getText().equals(""))
            vazio=true;
        else if(jtxIdCliente.getText().equals(""))
            vazio=true;
        else if(jtxIdEmail.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxEmail.setText("");
        jtxIdCliente.setText("");
        jtxIdEmail.setText("");
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
            if(jtxIdEmail.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para excluir!");
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
            if(jtxIdEmail.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para pesquisar!");
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

        jtxIdEmail = new javax.swing.JTextField();
        jtxEmail = new javax.swing.JTextField();
        jtxIdCliente = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEmailCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxIdEmail.setText("jTextField1");

        jtxEmail.setText("jTextField2");

        jtxIdCliente.setText("jTextField3");

        jbSalvar.setText("Salvar");

        jbEditar.setText("Editar");

        jbPesquisar.setText("Pesquisar");

        jbExcluir.setText("Excluir");

        jtEmailCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID do E-mail", "E-mail", "ID do Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtEmailCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtEmailClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtEmailCliente);
        if (jtEmailCliente.getColumnModel().getColumnCount() > 0) {
            jtEmailCliente.getColumnModel().getColumn(0).setResizable(false);
            jtEmailCliente.getColumnModel().getColumn(1).setResizable(false);
            jtEmailCliente.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setText("Id do E-mail:");

        jLabel2.setText("E-mail:");

        jLabel3.setText("Id do Cliente:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxIdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxIdCliente))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxIdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtEmailClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtEmailClienteMouseClicked
        int lin=jtEmailCliente.getSelectedRow();
        jtxIdEmail.setText(String.valueOf(jtEmailCliente.getValueAt(lin, 0)));
        jtxEmail.setText(String.valueOf(jtEmailCliente.getValueAt(lin, 1)));
        jtxIdCliente.setText(String.valueOf(jtEmailCliente.getValueAt(lin, 2)));
    }//GEN-LAST:event_jtEmailClienteMouseClicked

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
            java.util.logging.Logger.getLogger(TelaEmailCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEmailCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEmailCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEmailCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEmailCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTable jtEmailCliente;
    private javax.swing.JTextField jtxEmail;
    private javax.swing.JTextField jtxIdCliente;
    private javax.swing.JTextField jtxIdEmail;
    // End of variables declaration//GEN-END:variables
}
