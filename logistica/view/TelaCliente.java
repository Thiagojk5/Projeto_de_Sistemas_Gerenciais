/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.ClienteController;
import logistica.controller.EmailClienteController;
import logistica.controller.TelefoneClienteController;
import logistica.model.ClienteModel;
import logistica.model.EmailClienteModel;
import logistica.model.TelefoneClienteModel;
/**
 *
 * @author User
 */
public class TelaCliente extends javax.swing.JFrame {

    ClienteController controller = new ClienteController();
    TelefoneClienteController telefoneController = new TelefoneClienteController();
    EmailClienteController emailController = new EmailClienteController();

    public TelaCliente() {
        initComponents();
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return JtxCliTelefone.getText().trim().isEmpty()
                || jtxCPF.getText().trim().isEmpty()
                || jtxCliEmail.getText().trim().isEmpty()
                || jtxCliNome.getText().trim().isEmpty()
                || jtxCodigo.getText().trim().isEmpty();
    }
    private void limparCampos(){
        JtxCliTelefone.setText("");
        jtxCPF.setText("");
        jtxCliEmail.setText("");
        jtxCliNome.setText("");
        jtxCodigo.setText("");
        jtCliente.clearSelection();
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
        for(ClienteModel c : controller.listar()){
            if(c.getId() == id){
                return true;
            }
        }
        return false;
    }
    // Telefone e e-mail ficam em tabelas próprias; aqui cada cliente tem um de cada.
    private TelefoneClienteModel buscarTelefone(int idCliente){
        for(TelefoneClienteModel t : telefoneController.lista()){
            if(t.getCliente().getId() == idCliente){
                return t;
            }
        }
        return null;
    }
    private EmailClienteModel buscarEmail(int idCliente){
        for(EmailClienteModel e : emailController.listar()){
            if(e.getCliente().getId() == idCliente){
                return e;
            }
        }
        return null;
    }
    private void carregarLinha(int linha){
        jtxCodigo.setText(texto(jtCliente.getValueAt(linha, 0)));
        jtxCliNome.setText(texto(jtCliente.getValueAt(linha, 1)));
        jtxCPF.setText(texto(jtCliente.getValueAt(linha, 2)));
        JtxCliTelefone.setText(texto(jtCliente.getValueAt(linha, 3)));
        jtxCliEmail.setText(texto(jtCliente.getValueAt(linha, 4)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtCliente.getModel();
        modelo.setRowCount(0);
        try{
            java.util.ArrayList<TelefoneClienteModel> telefones = telefoneController.lista();
            java.util.ArrayList<EmailClienteModel> emails = emailController.listar();
            for(ClienteModel c : controller.listar()){
                String fone = "";
                String mail = "";
                for(TelefoneClienteModel t : telefones){
                    if(t.getCliente().getId() == c.getId()){
                        fone = "(" + t.getDdd() + ") " + t.getNumero();
                        break;
                    }
                }
                for(EmailClienteModel e : emails){
                    if(e.getCliente().getId() == c.getId()){
                        mail = e.getEmail();
                        break;
                    }
                }
                modelo.addRow(new Object[]{c.getId(), c.getNome(), c.getCpfCNPJ(), fone, mail});
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
            int id = lerInt(jtxCodigo.getText(), "Codigo");
            String nome = jtxCliNome.getText().trim();
            String cpfCnpj = digitos(jtxCPF.getText());
            String fone = digitos(JtxCliTelefone.getText());
            String email = jtxCliEmail.getText().trim();
            if(fone.length() != 11){
                mostrar("Telefone invalido! Informe o DDD e 9 digitos. Ex: (35) 999998888");
                return;
            }
            if(!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")){
                mostrar("E-mail invalido!");
                return;
            }
            String ddd = fone.substring(0, 2);
            String numero = fone.substring(2);
            String msg = novo ? controller.cadastrar(id, nome, cpfCnpj) : controller.atualizar(id, nome, cpfCnpj);
            boolean ok = sucesso(msg);
            StringBuilder resposta = new StringBuilder(msg);
            if(ok){
                TelefoneClienteModel telefone = buscarTelefone(id);
                String msgFone = telefone == null
                        ? telefoneController.cadastrar(id, ddd, numero, id)
                        : telefoneController.atualizar(telefone.getId(), ddd, numero, id);
                EmailClienteModel mail = buscarEmail(id);
                String msgMail = mail == null
                        ? emailController.cadastrar(id, email, id)
                        : emailController.atualizar(mail.getId(), email, id);
                resposta.append("\n").append(msgFone).append("\n").append(msgMail);
                ok = sucesso(msgFone) && sucesso(msgMail);
            }
            mostrar(resposta.toString());
            preencherTabela();
            if(ok){
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

        jlCliNome = new javax.swing.JLabel();
        jlCliCPF = new javax.swing.JLabel();
        jlCLiTelefone = new javax.swing.JLabel();
        jlCliEmail = new javax.swing.JLabel();
        jtxCliNome = new javax.swing.JTextField();
        jtxCPF = new javax.swing.JTextField();
        JtxCliTelefone = new javax.swing.JTextField();
        jtxCliEmail = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        JbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();
        jlCodigo = new javax.swing.JLabel();
        jtxCodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlCliNome.setText("Nome:");

        jlCliCPF.setText("CPF/CNPJ:");

        jlCLiTelefone.setText("Telefone:");

        jlCliEmail.setText("E-mail:");

        jtxCliNome.setText("jTextField1");

        jtxCPF.setText("jTextField2");

        JtxCliTelefone.setText("jTextField3");

        jtxCliEmail.setText("jTextField4");

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

        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
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
                "Código do cliente", "Nome", "CPF", "Telefone", "E-mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCliente);
        if (jtCliente.getColumnModel().getColumnCount() > 0) {
            jtCliente.getColumnModel().getColumn(0).setResizable(false);
            jtCliente.getColumnModel().getColumn(1).setResizable(false);
            jtCliente.getColumnModel().getColumn(2).setResizable(false);
            jtCliente.getColumnModel().getColumn(3).setResizable(false);
            jtCliente.getColumnModel().getColumn(4).setResizable(false);
        }

        jlCodigo.setText("Código:");

        jtxCodigo.setText("jTextField1");

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
                                .addComponent(jtxCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCliEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jlCLiTelefone)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JtxCliTelefone))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jlCliCPF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JbEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbExcluir)
                                .addGap(12, 12, 12)
                                .addComponent(jblPesquisar)))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliNome)
                    .addComponent(jtxCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliCPF)
                    .addComponent(jtxCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCodigo)
                    .addComponent(jtxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCLiTelefone)
                    .addComponent(JtxCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCliEmail)
                    .addComponent(jtxCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(JbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtClienteMouseClicked
        int linha = jtCliente.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
    }//GEN-LAST:event_jtClienteMouseClicked
    //Ação do botão salvar
    private void jbSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSalvarMouseClicked
        gravar(true);
    }//GEN-LAST:event_jbSalvarMouseClicked
    //Ação do botão editar
    private void JbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbEditarMouseClicked
        gravar(false);
    }//GEN-LAST:event_JbEditarMouseClicked
    //Ação do botão excluir
    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        String codigo = jtxCodigo.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o cliente " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
    //Ação do botão pesquisar
    private void jblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblPesquisarMouseClicked
        String codigo = jtxCodigo.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtCliente.getRowCount(); i++){
                if(texto(jtCliente.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtCliente.setRowSelectionInterval(i, i);
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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbEditar;
    private javax.swing.JTextField JtxCliTelefone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
    private javax.swing.JLabel jlCLiTelefone;
    private javax.swing.JLabel jlCliCPF;
    private javax.swing.JLabel jlCliEmail;
    private javax.swing.JLabel jlCliNome;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTextField jtxCPF;
    private javax.swing.JTextField jtxCliEmail;
    private javax.swing.JTextField jtxCliNome;
    private javax.swing.JTextField jtxCodigo;
    // End of variables declaration//GEN-END:variables
}
