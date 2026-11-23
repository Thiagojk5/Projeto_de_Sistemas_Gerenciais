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
import logistica.controller.EntregaController;
import logistica.controller.MotoristaController;
import logistica.controller.VeiculoController;
import logistica.controller.ViagemController;
import logistica.model.EntregaModel;
import logistica.model.MotoristaModel;
import logistica.model.VeiculoModel;
import logistica.model.ViagemModel;
/**
 *
 * @author User
 */
public class TelaViagem extends javax.swing.JFrame {

    ViagemController controller = new ViagemController();
    EntregaController entregaController = new EntregaController();
    MotoristaController motoristaController = new MotoristaController();
    VeiculoController veiculoController = new VeiculoController();

    public TelaViagem() {
        initComponents();
        limparCampos();
        preencherTabela();
    }

    private boolean camposVazios(){//verifica os campos obrigatórios (Id é gerado pelo banco; datas são opcionais).
        return jtxIdEntrega.getText().trim().isEmpty()
                || jtxIdMotorista.getText().trim().isEmpty()
                || jtxPlacaVeiculo.getText().trim().isEmpty();
    }
    private void limparCampos(){
        jtxIdViagem.setText("");
        jtxDataSaida.setText("");
        jtxDataChegada.setText("");
        jtxIdEntrega.setText("");
        jtxIdMotorista.setText("");
        jtxPlacaVeiculo.setText("");
        jtViagem.clearSelection();
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

    private boolean entregaExiste(int id){
        for(EntregaModel e : entregaController.listar()){
            if(e.getId() == id){
                return true;
            }
        }
        return false;
    }
    private boolean motoristaExiste(int id){
        for(MotoristaModel m : motoristaController.listar()){
            if(m.getId() == id){
                return true;
            }
        }
        return false;
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
        jtxIdViagem.setText(texto(jtViagem.getValueAt(linha, 0)));
        jtxDataSaida.setText(texto(jtViagem.getValueAt(linha, 1)));
        jtxDataChegada.setText(texto(jtViagem.getValueAt(linha, 2)));
        jtxIdEntrega.setText(texto(jtViagem.getValueAt(linha, 3)));
        jtxIdMotorista.setText(texto(jtViagem.getValueAt(linha, 4)));
        jtxPlacaVeiculo.setText(texto(jtViagem.getValueAt(linha, 6)));
    }
    public void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtViagem.getModel();
        modelo.setRowCount(0);
        try{
            for(ViagemModel v : controller.listar()){
                modelo.addRow(new Object[]{v.getId(), texto(v.getDataSaida()), texto(v.getDataChegada()),
                    v.getEntrega(), v.getMotorista().getId(), v.getMotorista().getNome(),
                    v.getVeiculo().getPlaca()});
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
        if(!novo && jtxIdViagem.getText().trim().isEmpty()){
            mostrar("Selecione uma viagem na tabela (ou informe o Id) para editar!");
            return;
        }
        try{
            int id = novo ? 0 : lerInt(jtxIdViagem.getText(), "Id da viagem");
            String saida = jtxDataSaida.getText().trim().isEmpty()
                    ? null : lerData(jtxDataSaida.getText(), "Data de saida");
            String chegada = jtxDataChegada.getText().trim().isEmpty()
                    ? null : lerData(jtxDataChegada.getText(), "Data de chegada");
            if(saida != null && chegada != null
                    && java.time.LocalDate.parse(chegada, FORMATO_DATA).isBefore(java.time.LocalDate.parse(saida, FORMATO_DATA))){
                mostrar("A data de chegada nao pode ser anterior a data de saida!");
                return;
            }
            int idEntrega = lerInt(jtxIdEntrega.getText(), "Codigo da entrega");
            int idMotorista = lerInt(jtxIdMotorista.getText(), "Codigo do motorista");
            if(!entregaExiste(idEntrega)){
                mostrar("Entrega nao encontrada!");
                return;
            }
            if(!motoristaExiste(idMotorista)){
                mostrar("Motorista nao encontrado!");
                return;
            }
            int idVeiculo = idDoVeiculo(jtxPlacaVeiculo.getText().trim());
            if(idVeiculo < 0){
                mostrar("Veiculo nao encontrado com essa placa!");
                return;
            }
            String msg = novo
                    ? controller.cadastrar(saida, chegada, idEntrega, idMotorista, idVeiculo)
                    : controller.atualizar(id, saida, chegada, idEntrega, idMotorista, idVeiculo);
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
        String codigo = jtxIdViagem.getText().trim();
        if(codigo.isEmpty()){
            mostrar("Informe o código (ou selecione uma linha da tabela) para excluir!");
            return;
        }
        try{
            int id = lerInt(codigo, "Codigo");
            if(JOptionPane.showConfirmDialog(this, "Excluir a viagem " + id + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
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
        String codigo = jtxIdViagem.getText().trim();
        preencherTabela();
        if(codigo.isEmpty()){
            return; // sem código: mostra todos os registros
        }
        try{
            int id = lerInt(codigo, "Codigo");
            for(int i = 0; i < jtViagem.getRowCount(); i++){
                if(texto(jtViagem.getValueAt(i, 0)).equals(String.valueOf(id))){
                    jtViagem.setRowSelectionInterval(i, i);
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
        setTitle("Viagem");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtxIdViagem = new JTextField(10);
        jtxIdViagem.setToolTipText("Gerado automaticamente. Informe só para Editar, Excluir ou Pesquisar.");
        jtxDataSaida = new JTextField(10);
        jtxDataSaida.setToolTipText("dd/mm/aaaa (opcional)");
        jtxDataChegada = new JTextField(10);
        jtxDataChegada.setToolTipText("dd/mm/aaaa (opcional)");
        jtxIdEntrega = new JTextField(10);
        jtxIdMotorista = new JTextField(10);
        jtxPlacaVeiculo = new JTextField(10);

        JPanel campos = new JPanel(new GridBagLayout());
        adicionar(campos, "Id da Viagem:", jtxIdViagem, 0, 0);
        adicionar(campos, "Código da Entrega:", jtxIdEntrega, 0, 1);
        adicionar(campos, "Data de Saída:", jtxDataSaida, 1, 0);
        adicionar(campos, "Data de Chegada:", jtxDataChegada, 1, 1);
        adicionar(campos, "Código do Motorista:", jtxIdMotorista, 2, 0);
        adicionar(campos, "Placa do Veículo:", jtxPlacaVeiculo, 2, 1);

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

        jtViagem = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Id", "Saída", "Chegada", "Entrega", "Cód. Motorista", "Motorista", "Placa"}) {
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        });
        jtViagem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = jtViagem.getSelectedRow();
                if(linha >= 0){
                    carregarLinha(linha);
                }
            }
        });
        JScrollPane rolagem = new JScrollPane(jtViagem);
        rolagem.setPreferredSize(new Dimension(700, 220));

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
            java.util.logging.Logger.getLogger(TelaViagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaViagem().setVisible(true);
            }
        });
    }

    private JButton JbEditar;
    private JButton jbExcluir;
    private JButton jbSalvar;
    private JButton jblPesquisar;
    private JTable jtViagem;
    private JTextField jtxDataChegada;
    private JTextField jtxDataSaida;
    private JTextField jtxIdEntrega;
    private JTextField jtxIdMotorista;
    private JTextField jtxIdViagem;
    private JTextField jtxPlacaVeiculo;
}
