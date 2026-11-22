/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.VeiculoController;
import logistica.model.VeiculoModel;
/**
 *
 * @author User
 */
public class TelaVeiculo extends javax.swing.JFrame {

    VeiculoController controller = new VeiculoController();

    public TelaVeiculo() {
        initComponents();
        jtxStatus.setToolTipText("Disponivel ou Indisponivel");
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return jtxIdVeiculo.getText().trim().isEmpty()
                || jtxPlaca.getText().trim().isEmpty()
                || jtxModelo.getText().trim().isEmpty()
                || jtxCapacidade.getText().trim().isEmpty()
                || jtxAno.getText().trim().isEmpty()
                || jtxStatus.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdVeiculo.setText("");
        jtxPlaca.setText("");
        jtxModelo.setText("");
        jtxCapacidade.setText("");
        jtxAno.setText("");
        jtxStatus.setText("");
        jtVeiculo.clearSelection();
        //habilitar botões
        JbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        jblPesquisar.setEnabled(true);
        jbSalvar.setEnabled(true);
    }

    private void mostrar(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem);
    }
    private void aviso(){
        mostrar("Preencha todos os campos obrigatórios!");
    }
    // Os controllers devolvem mensagens de texto; aqui descobrimos se a operação deu certo.
    private boolean sucesso(String mensagem){
        String m = mensagem.toLowerCase();
        return !(m.contains("nao ") || m.contains("invalid") || m.contains(" ja ") || m.contains("erro"));
    }
    private String texto(Object valor){
        return valor == null ? "" : String.valueOf(valor);
    }
    private int lerInt(String valor, String campo){
        try{
            return Integer.parseInt(valor.trim());
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(campo + " invalido!");
        }
    }
    private float lerFloat(String valor, String campo){
        try{
            return Float.parseFloat(valor.trim().replace(',', '.'));
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(campo + " invalido!");
        }
    }

    // O controller só aceita "Disponivel" ou "Indisponivel"; aqui ignoramos acento e maiúsculas.
    private String lerStatus(String valor){
        String s = java.text.Normalizer.normalize(valor.trim(), java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase();
        if(s.equals("disponivel")){
            return "Disponivel";
        }
        if(s.equals("indisponivel")){
            return "Indisponivel";
        }
        throw new IllegalArgumentException("Status invalido! Use Disponivel ou Indisponivel.");
    }
    private void selecionarTipo(String tipo){
        if(tipo.isEmpty()){
            return;
        }
        for(int i = 0; i < jcbTipo.getItemCount(); i++){
            if(String.valueOf(jcbTipo.getItemAt(i)).equalsIgnoreCase(tipo)){
                jcbTipo.setSelectedIndex(i);
                return;
            }
        }
        jcbTipo.addItem(tipo);
        jcbTipo.setSelectedItem(tipo);
    }
    private void carregarLinha(int linha){
        jtxIdVeiculo.setText(texto(jtVeiculo.getValueAt(linha, 0)));
        jtxPlaca.setText(texto(jtVeiculo.getValueAt(linha, 1)));
        jtxModelo.setText(texto(jtVeiculo.getValueAt(linha, 2)));
        selecionarTipo(texto(jtVeiculo.getValueAt(linha, 3)));
        jtxCapacidade.setText(texto(jtVeiculo.getValueAt(linha, 4)));
        jtxAno.setText(texto(jtVeiculo.getValueAt(linha, 5)));
        jtxStatus.setText(texto(jtVeiculo.getValueAt(linha, 6)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtVeiculo.getModel();
        modelo.setRowCount(0);
        try{
            for(VeiculoModel v : controller.listar()){
                modelo.addRow(new Object[]{v.getId(), v.getPlaca(), v.getModelo(), v.getTipo(),
                    v.getCapacidadeDeCarga(), v.getAno(), v.getStatus()});
            }
        }catch(RuntimeException e){
            mostrar("Erro ao carregar a tabela: " + e.getMessage());
        }
    }
    private void gravar(boolean novo){
        if(camposVazios()){
            aviso();
            return;
        }
        try{
            int id = lerInt(jtxIdVeiculo.getText(), "Id do veiculo");
            String placa = jtxPlaca.getText().trim().toUpperCase();
            String modelo = jtxModelo.getText().trim();
            String tipo = String.valueOf(jcbTipo.getSelectedItem());
            float capacidade = lerFloat(jtxCapacidade.getText(), "Capacidade de carga");
            int ano = lerInt(jtxAno.getText(), "Ano");
            String status = lerStatus(jtxStatus.getText());
            String msg = novo
                    ? controller.cadastrar(id, placa, modelo, tipo, capacidade, ano, status)
                    : controller.atualizar(id, placa, modelo, tipo, capacidade, ano, status);
            mostrar(msg);
            preencherTabela();
            if(sucesso(msg)){
                limparCampos();
            }
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }catch(RuntimeException e){
            mostrar("Erro: " + e.getMessage());
        }
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
        gravar(true);
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jtVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVeiculoMouseClicked
        int linha = jtVeiculo.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
    }//GEN-LAST:event_jtVeiculoMouseClicked

    private void JbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbEditarMouseClicked
        gravar(false);
    }//GEN-LAST:event_JbEditarMouseClicked
    
    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        String codigo = jtxIdVeiculo.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o veículo " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
                return;
            }
            String msg = controller.remover(id);
            mostrar(msg);
            preencherTabela();
            if(sucesso(msg)){
                limparCampos();
            }
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }catch(RuntimeException e){
            mostrar("Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_jbExcluirMouseClicked

    private void jblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblPesquisarMouseClicked
        String codigo = jtxIdVeiculo.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtVeiculo.getRowCount(); i++){
                if(texto(jtVeiculo.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtVeiculo.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
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
