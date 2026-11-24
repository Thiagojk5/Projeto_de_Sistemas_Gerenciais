/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.EmailClienteDao;
import logistica.model.ClienteModel;
import logistica.model.EmailClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EmailClienteController {
    EmailClienteDao dao = new EmailClienteDao();
    ClienteModel cliente = new ClienteModel();
    
    private String validar(int id,String nomeEmail,int idCliente){
        if(id<0){
            return "Codigo de identificacao invalido";
        }
        if(nomeEmail==null || nomeEmail.isBlank() || nomeEmail.length()>100){
            return "Email do Cliente invalido!";
        }
        if(idCliente<0){
            return "Codigo de identificacao do Cliente invalido";
        }
        return null;
    }
    
    public String cadastrar(int id,String nomeEmail,int idCliente){
        String erro = validar(id, nomeEmail, idCliente);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id) != null){
            return "Email do Cliente ja esta cadastrado!";
        }
        cliente.setId(idCliente);
        EmailClienteModel email = new EmailClienteModel(id, nomeEmail, cliente);
        if(dao.inserir(email)){
            return "Email do Cliente cadastrado!";
        }
        return "Nao foi possivel cadastrar Email do Cliente!";
    }
    
    public String atualizar(int id,String nomeEmail,int idCliente){
        String erro = validar(id, nomeEmail, idCliente);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id) == null){
            return "Email do Cliente nao encontrado!";
        }
        cliente.setId(idCliente);
        EmailClienteModel email = new EmailClienteModel(id, nomeEmail, cliente);
        if(dao.atualizar(email)){
            return "Email do Cliente atualizado!";
        }
        return "Nao foi possivel atualizar Email do Cliente!";
    }
    
    public String remover(int id){
        EmailClienteModel email = dao.buscar(id);
        if(email==null){
            return "Email do Cliente nao encontrado!";
        }
        if(dao.remover(email)){
            return "Email do Cliente removido!";
        }
        return "Nao foi possivel remover Email do Cliente!";
    }
    
    public ArrayList<EmailClienteModel> listar(){
        return dao.exibirTodos();
    }
}
