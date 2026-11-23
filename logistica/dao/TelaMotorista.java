/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JOptionPane;
import logistica.controller.MotoristaController;
import logistica.model.MotoristaModel;
/**
 *
 * @author User
 */
public class TelaMotorista extends javax.swing.JFrame {

    
    public TelaMotorista() {
        initComponents();
        limparCampos();
        jcbCategoriaCNH.removeAllItems();
        jcbCategoriaCNH.addItem("A");
        jcbCategoriaCNH.addItem("B");
        jcbCategoriaCNH.addItem("C");
        jcbCategoriaCNH.addItem("D");
        jcbCategoriaCNH.addItem("E");
        preencherTabela();
    }
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxCPF.getText().equals(""))
            vazio=true;
        else if(jtxNoCNH.getText().equals(""))
            vazio=true;
        else if(jtxNome.getText().equals(""))
            vazio=true;
        else if(jtxIdTelefone.getText().equals(""))
            vazio=true;
        else if(jtxValidade.getText().equals(""))
            vazio=true;
        else if(jtxCodigo.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxCPF.setText("");
        jtxNoCNH.setText("");
        jtxNome.setText("");
        jtxIdTelefone.setText("");
        jtxValidade.setText("");
        jtxCodigo.setText("");
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
            if(jtxCodigo.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para excluir!");
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
            if(jtxCodigo.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para pesquisar!");
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlNome = new javax.swing.JLabel();
        jlCPF = new javax.swing.JLabel();
        jlNoCNH = new javax.swing.JLabel();
        jlCategoria = new javax.swing.JLabel();
        jlValidade = new javax.swing.JLabel();
        jlTelefone = new javax.swing.JLabel();
        jtxNome = new javax.swing.JTextField();
        jtxCPF = new javax.swing.JTextField();
        jtxNoCNH = new javax.swing.JTextField();
        jtxValidade = new javax.swing.JTextField();
        jtxIdTelefone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMotorista = new javax.swing.JTable();
        jlCodigo = new javax.swing.JLabel();
        jtxCodigo = new javax.swing.JTextField();
        jcbCategoriaCNH = new javax.swing.JComboBox<>();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlNome.setText("Nome:");

        jlCPF.setText("CPF:");

        jlNoCNH.setText("n° da CNH:");

        jlCategoria.setText("Categoria da CNH:");

        jlValidade.setText("Validade da CNH:");

        jlTelefone.setText("Id do Telefone:");

        jtxNome.setText("jTextField1");

        jtxCPF.setText("jTextField2");

        jtxNoCNH.setText("jTextField3");

        jtxValidade.setText("jTextField1");

        jtxIdTelefone.setText("jTextField1");

        jtMotorista.setModel(new javax.swing.table.DefaultTableModel(
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
                "Código", "Nome", "CPF", "n° da CNH", "Categoria da CNH", "Validade da CNH", "Id do Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jtMotorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMotoristaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtMotorista);
        if (jtMotorista.getColumnModel().getColumnCount() > 0) {
            jtMotorista.getColumnModel().getColumn(0).setResizable(false);
            jtMotorista.getColumnModel().getColumn(1).setResizable(false);
            jtMotorista.getColumnModel().getColumn(2).setResizable(false);
            jtMotorista.getColumnModel().getColumn(3).setResizable(false);
            jtMotorista.getColumnModel().getColumn(4).setResizable(false);
            jtMotorista.getColumnModel().getColumn(5).setResizable(false);
            jtMotorista.getColumnModel().getColumn(6).setResizable(false);
        }

        jlCodigo.setText("Código:");

        jtxCodigo.setText("jTextField1");

        jcbCategoriaCNH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbSalvar.setText("Salvar");

        jbEditar.setText("Editar");

        jbPesquisar.setText("Pesquisar");

        jbExcluir.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlTelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxIdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlValidade)
                                    .addComponent(jlCategoria))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbCategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxValidade)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxNome, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCPF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlNoCNH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxNoCNH, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNome)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCPF)
                            .addComponent(jlCodigo)
                            .addComponent(jtxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxNoCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNoCNH))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlCategoria)
                            .addComponent(jcbCategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTelefone)
                    .addComponent(jtxIdTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       
    private void jtMotoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMotoristaMouseClicked
        int lin=jtMotorista.getSelectedRow();
        jtxCodigo.setText(String.valueOf(jtMotorista.getValueAt(lin, 0)));
        jtxNome.setText(String.valueOf(jtMotorista.getValueAt(lin, 1)));
        jtxCPF.setText(String.valueOf(jtMotorista.getValueAt(lin, 2)));
        jtxNoCNH.setText(String.valueOf(jtMotorista.getValueAt(lin, 3)));
        jcbCategoriaCNH.setSelectedItem(String.valueOf(jtMotorista.getValueAt(lin, 4)));
        jtxValidade.setText(String.valueOf(jtMotorista.getValueAt(lin, 5)));
        jtxIdTelefone.setText(String.valueOf(jtMotorista.getValueAt(lin, 6)));
    }//GEN-LAST:event_jtMotoristaMouseClicked

   private void preencherTabela(){
    
    }
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
            java.util.logging.Logger.getLogger(TelaMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMotorista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox<String> jcbCategoriaCNH;
    private javax.swing.JLabel jlCPF;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlNoCNH;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JLabel jlValidade;
    private javax.swing.JTable jtMotorista;
    private javax.swing.JTextField jtxCPF;
    private javax.swing.JTextField jtxCodigo;
    private javax.swing.JTextField jtxIdTelefone;
    private javax.swing.JTextField jtxNoCNH;
    private javax.swing.JTextField jtxNome;
    private javax.swing.JTextField jtxValidade;
    // End of variables declaration//GEN-END:variables
}
