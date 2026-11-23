/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JOptionPane;
import logistica.controller.RotaController;
import logistica.model.RotaModel;
/**
 *
 * @author User
 */
public class TelaRota extends javax.swing.JFrame {

    
    public TelaRota() {
        initComponents();
        limparCampos();
        preencherTabela();
    }
    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        boolean vazio=false;
        if(jtxDestino.getText().equals(""))
            vazio=true;
        else if(jtxDistancia.getText().equals(""))
            vazio=true;
        else if(jtxIdRota.getText().equals(""))
            vazio=true;
        else if(jtxOrigem.getText().equals(""))
            vazio=true;
        else if(jtxTempo.getText().equals(""))
            vazio=true;
        return vazio;
    }
    private void limparCampos(){
        jtxDestino.setText("");
        jtxDistancia.setText("");
        jtxIdRota.setText("");
        jtxOrigem.setText("");
        jtxTempo.setText("");
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
            if(jtxIdRota.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para excluir!");
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
            if(jtxIdRota.getText().equals("")) JOptionPane.showMessageDialog(this, "Preencha o ID para pesquisar!");
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

        jlIdRota = new javax.swing.JLabel();
        jlOrigem = new javax.swing.JLabel();
        jlDestino = new javax.swing.JLabel();
        jlDistancia = new javax.swing.JLabel();
        jlTempo = new javax.swing.JLabel();
        jtxIdRota = new javax.swing.JTextField();
        jtxOrigem = new javax.swing.JTextField();
        jtxDestino = new javax.swing.JTextField();
        jtxDistancia = new javax.swing.JTextField();
        jtxTempo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtRota = new javax.swing.JTable();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlIdRota.setText("Id da Rota:");

        jlOrigem.setText("Origem:");

        jlDestino.setText("Destino:");

        jlDistancia.setText("Distância em km:");

        jlTempo.setText("Tempo Estimado:");

        jtxIdRota.setText("jTextField1");

        jtxOrigem.setText("jTextField2");

        jtxDestino.setText("jTextField3");

        jtxDistancia.setText("jTextField4");

        jtxTempo.setText("jTextField5");

        jtRota.setModel(new javax.swing.table.DefaultTableModel(
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
                "Id da Rota", "Origem", "Destino", "Distancia em km", "Tempo estimado em dias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
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
        jtRota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtRotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtRota);

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
                        .addComponent(jlDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDistancia)
                            .addComponent(jlTempo))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jtxTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlIdRota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxIdRota, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdRota)
                    .addComponent(jtxIdRota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlOrigem)
                    .addComponent(jtxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDestino)
                    .addComponent(jtxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDistancia)
                    .addComponent(jtxDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTempo)
                    .addComponent(jtxTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbExcluir))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtRotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtRotaMouseClicked
        int lin=jtRota.getSelectedRow();
        jtxIdRota.setText(String.valueOf(jtRota.getValueAt(lin, 0)));
        jtxOrigem.setText(String.valueOf(jtRota.getValueAt(lin, 1)));
        jtxDestino.setText(String.valueOf(jtRota.getValueAt(lin, 2)));
        jtxDistancia.setText(String.valueOf(jtRota.getValueAt(lin, 3)));
        jtxTempo.setText(String.valueOf(jtRota.getValueAt(lin, 4)));
    }//GEN-LAST:event_jtRotaMouseClicked
    public void preencherTabela(){
        
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
            java.util.logging.Logger.getLogger(TelaRota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlDestino;
    private javax.swing.JLabel jlDistancia;
    private javax.swing.JLabel jlIdRota;
    private javax.swing.JLabel jlOrigem;
    private javax.swing.JLabel jlTempo;
    private javax.swing.JTable jtRota;
    private javax.swing.JTextField jtxDestino;
    private javax.swing.JTextField jtxDistancia;
    private javax.swing.JTextField jtxIdRota;
    private javax.swing.JTextField jtxOrigem;
    private javax.swing.JTextField jtxTempo;
    // End of variables declaration//GEN-END:variables
}
