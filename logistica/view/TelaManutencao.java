/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.ManutencaoController;
import logistica.controller.VeiculoController;
import logistica.model.ManutencaoModel;
import logistica.model.VeiculoModel;
/**
 *
 * @author User
 */
public class TelaManutencao extends javax.swing.JFrame {

    ManutencaoController controller = new ManutencaoController();
    VeiculoController veiculoController = new VeiculoController();

    public TelaManutencao() {
        initComponents();
        jtxIdManutencao.setToolTipText("Gerado automaticamente. Informe só para Editar, Excluir ou Pesquisar.");
        jtxDataManutencao.setToolTipText("dd/mm/aaaa");
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica os campos obrigatórios (Id é gerado pelo banco; descrição é opcional).
        return jtxDataManutencao.getText().trim().isEmpty()
                || jtxTipo.getText().trim().isEmpty()
                || jtxCusto.getText().trim().isEmpty()
                || jtxPlacaVeiculo.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdManutencao.setText("");
        jtxDataManutencao.setText("");
        jtxTipo.setText("");
        jtxCusto.setText("");
        jtxPlacaVeiculo.setText("");
        jtxDesc.setText("");
        jtManutencao.clearSelection();
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

    private static final java.time.format.DateTimeFormatter FORMATO_DATA =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(java.time.format.ResolverStyle.STRICT);
    private String lerData(String valor, String campo){
        String data = valor.trim();
        try{
            java.time.LocalDate.parse(data, FORMATO_DATA);
        }catch(java.time.format.DateTimeParseException e){
            throw new IllegalArgumentException(campo + " invalida! Use o formato dd/mm/aaaa.");
        }
        return data;
    }

    // A tela pede a placa; o controller trabalha com o código do veículo.
    private int idDoVeiculo(String placa){
        for(VeiculoModel v : veiculoController.listar()){
            if(v.getPlaca().equalsIgnoreCase(placa)){
                return v.getId();
            }
        }
        return -1;
    }
    private void carregarLinha(int linha){
        jtxIdManutencao.setText(texto(jtManutencao.getValueAt(linha, 0)));
        jtxDataManutencao.setText(texto(jtManutencao.getValueAt(linha, 1)));
        jtxTipo.setText(texto(jtManutencao.getValueAt(linha, 2)));
        jtxCusto.setText(texto(jtManutencao.getValueAt(linha, 3)));
        jtxPlacaVeiculo.setText(texto(jtManutencao.getValueAt(linha, 4)));
        jtxDesc.setText(texto(jtManutencao.getValueAt(linha, 5)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtManutencao.getModel();
        modelo.setRowCount(0);
        try{
            for(ManutencaoModel m : controller.listar()){
                modelo.addRow(new Object[]{m.getId(), m.getData(), m.getTipo(), m.getCusto(),
                    m.getVeiculo().getPlaca(), texto(m.getDescricao())});
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
        if(!novo && jtxIdManutencao.getText().trim().isEmpty()){
            mostrar("Selecione uma manutenção na tabela (ou informe o Id) para editar!");
            return;
        }
        try{
            int id = novo ? 0 : lerInt(jtxIdManutencao.getText(), "Id da manutencao");
            String data = lerData(jtxDataManutencao.getText(), "Data");
            String tipo = jtxTipo.getText().trim();
            float custo = lerFloat(jtxCusto.getText(), "Custo");
            String descricao = jtxDesc.getText().trim();
            if(descricao.isEmpty()){
                descricao = null;
            }
            int idVeiculo = idDoVeiculo(jtxPlacaVeiculo.getText().trim());
            if(idVeiculo < 0){
                mostrar("Veiculo nao encontrado com essa placa!");
                return;
            }
            String msg = novo
                    ? controller.cadastrar(custo, tipo, data, descricao, idVeiculo)
                    : controller.atualizar(id, custo, tipo, data, descricao, idVeiculo);
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
        jtxIdManutencao = new javax.swing.JTextField();
        jtxDataManutencao = new javax.swing.JTextField();
        jtxTipo = new javax.swing.JTextField();
        jtxCusto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxPlacaVeiculo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxDesc = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        JbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtManutencao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Id da Manutenção:");

        jLabel2.setText("Data:");

        jLabel3.setText("Tipo:");

        jLabel4.setText("Custo:");

        jtxIdManutencao.setText("jTextField1");

        jtxDataManutencao.setText("jTextField2");

        jtxTipo.setText("jTextField3");

        jtxCusto.setText("jTextField4");

        jLabel5.setText("Placa do Veículo:");

        jtxPlacaVeiculo.setText("jTextField5");

        jLabel6.setText("Descrição:");

        jtxDesc.setText("jTextField6");

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

        jtManutencao.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID", "Data", "Tipo", "Custo", "Placa do Veículo", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
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
        jtManutencao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtManutencaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtManutencao);
        if (jtManutencao.getColumnModel().getColumnCount() > 0) {
            jtManutencao.getColumnModel().getColumn(0).setResizable(false);
            jtManutencao.getColumnModel().getColumn(1).setResizable(false);
            jtManutencao.getColumnModel().getColumn(2).setResizable(false);
            jtManutencao.getColumnModel().getColumn(3).setResizable(false);
            jtManutencao.getColumnModel().getColumn(4).setResizable(false);
            jtManutencao.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir)
                                .addGap(12, 12, 12)
                                .addComponent(jblPesquisar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxDataManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxIdManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxIdManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtxPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxDataManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jtxDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jtxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
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
        String codigo = jtxIdManutencao.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir a manutenção " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
        String codigo = jtxIdManutencao.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtManutencao.getRowCount(); i++){
                if(texto(jtManutencao.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtManutencao.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }
    }//GEN-LAST:event_jblPesquisarMouseClicked

    private void jtManutencaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtManutencaoMouseClicked
        int linha = jtManutencao.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
    }//GEN-LAST:event_jtManutencaoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaManutencao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaManutencao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaManutencao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaManutencao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaManutencao().setVisible(true);
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
    private javax.swing.JTable jtManutencao;
    private javax.swing.JTextField jtxCusto;
    private javax.swing.JTextField jtxDataManutencao;
    private javax.swing.JTextField jtxDesc;
    private javax.swing.JTextField jtxIdManutencao;
    private javax.swing.JTextField jtxPlacaVeiculo;
    private javax.swing.JTextField jtxTipo;
    // End of variables declaration//GEN-END:variables
}
