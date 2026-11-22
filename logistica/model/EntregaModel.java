/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class EntregaModel {
    // id, dataSolicitacao, dataPrevista, dataRealizacao, status, valorDoFrete, cliente, 
    // endereco, rota, funcionario
    private int id;
    private float valorDoFrete;
    private String dataSolicitacao, dataPrevista, dataRealizacao, status;
    private ClienteModel cliente;
    private EnderecoModel endereco;
    private RotaModel rota;
    private FuncionarioModel funcionario;
    
    public EntregaModel(){
        id = 0;
        valorDoFrete = 0;
        dataSolicitacao = "";
        dataPrevista = "";
        dataRealizacao = "";
        status = "";
        cliente = new ClienteModel();
        endereco = new EnderecoModel();
        rota = new RotaModel();
        funcionario = new FuncionarioModel();
    }

    public EntregaModel(int id, float valorDoFrete, String dataSolicitacao, String dataPrevista, String dataRealizacao, String status, ClienteModel cliente, EnderecoModel endereco, RotaModel rota, FuncionarioModel funcionario) {
        this.id = id;
        this.valorDoFrete = valorDoFrete;
        this.dataSolicitacao = dataSolicitacao;
        this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.status = status;
        this.cliente = cliente;
        this.endereco = endereco;
        this.rota = rota;
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorDoFrete() {
        return valorDoFrete;
    }

    public void setValorDoFrete(float valorDoFrete) {
        this.valorDoFrete = valorDoFrete;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public RotaModel getRota() {
        return rota;
    }

    public void setRota(RotaModel rota) {
        this.rota = rota;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }
}
