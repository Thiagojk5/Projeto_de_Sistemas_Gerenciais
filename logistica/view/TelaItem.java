/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.EntregaController;
import logistica.controller.ItemController;
import logistica.model.EntregaModel;
import logistica.model.ItemModel;
/**
 *
 * @author User
 */
public class TelaItem extends javax.swing.JFrame {

    ItemController controller = new ItemController();
    EntregaController entregaController = new EntregaController();

    public TelaItem() {
        initComponents();
        jtxIdItem.setToolTipText("Gerado automaticamente. Informe só para Editar, Excluir ou Pesquisar.");
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica os campos obrigatórios (o Id é gerado pelo banco).
        return jtxDescricao.getText().trim().isEmpty()
                || jtxPeso.getText().trim().isEmpty()
                || jtxVolume.getText().trim().isEmpty()
                || jtxValorDeclarado.getText().trim().isEmpty()
                || jtxCodEntrega.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdItem.setText("");
        jtxDescricao.setText("");
        jtxPeso.setText("");
        jtxVolume.setText("");
        jtxValorDeclarado.setText("");
        jtxCodEntrega.setText("");
        jtItem.clearSelection();
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

    private boolean entregaExiste(int id){
        for(EntregaModel e : entregaController.listar()){
            if(e.getId() == id){
                return true;
            }
        }
        return false;
    }
    private void carregarLinha(int linha){
        jtxIdItem.setText(texto(jtItem.getValueAt(linha, 0)));
        jtxDescricao.setText(texto(jtItem.getValueAt(linha, 1)));
        jtxPeso.setText(texto(jtItem.getValueAt(linha, 2)));
        jtxVolume.setText(texto(jtItem.getValueAt(linha, 3)));
        jtxValorDeclarado.setText(texto(jtItem.getValueAt(linha, 4)));
        jtxCodEntrega.setText(texto(jtItem.getValueAt(linha, 5)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtItem.getModel();
        modelo.setRowCount(0);
        try{
            for(ItemModel i : controller.listar()){
                modelo.addRow(new Object[]{i.getId(), i.getDescricao(), i.getPeso(), i.getVolume(),
                    i.getValorDeclarado(), i.getEntrega()});
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
        if(!novo && jtxIdItem.getText().trim().isEmpty()){
            mostrar("Selecione um item na tabela (ou informe o Id) para editar!");
            return;
        }
        try{
            int id = novo ? 0 : lerInt(jtxIdItem.getText(), "Id do item");
            float peso = lerFloat(jtxPeso.getText(), "Peso");
            float volume = lerFloat(jtxVolume.getText(), "Volume");
            float valor = lerFloat(jtxValorDeclarado.getText(), "Valor declarado");
            int idEntrega = lerInt(jtxCodEntrega.getText(), "Codigo da entrega");
            String descricao = jtxDescricao.getText().trim();
            if(!entregaExiste(idEntrega)){
                mostrar("Entrega nao encontrada!");
                return;
            }
            String msg = novo
                    ? controller.cadastrar(peso, volume, valor, descricao, idEntrega)
                    : controller.atualizar(id, peso, volume, valor, descricao, idEntrega);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxIdItem = new javax.swing.JTextField();
        jtxDescricao = new javax.swing.JTextField();
        jtxPeso = new javax.swing.JTextField();
        jtxVolume = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxValorDeclarado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxCodEntrega = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        JbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtItem = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Id do Item:");

        jLabel2.setText("Descrição:");

        jLabel3.setText("Peso em kg:");

        jLabel4.setText("Volume em L:");

        jtxIdItem.setText("jTextField1");

        jtxDescricao.setText("jTextField2");

        jtxPeso.setText("jTextField3");

        jtxVolume.setText("jTextField4");

        jLabel5.setText("Valor declarado:");

        jtxValorDeclarado.setText("jTextField5");

        jLabel6.setText("Código da Entrega:");

        jtxCodEntrega.setText("jTextField6");

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

        jtItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id do Item", "Descrição", "Peso em Kg", "Volume em L", "Valor declarado", "Código da Entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtItemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtItem);
        if (jtItem.getColumnModel().getColumnCount() > 0) {
            jtItem.getColumnModel().getColumn(0).setResizable(false);
            jtItem.getColumnModel().getColumn(1).setResizable(false);
            jtItem.getColumnModel().getColumn(2).setResizable(false);
            jtItem.getColumnModel().getColumn(3).setResizable(false);
            jtItem.getColumnModel().getColumn(4).setResizable(false);
            jtItem.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxValorDeclarado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxCodEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir)
                                .addGap(12, 12, 12)
                                .addComponent(jblPesquisar)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtxValorDeclarado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtxCodEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSalvarMouseClicked
        gravar(true);
    }//GEN-LAST:event_jbSalvarMouseClicked

    private void JbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbEditarMouseClicked
        gravar(false);
    }//GEN-LAST:event_JbEditarMouseClicked

    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        String codigo = jtxIdItem.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o item " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
        String codigo = jtxIdItem.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtItem.getRowCount(); i++){
                if(texto(jtItem.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtItem.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }
    }//GEN-LAST:event_jblPesquisarMouseClicked

    private void jtItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtItemMouseClicked
        int linha = jtItem.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
    }//GEN-LAST:event_jtItemMouseClicked

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
            java.util.logging.Logger.getLogger(TelaItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaItem().setVisible(true);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
    private javax.swing.JTable jtItem;
    private javax.swing.JTextField jtxCodEntrega;
    private javax.swing.JTextField jtxDescricao;
    private javax.swing.JTextField jtxIdItem;
    private javax.swing.JTextField jtxPeso;
    private javax.swing.JTextField jtxValorDeclarado;
    private javax.swing.JTextField jtxVolume;
    // End of variables declaration//GEN-END:variables
}
