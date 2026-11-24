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
import logistica.controller.CategoriaCnhController;
import logistica.model.CategoriaCnhModel;

/**
 *
 * @author User
 */
public class TelaCategoriaCnh extends javax.swing.JFrame {

    CategoriaCnhController controller = new CategoriaCnhController();

    public TelaCategoriaCnh() {
        initComponents();
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica se todos os campos estão preencidos.
        return jtxCodigo.getText().trim().isEmpty()
                || jtxTipo.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxCodigo.setText("");
        jtxTipo.setText("");
        jtCategoria.clearSelection();
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

    private void carregarLinha(int linha){
        jtxCodigo.setText(texto(jtCategoria.getValueAt(linha, 0)));
        jtxTipo.setText(texto(jtCategoria.getValueAt(linha, 1)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtCategoria.getModel();
        modelo.setRowCount(0);
        try{
            for(CategoriaCnhModel c : controller.listar()){
                modelo.addRow(new Object[]{c.getId(), c.getTipoCnh()});
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
            String tipo = jtxTipo.getText().trim().toUpperCase();
            String msg = novo ? controller.cadastrar(id, tipo) : controller.atualizar(id, tipo);
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
            if(JOptionPane.showConfirmDialog(this, "Excluir a categoria " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
                return;
            }
            String msg = controller.removerPorId(id);
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
            for(int i = 0; i < jtCategoria.getRowCount(); i++){
                if(texto(jtCategoria.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtCategoria.setRowSelectionInterval(i, i);
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
        setTitle("Categoria de CNH");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtxCodigo = new JTextField(10);
        jtxTipo = new JTextField(6);
        jtxTipo.setToolTipText("Até 2 letras");

        JPanel campos = new JPanel(new GridBagLayout());
        adicionar(campos, "Código:", jtxCodigo, 0, 0);
        adicionar(campos, "Tipo (A, B, C, D, E, AB...):", jtxTipo, 0, 1);

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

        jtCategoria = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Código", "Tipo"}) {
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        });
        jtCategoria.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = jtCategoria.getSelectedRow();
                if(linha >= 0){
                    carregarLinha(linha);
                }
            }
        });
        JScrollPane rolagem = new JScrollPane(jtCategoria);
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
            java.util.logging.Logger.getLogger(TelaCategoriaCnh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCategoriaCnh().setVisible(true);
            }
        });
    }

    private JButton JbEditar;
    private JButton jbExcluir;
    private JButton jbSalvar;
    private JButton jblPesquisar;
    private JTable jtCategoria;
    private JTextField jtxCodigo;
    private JTextField jtxTipo;
}
