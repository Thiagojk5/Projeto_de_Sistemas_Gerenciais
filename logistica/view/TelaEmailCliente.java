/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package logistica.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logistica.controller.ClienteController;
import logistica.controller.EmailClienteController;
import logistica.model.ClienteModel;
import logistica.model.EmailClienteModel;

/**
 *
 * @author User
 */
public class TelaEmailCliente extends javax.swing.JFrame {

    EmailClienteController controller = new EmailClienteController();
    ClienteController clienteController = new ClienteController();

    public TelaEmailCliente() {
        initComponents();
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return jtxCodigo.getText().trim().isEmpty()
                || jtxEmail.getText().trim().isEmpty()
                || jtxIdCliente.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxCodigo.setText("");
        jtxEmail.setText("");
        jtxIdCliente.setText("");
        jtEmail.clearSelection();
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

    private boolean clienteExiste(int id){
        for(ClienteModel item : clienteController.listar()){
            if(item.getId() == id){
                return true;
            }
        }
        return false;
    }

    private void carregarLinha(int linha){
        jtxCodigo.setText(texto(jtEmail.getValueAt(linha, 0)));
        jtxEmail.setText(texto(jtEmail.getValueAt(linha, 1)));
        jtxIdCliente.setText(texto(jtEmail.getValueAt(linha, 2)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtEmail.getModel();
        modelo.setRowCount(0);
        try{
            for(EmailClienteModel e : controller.listar()){
                modelo.addRow(new Object[]{e.getId(), e.getEmail(), e.getCliente().getId(), e.getCliente().getNome()});
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
            String email = jtxEmail.getText().trim();
            int idCliente = lerInt(jtxIdCliente.getText(), "Codigo do cliente");
            if(!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")){
                mostrar("E-mail invalido!");
                return;
            }
            if(!clienteExiste(idCliente)){
                mostrar("Cliente nao encontrado!");
                return;
            }
            String msg = novo ? controller.cadastrar(id, email, idCliente) : controller.atualizar(id, email, idCliente);
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

    private void excluir(){
        String codigo = jtxCodigo.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir o e-mail " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
    }

    private void pesquisar(){
        String codigo = jtxCodigo.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtEmail.getRowCount(); i++){
                if(texto(jtEmail.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtEmail.setRowSelectionInterval(i, i);
                    carregarLinha(i);
                    return;
                }
            }
            mostrar("Nenhum registro encontrado com o código " + id + "!");
        }catch(IllegalArgumentException e){
            mostrar(e.getMessage());
        }
    }

    // Tela montada por código (não usa arquivo .form).
    private void initComponents() {
        setTitle("E-mail do Cliente");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtxCodigo = new JTextField(10);
        jtxEmail = new JTextField(25);
        jtxIdCliente = new JTextField(10);

        JPanel campos = new JPanel(new GridBagLayout());
        adicionar(campos, "Código:", jtxCodigo, 0, 0);
        adicionar(campos, "E-mail:", jtxEmail, 0, 1);
        adicionar(campos, "Código do Cliente:", jtxIdCliente, 1, 0);

        jbSalvar = new JButton("Salvar");
        JbEditar = new JButton("Editar");
        jbExcluir = new JButton("Excluir");
        jblPesquisar = new JButton("Pesquisar");
        jbSalvar.addActionListener(e -> gravar(true));
        JbEditar.addActionListener(e -> gravar(false));
        jbExcluir.addActionListener(e -> excluir());
        jblPesquisar.addActionListener(e -> pesquisar());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botoes.add(jbSalvar);
        botoes.add(JbEditar);
        botoes.add(jbExcluir);
        botoes.add(jblPesquisar);

        jtEmail = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Código", "E-mail", "Cód. Cliente", "Cliente"}) {
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        });
        jtEmail.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = jtEmail.getSelectedRow();
                if(linha >= 0){
                    carregarLinha(linha);
                }
            }
        });
        JScrollPane rolagem = new JScrollPane(jtEmail);
        rolagem.setPreferredSize(new Dimension(680, 220));

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(campos, BorderLayout.CENTER);
        topo.add(botoes, BorderLayout.SOUTH);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topo, BorderLayout.NORTH);
        getContentPane().add(rolagem, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionar(JPanel painel, String rotulo, java.awt.Component campo, int linha, int coluna){
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = linha;
        g.insets = new Insets(4, 6, 4, 6);
        g.anchor = GridBagConstraints.WEST;
        g.gridx = coluna * 2;
        painel.add(new JLabel(rotulo), g);
        g.gridx = coluna * 2 + 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        painel.add(campo, g);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEmailCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEmailCliente().setVisible(true);
            }
        });
    }

    private JButton JbEditar;
    private JButton jbExcluir;
    private JButton jbSalvar;
    private JButton jblPesquisar;
    private JTable jtEmail;
    private JTextField jtxCodigo;
    private JTextField jtxEmail;
    private JTextField jtxIdCliente;
}
