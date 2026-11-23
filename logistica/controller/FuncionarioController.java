/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.FuncionarioDao;
import logistica.model.FuncionarioModel;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class FuncionarioController {
    FuncionarioDao dao = new FuncionarioDao();
    
    private String validar(int id, String nome, String cargo){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        
        if(nome == null || nome.isBlank() || nome.length()>100){
            return "Nome invalido!";
        }
        
        if(cargo == null || cargo.isBlank() || cargo.length() > 50){
            return "Cargo invalido!";
        }
       return null;
    }
    
    public String cadastrar(int id,String nome,String cargo){
        String erro = validar(id, nome, cargo);
        if(erro != null){
            return erro;
        }
        if(dao.buscar(id) != null){
            return "Funcionario ja esta cadastrado!";
        }
        
        FuncionarioModel funcionario = new FuncionarioModel(id, nome, cargo);
        if(dao.inserir(funcionario)){
            return "Funcionario inserido!";
        }
        return "Nao foi possivel cadastrar Funcionario!";
    }
    
    public String atualizar(int id,String nome, String cargo){
        String erro = validar(id, nome, cargo);
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) == null){
            return "Funcionario nao encontrado!";
        }
        
        FuncionarioModel funcionario = new FuncionarioModel(id, nome, cargo);
        
        if(dao.atualizar(funcionario)){
            return "Funcionario atualizado com sucesso!";
        }
        
        return "Nso foi oissivel atualizar o Funcionario!";
    }
    
    public String remover(int id){
        FuncionarioModel funcionario = dao.buscar(id);
        if(funcionario == null){
            return "Funcionario nao encontrado!";
        }
        
        if(dao.remover(funcionario)){
            return "Funcionario removido!";
        }
        
        return "Nao foi possivel remover O Funcionario";
    }
    
    public ArrayList<FuncionarioModel> listar(){
        return dao.exibirTodos();
    }
}
