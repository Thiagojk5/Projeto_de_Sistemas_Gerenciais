/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.EmailMotoristaDao;
import logistica.model.MotoristaModel;
import logistica.model.EmailMotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EmailMotoristaController {
    EmailMotoristaDao dao = new EmailMotoristaDao();
    MotoristaModel motorista = new MotoristaModel();
    
    private String validar(int id,String nomeEmail,int idMotorista){
        if(id<0){
            return "Codigo de identificacao invalido";
        }
        if(nomeEmail==null || nomeEmail.isBlank() || nomeEmail.length()>100){
            return "Email do Motorista invalido!";
        }
        if(idMotorista<0){
            return "Codigo de identificacao do Motorista invalido";
        }
        return null;
    }
    
    public String cadastrar(int id,String nomeEmail,int idMotorista){
        String erro = validar(id, nomeEmail, idMotorista);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id) != null){
            return "Email do Motorista ja esta cadastrado!";
        }
        motorista.setId(idMotorista);
        EmailMotoristaModel email = new EmailMotoristaModel(id, nomeEmail, motorista);
        if(dao.inserir(email)){
            return "Email do Motorista cadastrado!";
        }
        return "Nao foi possivel cadastrar Email do Motorista!";
    }
    
    public String atualizar(int id,String nomeEmail,int idMotorista){
        String erro = validar(id, nomeEmail, idMotorista);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id) == null){
            return "Email do Motorista nao encontrado!";
        }
        motorista.setId(idMotorista);
        EmailMotoristaModel email = new EmailMotoristaModel(id, nomeEmail, motorista);
        if(dao.atualizar(email)){
            return "Email do Motorista atualizado!";
        }
        return "Nao foi possivel atualizar Email do Motorista!";
    }
    
    public String remover(int id){
        EmailMotoristaModel email = dao.buscar(id);
        if(email==null){
            return "Email do Motorista nao encontrado!";
        }
        if(dao.remover(email)){
            return "Email do Motorista removido!";
        }
        return "Nao foi possivel remover Email do Motorista!";
    }
    
    public ArrayList<EmailMotoristaModel> listar(){
        return dao.exibirTodos();
    }
}
