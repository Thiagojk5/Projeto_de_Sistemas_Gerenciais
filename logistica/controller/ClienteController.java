/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.ClienteDao;
import logistica.model.ClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ClienteController {
    private ClienteDao dao = new ClienteDao();
    
    private String validar(int id,String nome, String cpfCnpj){
        if(nome == null || nome.isBlank() || nome.length()>100){
            return "Nome invalido!";
        }
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
     
        /*
       if(cpfCnpj == null || cpfCnpj.isBlank() || cpfCnpj.length() < 14){
        return "CPF ou CNPJ invalido!";
       }*/
       if(cpfCnpj == null || !cpfCnpj.matches("\\d{11}|\\d{14}")){
            return "CPF ou CNPJ invalido!";
       }
       return null;
    }   
    
    public String cadastrar(int id,String nome, String cpfCnpj){
        String erro = validar(id,nome,cpfCnpj);
        
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) != null){
            return "Cliente ja esta Cadastrado!";
        }
        ClienteModel cliente = new ClienteModel(id, nome, cpfCnpj);
        
        if(dao.inserir(cliente)){
            return "Cliente inserido";
        }
        return "Nao foi possivel inserir Cliente";
    }
    
    public String atualizar(int id, String nome, String cfpCnpj){
        String erro = validar(id,nome,cfpCnpj);
        
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) == null){
            return "Cliente nao encontrado!";
        }
        
        ClienteModel cliente = new ClienteModel(id, nome, cfpCnpj);
        
        if(dao.atualizar(cliente)){
            return "Cliente atualizado com sucesso!";
        }
        
        return "Nao foi possivel atualizar Cliente!";
    }
    
    public String remover(int id){
        ClienteModel cliente = dao.buscar(id);
        
        if(cliente == null){
            return "Cliente nao encontrado!";
        }
        if(dao.remover(cliente)){
            return "Cliente removido!";
        }
        return "Nao foi possivel remover o Cliente!";
    }
    
    public ArrayList<ClienteModel> listar(){
        return dao.exibirTodos();
    }
}
