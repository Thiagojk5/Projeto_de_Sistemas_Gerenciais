/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.MotoristaDao;
import logistica.model.MotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class MotoristaController {
    MotoristaDao dao = new MotoristaDao();
    
    private String validar(int id, String nome, String cpf, String numeroCnh, String validadeCnh){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        if(nome == null || nome.isBlank() || nome.length() > 100){
            return "Nome invalido!";
        }
        if(cpf == null || cpf.isBlank() || cpf.length() > 11){
            return "Cpf invalido!";
        }
        if(numeroCnh == null || numeroCnh.isBlank() || numeroCnh.length() > 11){
            return "CNH invalido!";
        }
        if(validadeCnh == null || validadeCnh.isBlank() || validadeCnh.length() > 10){
            return "Data de Validade invalidada!";
        }
        
        return null;
    }
    
    public String cadastrar(int id, String nome, String cpf, String numeroCnh, String validadeCnh){
        String erro = validar(id, nome, cpf, numeroCnh, validadeCnh);
        
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) != null){
            return "Motorista ja esta cadastrado!";
        }
        
        MotoristaModel motorista = new MotoristaModel(id, nome, cpf, numeroCnh, validadeCnh);
        if(dao.inserir(motorista)){
            return "Motorista cadastrado!";
        }
        
        return "Nao foi possivel cadastrar Motorista!";
    }
    
    public String atualizar(int id, String nome, String cpf, String numeroCnh, String validadeCnh){
        String erro = validar(id, nome, cpf, numeroCnh, validadeCnh);
        if(erro!= null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Motorista nao encontrado!";
        }
        MotoristaModel motorista = new MotoristaModel(id, nome, cpf, numeroCnh, validadeCnh);
        if(dao.atualizar(motorista)){
            return "Motorista atualizado!";
        }
        return "Nao foi possivel atualizar Motorista!";
    }
    
    public String remover(int id){
        MotoristaModel motorista = dao.buscar(id);
        if(motorista == null){
            return "Motorista nao encontrado!";
        }
        if(dao.remover(motorista)){
            return "Motorista removido!";
        }
        return "Nao foi possivel remover Motorista";
    }
    
    public ArrayList<MotoristaModel> listar(){
        return dao.exibirTodos();
    }
}
