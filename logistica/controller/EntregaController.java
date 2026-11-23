/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.EntregaDao;
import logistica.model.ClienteModel;
import logistica.model.EnderecoModel;
import logistica.model.RotaModel;
import logistica.model.FuncionarioModel;
import logistica.model.EntregaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EntregaController {
    EntregaDao dao = new EntregaDao();
    ClienteModel cliente = new ClienteModel();
    EnderecoModel endereco = new EnderecoModel();
    RotaModel rota = new RotaModel();
    FuncionarioModel funcionario = new FuncionarioModel();
    
    private String validar(float valorDoFrete,String dataSolicitacao,String dataPrevista,String dataRealizacao,String status,int idCliente,int idEndereco,int idRota,int idFuncionario){
        if(valorDoFrete<0){
            return "Valor do Frete invalido!";
        }
        if(dataSolicitacao==null || dataSolicitacao.trim().length()!=10){
            return "Data de Solicitacao invalida!";
        }
        if(dataPrevista==null || dataPrevista.trim().length()!=10){
            return "Data Prevista invalida!";
        }
        if(dataRealizacao!=null && !dataRealizacao.trim().isEmpty() && dataRealizacao.trim().length()!=10){
            return "Data de Realizacao invalida!";
        }
        if(status==null || status.trim().isEmpty() || status.length()>20){
            return "Status da Entrega invalido!";
        }
        if(idCliente<0){
            return "Codigo de identificacao de Cliente invalido!";
        }
        if(idEndereco<0){
            return "Codigo de identificacao de Endereco invalido!";
        }
        if(idRota<0){
            return "Codigo de identificacao de Rota invalido!";
        }
        if(idFuncionario<0){
            return "Codigo de identificacao de Funcionario invalido!";
        }
        return null;
    }
    
    public String cadastrar(float valorDoFrete,String dataSolicitacao,String dataPrevista,String dataRealizacao,String status,int idCliente,int idEndereco,int idRota,int idFuncionario){
        String erro = validar(valorDoFrete, dataSolicitacao, dataPrevista, dataRealizacao, status, idCliente, idEndereco, idRota, idFuncionario);
        if(erro!=null){
            return erro;
        }
        cliente.setId(idCliente);
        endereco.setId(idEndereco);
        rota.setId(idRota);
        funcionario.setId(idFuncionario);
        EntregaModel entrega = new EntregaModel(0, valorDoFrete, dataSolicitacao, dataPrevista, dataRealizacao, status, cliente, endereco, rota, funcionario);
        if(dao.inserir(entrega)){
            return "Entrega foi Cadastrada com SUCESSO!";
        }
        return "Entrega NAO foi Cadastrada com SUCESSO!";
    }
    
    public String atualizar(int id,float valorDoFrete,String dataSolicitacao,String dataPrevista,String dataRealizacao,String status,int idCliente,int idEndereco,int idRota,int idFuncionario){
        if(id<0){
            return "Codigo de identificacao de Entrega invalido!";
        }
        String erro = validar(valorDoFrete, dataSolicitacao, dataPrevista, dataRealizacao, status, idCliente, idEndereco, idRota, idFuncionario);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Entrega NAO Cadastrada!";
        }
        cliente.setId(idCliente);
        endereco.setId(idEndereco);
        rota.setId(idRota);
        funcionario.setId(idFuncionario);
        EntregaModel entrega = new EntregaModel(id, valorDoFrete, dataSolicitacao, dataPrevista, dataRealizacao, status, cliente, endereco, rota, funcionario);
        if(dao.atualizar(entrega)){
            return "Entrega foi Atualizada com SUCESSO!";
        }
        return "Entrega NAO foi Atualizada com SUCESSO!";
    }
    
    
    public String remover(int id){
        EntregaModel entrega = dao.buscar(id);
        if(entrega==null){
            return "Entrega NAO Cadastrada!";
        }
        if(dao.remover(entrega)){
            return "Entrega foi Removida com SUCESSO!";
        }
        return "Entrega NAO foi Removida com SUCESSO!";
    }
    
    public ArrayList<EntregaModel> listar(){
        return dao.exibirTodos();
    }
}