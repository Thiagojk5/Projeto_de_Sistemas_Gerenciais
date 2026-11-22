/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.TelefoneClienteDao;
import logistica.model.TelefoneClienteModel;
import logistica.model.ClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class TelefoneClienteController {
    TelefoneClienteDao dao = new TelefoneClienteDao();
    ClienteModel cliente = new ClienteModel();
    
    private String validar(int id,String ddd,String numero,int idCliente){
        if(id<0){
            return "Codigo de identificacao invalido";
        }
        if(ddd==null || ddd.isBlank() || ddd.length()>2){
            return "DDD invalido!";
        }
        if(numero==null || numero.isBlank() || numero.length()!=9){
            return "Numero invalido!";
        }
        if(idCliente<0){
            return "Codigo de identificacao do Cliente invalido!";
        }
        return null;
    }
    
    public String cadastrar(int id,String ddd,String numero,int idCliente){
        String erro = validar(id, ddd, numero, idCliente);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)!=null){
            return "Telefone de Cliente ja esta cadastrado!";
        }
        cliente.setId(idCliente);
        TelefoneClienteModel telefone = new TelefoneClienteModel(id, ddd, numero, cliente);
        if(dao.inserir(telefone)){
            return "Telefone do Cliente cadastrado!";
        }
        return "Nao foi possivel cadastrar Telefone do Cliente!";
    }
    
    public String atualizar(int id,String ddd,String numero,int idCliente){
        String erro = validar(id, ddd, numero, idCliente);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Telefone do Cliente nao encontrado!";
        }
        cliente.setId(idCliente);
        TelefoneClienteModel telefone = new TelefoneClienteModel(id, ddd, numero, cliente);
        if(dao.atualizar(telefone)){
            return "Telefone do Cliente atualizado!";
        }
        return "Nao foi possivel atualizar Telefone do Cliente!";
    }
    
    public String remover(int id){
        TelefoneClienteModel telefone = dao.buscar(id);
        if(telefone==null){
            return "Telefone do Cliente nao encontrado!";
        }
        if(dao.remover(telefone)){
            return "Telefone do Cliente removido!";
        }
        return "Nao foi possivel remover Telefone do Cliente!";
    }
    
    public ArrayList<TelefoneClienteModel> lista(){
        return dao.exibirTodos();
    }
}
