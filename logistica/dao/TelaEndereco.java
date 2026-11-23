/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import logistica.controller.EnderecoController;
import logistica.model.EnderecoModel;
/**
 *
 * @author User
 */
public class TelaEndereco extends javax.swing.JFrame {

    
    public TelaEndereco() {
        initComponents();
        limparCampos();
        preencherTabela();
        jcbUF.removeAllItems();
        jcbUF.addItem("MG");
        jcbUF.addItem("SP");
        jcbUF.addItem("RJ");
        jcbUF.addItem("ES");
        jcbUF.addItem("BA");
        jcbUF.addItem("GO");
    }
private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxBairro.getText().equals(""))
            vazio=true;
        else if(jtxCEP.getText().equals(""))
            vazio=true;
        else if(jtxCid.getText().equals(""))
            vazio=true;
        else if(jtxIdEnd.getText().equals(""))
            vazio=true;
        else if(jtxLogradouro.getText().equals(""))
            vazio=true;
        else if(jtxIdCli.getText().equals(""))
            vazio=true;
        else if(jtxNumero.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxBairro.setText("");
        jtxCEP.setText("");
        jtxCid.setText("");
        jtxIdEnd.setText("");
        jtxLogradouro.setText("");
        jtxIdCli.setText("");
        jtxNumero.setText("");
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
            if(jtxIdEnd.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para excluir!");
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
            if(jtxIdEnd.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para pesquisar!");
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

        jlIdEnd = new javax.swing.JLabel();
        jlLogradouro = new javax.swing.JLabel();
        jlNum = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxIdEnd = new javax.swing.JTextField();
        jtxLogradouro = new javax.swing.JTextField();
        jtxNumero = new javax.swing.JTextField();
        jtxBairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxCEP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxCid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxIdCli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEnd = new javax.swing.JTable();
        jcbUF = new javax.swing.JComboBox<>();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlIdEnd.setText("Id do Endereco:");

        jlLogradouro.setText("Logradouro:");

        jlNum.setText("N°:");

        jLabel4.setText("Bairro:");

        jtxIdEnd.setText("jTextField1");

        jtxLogradouro.setText("jTextField2");

        jtxNumero.setText("jTextField3");

        jtxBairro.setText("jTextField4");

        jLabel5.setText("CEP:");

        jtxCEP.setText("jTextField5");

        jLabel6.setText("Cidade:");

        jtxCid.setText("jTextField6");

        jLabel7.setText("UF:");

        jLabel8.setText("ID do Cliente:");

        jtxIdCli.setText("jTextField7");

        jtEnd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id do Endereco", "Logradouro", "N°", "Bairro", "CEP", "Cidade", "UF", "Id doCliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtEnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtEndMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtEnd);
        if (jtEnd.getColumnModel().getColumnCount() > 0) {
            jtEnd.getColumnModel().getColumn(0).setResizable(false);
            jtEnd.getColumnModel().getColumn(1).setResizable(false);
            jtEnd.getColumnModel().getColumn(2).setResizable(false);
            jtEnd.getColumnModel().getColumn(3).setResizable(false);
            jtEnd.getColumnModel().getColumn(4).setResizable(false);
            jtEnd.getColumnModel().getColumn(5).setResizable(false);
            jtEnd.getColumnModel().getColumn(6).setResizable(false);
            jtEnd.getColumnModel().getColumn(7).setResizable(false);
        }

        jcbUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbSalvar.setText("Salvar");

        jbEditar.setText("Editar");

        jbPesquisar.setText("Pesquisar");

        jbExcluir.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jlIdEnd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxIdEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jlLogradouro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jlNum)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxCid)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdEnd)
                    .addComponent(jtxIdEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtxCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlLogradouro)
                    .addComponent(jtxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlNum)
                        .addComponent(jtxNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jtxCid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jcbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtxIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtEndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtEndMouseClicked
        int lin=jtEnd.getSelectedRow();
        jtxIdEnd.setText(String.valueOf(jtEnd.getValueAt(lin, 0)));
        jtxLogradouro.setText(String.valueOf(jtEnd.getValueAt(lin, 1)));
        jtxNumero.setText(String.valueOf(jtEnd.getValueAt(lin, 2)));
        jtxBairro.setText(String.valueOf(jtEnd.getValueAt(lin, 3)));
        jtxCEP.setText(String.valueOf(jtEnd.getValueAt(lin, 4)));
        jtxCid.setText(String.valueOf(jtEnd.getValueAt(lin, 5)));
        jtxIdCli.setText(String.valueOf(jtEnd.getValueAt(lin, 7)));
        jcbUF.setSelectedItem(String.valueOf(jtEnd.getValueAt(lin, 6)));
    }//GEN-LAST:event_jtEndMouseClicked

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
            java.util.logging.Logger.getLogger(TelaEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEndereco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox<String> jcbUF;
    private javax.swing.JLabel jlIdEnd;
    private javax.swing.JLabel jlLogradouro;
    private javax.swing.JLabel jlNum;
    private javax.swing.JTable jtEnd;
    private javax.swing.JTextField jtxBairro;
    private javax.swing.JTextField jtxCEP;
    private javax.swing.JTextField jtxCid;
    private javax.swing.JTextField jtxIdCli;
    private javax.swing.JTextField jtxIdEnd;
    private javax.swing.JTextField jtxLogradouro;
    private javax.swing.JTextField jtxNumero;
    // End of variables declaration//GEN-END:variables
}
