/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.ClienteController;
import logistica.controller.EnderecoController;
import logistica.model.ClienteModel;
import logistica.model.EnderecoModel;
/**
 *
 * @author User
 */
public class TelaEndereco extends javax.swing.JFrame {

    EnderecoController controller = new EnderecoController();
    ClienteController clienteController = new ClienteController();
    private static final String[] UFS = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
        "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};

    public TelaEndereco() {
        initComponents();
        limparCampos();
        // o endereço é ligado ao cliente pelo código (id_cliente)
        jLabel8.setText("Código do Cliente:");
        jtEnd.getColumnModel().getColumn(7).setHeaderValue("Cód. Cliente");
        jtEnd.getTableHeader().repaint();
        jcbUF.removeAllItems();
        for(String uf : UFS){
            jcbUF.addItem(uf);
        }
        jcbUF.setSelectedItem("MG");
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return jtxIdEnd.getText().trim().isEmpty()
                || jtxLogradouro.getText().trim().isEmpty()
                || jtxNumero.getText().trim().isEmpty()
                || jtxBairro.getText().trim().isEmpty()
                || jtxCEP.getText().trim().isEmpty()
                || jtxCid.getText().trim().isEmpty()
                || jtxNomeCli.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdEnd.setText("");
        jtxLogradouro.setText("");
        jtxNumero.setText("");
        jtxBairro.setText("");
        jtxCEP.setText("");
        jtxCid.setText("");
        jtxNomeCli.setText("");
        jtEnd.clearSelection();
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

    private String digitos(String valor){
        return valor.replaceAll("\\D", "");
    }

    private boolean clienteExiste(int id){
        for(ClienteModel c : clienteController.listar()){
            if(c.getId() == id){
                return true;
            }
        }
        return false;
    }
    private void carregarLinha(int linha){
        jtxIdEnd.setText(texto(jtEnd.getValueAt(linha, 0)));
        jtxLogradouro.setText(texto(jtEnd.getValueAt(linha, 1)));
        jtxNumero.setText(texto(jtEnd.getValueAt(linha, 2)));
        jtxBairro.setText(texto(jtEnd.getValueAt(linha, 3)));
        jtxCEP.setText(texto(jtEnd.getValueAt(linha, 4)));
        jtxCid.setText(texto(jtEnd.getValueAt(linha, 5)));
        jcbUF.setSelectedItem(texto(jtEnd.getValueAt(linha, 6)));
        jtxNomeCli.setText(texto(jtEnd.getValueAt(linha, 7)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtEnd.getModel();
        modelo.setRowCount(0);
        try{
            for(EnderecoModel e : controller.listar()){
                modelo.addRow(new Object[]{e.getId(), e.getLogradouro(), e.getNumero(), e.getBairro(),
                    e.getCep(), e.getCidade(), e.getUf(), String.valueOf(e.getCliente().getId())});
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
            int id = lerInt(jtxIdEnd.getText(), "Id do endereco");
            int idCliente = lerInt(jtxNomeCli.getText(), "Codigo do cliente");
            if(!clienteExiste(idCliente)){
                mostrar("Cliente nao encontrado!");
                return;
            }
            String cep = digitos(jtxCEP.getText());
            String uf = String.valueOf(jcbUF.getSelectedItem());
            String logradouro = jtxLogradouro.getText().trim();
            String numero = jtxNumero.getText().trim();
            String bairro = jtxBairro.getText().trim();
            String cidade = jtxCid.getText().trim();
            String msg = novo
                    ? controller.cadastrar(id, cep, logradouro, numero, bairro, cidade, uf, idCliente)
                    : controller.atualizar(id, cep, logradouro, numero, bairro, cidade, uf, idCliente);
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
        jtxNomeCli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEnd = new javax.swing.JTable();
        jbSalvar = new javax.swing.JButton();
        JbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jblPesquisar = new javax.swing.JButton();
        jcbUF = new javax.swing.JComboBox<>();

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

        jLabel8.setText("Nome do Cliente:");

        jtxNomeCli.setText("jTextField7");

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
                "Id do Endereco", "Logradouro", "N°", "Bairro", "CEP", "Cidade", "UF", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jcbUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(jtxNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir)
                        .addGap(12, 12, 12)
                        .addComponent(jblPesquisar))
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
                            .addComponent(jtxCid))))
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
                    .addComponent(jtxNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
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
        String codigo = jtxIdEnd.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o endereço " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
        String codigo = jtxIdEnd.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtEnd.getRowCount(); i++){
                if(texto(jtEnd.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtEnd.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }
    }//GEN-LAST:event_jblPesquisarMouseClicked

    private void jtEndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtEndMouseClicked
        int linha = jtEnd.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
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
    private javax.swing.JButton JbEditar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
    private javax.swing.JComboBox<String> jcbUF;
    private javax.swing.JLabel jlIdEnd;
    private javax.swing.JLabel jlLogradouro;
    private javax.swing.JLabel jlNum;
    private javax.swing.JTable jtEnd;
    private javax.swing.JTextField jtxBairro;
    private javax.swing.JTextField jtxCEP;
    private javax.swing.JTextField jtxCid;
    private javax.swing.JTextField jtxIdEnd;
    private javax.swing.JTextField jtxLogradouro;
    private javax.swing.JTextField jtxNomeCli;
    private javax.swing.JTextField jtxNumero;
    // End of variables declaration//GEN-END:variables
}
