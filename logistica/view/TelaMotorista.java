/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package logistica.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logistica.controller.CategoriaCnhController;
import logistica.controller.MotoristaCategoriaController;
import logistica.controller.MotoristaController;
import logistica.controller.TelefoneMotoristaController;
import logistica.model.CategoriaCnhModel;
import logistica.model.MotoristaCategoriaModel;
import logistica.model.MotoristaModel;
import logistica.model.TelefoneMotoristaModel;
/**
 *
 * @author User
 */
public class TelaMotorista extends javax.swing.JFrame {

    MotoristaController controller = new MotoristaController();
    TelefoneMotoristaController telefoneController = new TelefoneMotoristaController();
    MotoristaCategoriaController motoristaCategoriaController = new MotoristaCategoriaController();
    CategoriaCnhController categoriaController = new CategoriaCnhController();

    public TelaMotorista() {
        initComponents();
        limparCampos();
        jtMotorista.getColumnModel().getColumn(2).setHeaderValue("CPF");
        jtMotorista.getTableHeader().repaint();
        jtxValidade.setToolTipText("dd/mm/aaaa");
        jcbCategoriaCNH.removeAllItems();
        jcbCategoriaCNH.addItem("B");
        jcbCategoriaCNH.addItem("C");
        jcbCategoriaCNH.addItem("E");
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return jtxCodigo.getText().trim().isEmpty()
                || jtxNome.getText().trim().isEmpty()
                || jtxCPF.getText().trim().isEmpty()
                || jtxNoCNH.getText().trim().isEmpty()
                || jtxValidade.getText().trim().isEmpty()
                || jtxTelefone.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxCodigo.setText("");
        jtxNome.setText("");
        jtxCPF.setText("");
        jtxNoCNH.setText("");
        jtxValidade.setText("");
        jtxTelefone.setText("");
        jtMotorista.clearSelection();
        //habilitar botões
        jbEditar.setEnabled(true);
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

    private String digitos(String valor){
        return valor.replaceAll("\\D", "");
    }

    // Devolve o código da categoria; se ainda não existir no banco, cadastra com o próximo código livre.
    private int idDaCategoria(String tipo){
        int maior = 0;
        for(CategoriaCnhModel c : categoriaController.listar()){
            if(c.getTipoCnh().equalsIgnoreCase(tipo)){
                return c.getId();
            }
            if(c.getId() > maior){
                maior = c.getId();
            }
        }
        categoriaController.cadastrar(maior + 1, tipo);
        for(CategoriaCnhModel c : categoriaController.listar()){
            if(c.getTipoCnh().equalsIgnoreCase(tipo)){
                return c.getId();
            }
        }
        return -1;
    }
    private TelefoneMotoristaModel buscarTelefone(int idMotorista){
        for(TelefoneMotoristaModel t : telefoneController.lista()){
            if(t.getMotorista().getId() == idMotorista){
                return t;
            }
        }
        return null;
    }
    private MotoristaCategoriaModel buscarCategoria(int idMotorista){
        for(MotoristaCategoriaModel c : motoristaCategoriaController.listar()){
            if(c.getMotorista().getId() == idMotorista){
                return c;
            }
        }
        return null;
    }
    private void selecionarCategoria(String tipo){
        if(tipo.isEmpty()){
            return;
        }
        for(int i = 0; i < jcbCategoriaCNH.getItemCount(); i++){
            if(jcbCategoriaCNH.getItemAt(i).equalsIgnoreCase(tipo)){
                jcbCategoriaCNH.setSelectedIndex(i);
                return;
            }
        }
        jcbCategoriaCNH.addItem(tipo);
        jcbCategoriaCNH.setSelectedItem(tipo);
    }
    private void carregarLinha(int linha){
        jtxCodigo.setText(texto(jtMotorista.getValueAt(linha, 0)));
        jtxNome.setText(texto(jtMotorista.getValueAt(linha, 1)));
        jtxCPF.setText(texto(jtMotorista.getValueAt(linha, 2)));
        jtxNoCNH.setText(texto(jtMotorista.getValueAt(linha, 3)));
        selecionarCategoria(texto(jtMotorista.getValueAt(linha, 4)));
        jtxValidade.setText(texto(jtMotorista.getValueAt(linha, 5)));
        jtxTelefone.setText(texto(jtMotorista.getValueAt(linha, 6)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtMotorista.getModel();
        modelo.setRowCount(0);
        try{
            java.util.ArrayList<TelefoneMotoristaModel> telefones = telefoneController.lista();
            java.util.ArrayList<MotoristaCategoriaModel> categorias = motoristaCategoriaController.listar();
            for(MotoristaModel m : controller.listar()){
                String fone = "";
                String tipo = "";
                for(TelefoneMotoristaModel t : telefones){
                    if(t.getMotorista().getId() == m.getId()){
                        fone = "(" + t.getDdd() + ") " + t.getNumero();
                        break;
                    }
                }
                for(MotoristaCategoriaModel c : categorias){
                    if(c.getMotorista().getId() == m.getId()){
                        tipo = c.getCategoria().getTipoCnh();
                        break;
                    }
                }
                modelo.addRow(new Object[]{m.getId(), m.getNome(), m.getCpf(), m.getNumeroCnh(), tipo,
                    m.getValidadeCnh(), fone});
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
            String nome = jtxNome.getText().trim();
            String cpf = digitos(jtxCPF.getText());
            String cnh = digitos(jtxNoCNH.getText());
            String validade = lerData(jtxValidade.getText(), "Validade da CNH");
            String fone = digitos(jtxTelefone.getText());
            if(fone.length() != 11){
                mostrar("Telefone invalido! Informe o DDD e 9 digitos. Ex: (35) 999998888");
                return;
            }
            String tipo = String.valueOf(jcbCategoriaCNH.getSelectedItem());
            String msg = novo
                    ? controller.cadastrar(id, nome, cpf, cnh, validade)
                    : controller.atualizar(id, nome, cpf, cnh, validade);
            boolean ok = sucesso(msg);
            StringBuilder resposta = new StringBuilder(msg);
            if(ok){
                TelefoneMotoristaModel telefone = buscarTelefone(id);
                String msgFone = telefone == null
                        ? telefoneController.cadastrar(id, fone.substring(0, 2), fone.substring(2), id)
                        : telefoneController.atualizar(telefone.getId(), fone.substring(0, 2), fone.substring(2), id);
                String msgCategoria;
                int idCategoria = idDaCategoria(tipo);
                if(idCategoria < 0){
                    msgCategoria = "Nao foi possivel cadastrar a Categoria de CNH!";
                }else if(buscarCategoria(id) == null){
                    msgCategoria = motoristaCategoriaController.cadastrar(id, idCategoria);
                }else{
                    msgCategoria = motoristaCategoriaController.atualizar(id, idCategoria);
                }
                resposta.append("\n").append(msgFone).append("\n").append(msgCategoria);
                ok = sucesso(msgFone) && sucesso(msgCategoria);
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

        jbSalvar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
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
        jtxTelefone = new javax.swing.JTextField();
        jblPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMotorista = new javax.swing.JTable();
        jlCodigo = new javax.swing.JLabel();
        jtxCodigo = new javax.swing.JTextField();
        jbEditar = new javax.swing.JButton();
        jcbCategoriaCNH = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbExcluirMouseClicked(evt);
            }
        });

        jlNome.setText("Nome:");

        jlCPF.setText("CPF:");

        jlNoCNH.setText("n° da CNH:");

        jlCategoria.setText("Categoria da CNH:");

        jlValidade.setText("Validade da CNH:");

        jlTelefone.setText("Telefone:");

        jtxNome.setText("jTextField1");

        jtxCPF.setText("jTextField2");

        jtxNoCNH.setText("jTextField3");

        jtxValidade.setText("jTextField1");

        jtxTelefone.setText("jTextField1");

        jblPesquisar.setText("Pesquisar");
        jblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblPesquisarMouseClicked(evt);
            }
        });

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
                "Código", "Nome", "CPF/CNPJ", "n° da CNH", "Categoria da CNH", "Validdade da CNH", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtMotorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMotoristaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtMotorista);

        jlCodigo.setText("Código:");

        jtxCodigo.setText("jTextField1");

        jbEditar.setText("Editar");
        jbEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbEditarMouseClicked(evt);
            }
        });

        jcbCategoriaCNH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlValidade)
                            .addComponent(jlCategoria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbCategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(3, 3, 3)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jblPesquisar)))
                .addContainerGap(425, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
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
                    .addComponent(jtxTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbExcluir)
                    .addComponent(jblPesquisar)
                    .addComponent(jbEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //botão salvar
    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        gravar(true);
    }//GEN-LAST:event_jbSalvarActionPerformed
        
    private void jtMotoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMotoristaMouseClicked
        int linha = jtMotorista.getSelectedRow();
        if(linha < 0){
            return;
        }
        carregarLinha(linha);
    }//GEN-LAST:event_jtMotoristaMouseClicked
    //excluir
    private void jbExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbExcluirMouseClicked
        String codigo = jtxCodigo.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o motorista " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
    //pesquisar
    private void jblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblPesquisarMouseClicked
        String codigo = jtxCodigo.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtMotorista.getRowCount(); i++){
                if(texto(jtMotorista.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtMotorista.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }
    }//GEN-LAST:event_jblPesquisarMouseClicked
    //botão editar
    private void jbEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbEditarMouseClicked
        gravar(false);
    }//GEN-LAST:event_jbEditarMouseClicked
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
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jblPesquisar;
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
    private javax.swing.JTextField jtxNoCNH;
    private javax.swing.JTextField jtxNome;
    private javax.swing.JTextField jtxTelefone;
    private javax.swing.JTextField jtxValidade;
    // End of variables declaration//GEN-END:variables
}
