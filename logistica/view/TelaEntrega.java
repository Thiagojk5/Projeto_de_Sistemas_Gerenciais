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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logistica.controller.ClienteController;
import logistica.controller.EnderecoController;
import logistica.controller.EntregaController;
import logistica.controller.FuncionarioController;
import logistica.controller.RotaController;
import logistica.model.ClienteModel;
import logistica.model.EnderecoModel;
import logistica.model.EntregaModel;
import logistica.model.FuncionarioModel;
import logistica.model.RotaModel;
/**
 *
 * @author User
 */
public class TelaEntrega extends javax.swing.JFrame {

    EntregaController controller = new EntregaController();
    ClienteController clienteController = new ClienteController();
    EnderecoController enderecoController = new EnderecoController();
    RotaController rotaController = new RotaController();
    FuncionarioController funcionarioController = new FuncionarioController();

    public TelaEntrega() {
        initComponents();
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica os campos obrigatórios (Id é gerado pelo banco; data de realização é opcional).
        return jtxDataSolicitacao.getText().trim().isEmpty()
                || jtxDataPrevista.getText().trim().isEmpty()
                || jtxValorFrete.getText().trim().isEmpty()
                || jtxIdCliente.getText().trim().isEmpty()
                || jtxIdEndereco.getText().trim().isEmpty()
                || jtxIdRota.getText().trim().isEmpty()
                || jtxIdFuncionario.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdEntrega.setText("");
        jtxDataSolicitacao.setText("");
        jtxDataPrevista.setText("");
        jtxDataRealizacao.setText("");
        jtxValorFrete.setText("");
        jtxIdCliente.setText("");
        jtxIdEndereco.setText("");
        jtxIdRota.setText("");
        jtxIdFuncionario.setText("");
        jcbStatus.setSelectedIndex(0);
        jtEntrega.clearSelection();
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

    private boolean clienteExiste(int id){
        for(ClienteModel c : clienteController.listar()){
            if(c.getId() == id){
                return true;
            }
        }
        return false;
    }
    private boolean enderecoExiste(int id){
        for(EnderecoModel e : enderecoController.listar()){
            if(e.getId() == id){
                return true;
            }
        }
        return false;
    }
    private boolean rotaExiste(int id){
        for(RotaModel r : rotaController.listar()){
            if(r.getId() == id){
                return true;
            }
        }
        return false;
    }
    private boolean funcionarioExiste(int id){
        for(FuncionarioModel f : funcionarioController.listar()){
            if(f.getId() == id){
                return true;
            }
        }
        return false;
    }
    private void selecionarStatus(String status){
        if(status.isEmpty()){
            return;
        }
        for(int i = 0; i < jcbStatus.getItemCount(); i++){
            if(jcbStatus.getItemAt(i).equalsIgnoreCase(status)){
                jcbStatus.setSelectedIndex(i);
                return;
            }
        }
        jcbStatus.addItem(status);
        jcbStatus.setSelectedItem(status);
    }

    private void carregarLinha(int linha){
        jtxIdEntrega.setText(texto(jtEntrega.getValueAt(linha, 0)));
        jtxDataSolicitacao.setText(texto(jtEntrega.getValueAt(linha, 1)));
        jtxDataPrevista.setText(texto(jtEntrega.getValueAt(linha, 2)));
        jtxDataRealizacao.setText(texto(jtEntrega.getValueAt(linha, 3)));
        jtxValorFrete.setText(texto(jtEntrega.getValueAt(linha, 4)));
        jtxIdCliente.setText(texto(jtEntrega.getValueAt(linha, 5)));
        jtxIdEndereco.setText(texto(jtEntrega.getValueAt(linha, 6)));
        jtxIdRota.setText(texto(jtEntrega.getValueAt(linha, 7)));
        jtxIdFuncionario.setText(texto(jtEntrega.getValueAt(linha, 8)));
        selecionarStatus(texto(jtEntrega.getValueAt(linha, 9)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtEntrega.getModel();
        modelo.setRowCount(0);
        try{
            for(EntregaModel e : controller.listar()){
                modelo.addRow(new Object[]{e.getId(), e.getDataSolicitacao(), e.getDataPrevista(),
                    texto(e.getDataRealizacao()), e.getValorDoFrete(), e.getCliente().getId(),
                    e.getEndereco().getId(), e.getRota().getId(), e.getFuncionario().getId(), e.getStatus()});
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
        if(!novo && jtxIdEntrega.getText().trim().isEmpty()){
            mostrar("Selecione uma entrega na tabela (ou informe o Id) para editar!");
            return;
        }
        try{
            int id = novo ? 0 : lerInt(jtxIdEntrega.getText(), "Id da entrega");
            String solicitacao = lerData(jtxDataSolicitacao.getText(), "Data de solicitacao");
            String prevista = lerData(jtxDataPrevista.getText(), "Data prevista");
            String realizacao = jtxDataRealizacao.getText().trim().isEmpty()
                    ? null : lerData(jtxDataRealizacao.getText(), "Data de realizacao");
            float valor = lerFloat(jtxValorFrete.getText(), "Valor do frete");
            int idCliente = lerInt(jtxIdCliente.getText(), "Codigo do cliente");
            int idEndereco = lerInt(jtxIdEndereco.getText(), "Codigo do endereco");
            int idRota = lerInt(jtxIdRota.getText(), "Codigo da rota");
            int idFuncionario = lerInt(jtxIdFuncionario.getText(), "Codigo do funcionario");
            String status = String.valueOf(jcbStatus.getSelectedItem());
            if(!clienteExiste(idCliente)){
                mostrar("Cliente nao encontrado!");
                return;
            }
            if(!enderecoExiste(idEndereco)){
                mostrar("Endereco nao encontrado!");
                return;
            }
            if(!rotaExiste(idRota)){
                mostrar("Rota nao encontrada!");
                return;
            }
            if(!funcionarioExiste(idFuncionario)){
                mostrar("Funcionario nao encontrado!");
                return;
            }
            String msg = novo
                    ? controller.cadastrar(valor, solicitacao, prevista, realizacao, status, idCliente, idEndereco, idRota, idFuncionario)
                    : controller.atualizar(id, valor, solicitacao, prevista, realizacao, status, idCliente, idEndereco, idRota, idFuncionario);
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
        String codigo = jtxIdEntrega.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir a entrega " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
        String codigo = jtxIdEntrega.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtEntrega.getRowCount(); i++){
                if(texto(jtEntrega.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtEntrega.setRowSelectionInterval(i, i);
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
        setTitle("Entrega");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxIdEntrega = new JTextField(10);
        jtxIdEntrega.setToolTipText("Gerado automaticamente. Informe só para Editar, Excluir ou Pesquisar.");
        jtxDataSolicitacao = new JTextField(10);
        jtxDataSolicitacao.setToolTipText("dd/mm/aaaa");
        jtxDataPrevista = new JTextField(10);
        jtxDataPrevista.setToolTipText("dd/mm/aaaa");
        jtxDataRealizacao = new JTextField(10);
        jtxDataRealizacao.setToolTipText("dd/mm/aaaa (opcional)");
        jtxValorFrete = new JTextField(10);
        jtxIdCliente = new JTextField(10);
        jtxIdEndereco = new JTextField(10);
        jtxIdRota = new JTextField(10);
        jtxIdFuncionario = new JTextField(10);
        jcbStatus = new JComboBox<>(new String[]{"A combinar", "Marcada", "Cancelada"});

        JPanel campos = new JPanel(new GridBagLayout());
        adicionar(campos, "Id da Entrega:", jtxIdEntrega, 0, 0);
        adicionar(campos, "Status:", jcbStatus, 0, 1);
        adicionar(campos, "Data de Solicitação:", jtxDataSolicitacao, 1, 0);
        adicionar(campos, "Data Prevista:", jtxDataPrevista, 1, 1);
        adicionar(campos, "Data de Realização:", jtxDataRealizacao, 2, 0);
        adicionar(campos, "Valor do Frete:", jtxValorFrete, 2, 1);
        adicionar(campos, "Código do Cliente:", jtxIdCliente, 3, 0);
        adicionar(campos, "Código do Endereço:", jtxIdEndereco, 3, 1);
        adicionar(campos, "Código da Rota:", jtxIdRota, 4, 0);
        adicionar(campos, "Código do Funcionário:", jtxIdFuncionario, 4, 1);

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

        jtEntrega = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Id", "Solicitação", "Prevista", "Realização", "Valor do Frete",
                    "Cliente", "Endereço", "Rota", "Funcionário", "Status"}) {
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        });
        jtEntrega.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = jtEntrega.getSelectedRow();
                if(linha >= 0){
                    carregarLinha(linha);
                }
            }
        });
        JScrollPane rolagem = new JScrollPane(jtEntrega);
        rolagem.setPreferredSize(new Dimension(820, 220));

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
            java.util.logging.Logger.getLogger(TelaEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEntrega().setVisible(true);
            }
        });
    }

    private JButton JbEditar;
    private JButton jbExcluir;
    private JButton jbSalvar;
    private JButton jblPesquisar;
    private JComboBox<String> jcbStatus;
    private JTable jtEntrega;
    private JTextField jtxDataPrevista;
    private JTextField jtxDataRealizacao;
    private JTextField jtxDataSolicitacao;
    private JTextField jtxIdCliente;
    private JTextField jtxIdEndereco;
    private JTextField jtxIdEntrega;
    private JTextField jtxIdFuncionario;
    private JTextField jtxIdRota;
    private JTextField jtxValorFrete;
}
