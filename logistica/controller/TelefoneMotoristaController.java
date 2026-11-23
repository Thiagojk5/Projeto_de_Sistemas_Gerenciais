/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.TelefoneMotoristaDao;
import logistica.model.TelefoneMotoristaModel;
import logistica.model.MotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class TelefoneMotoristaController {
    TelefoneMotoristaDao dao = new TelefoneMotoristaDao();
    MotoristaModel motorista = new MotoristaModel();
    
    private String validar(int id,String ddd,String numero,int idMotorista){
        if(id<0){
            return "Codigo de identificacao invalido";
        }
        if(ddd==null || ddd.isBlank() || ddd.length()>2){
            return "DDD invalido!";
        }
        if(numero.isBlank() || numero.length()<9 || numero.length()>9){
            return "Numero invalido!";
        }
        if(idMotorista<0){
            return "Codigo de identificacao do Motorista invalido!";
        }
        return null;
    }
    
    public String cadastrar(int id,String ddd,String numero,int idMotorista){
        String erro = validar(id, ddd, numero, idMotorista);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)!=null){
            return "Telefone de Motorista ja esta cadastrado!";
        }
        motorista.setId(idMotorista);
        TelefoneMotoristaModel telefone = new TelefoneMotoristaModel(id, ddd, numero, motorista);
        if(dao.inserir(telefone)){
            return "Telefone do Motorista cadastrado!";
        }
        return "Nao foi possivel cadastrar Telefone do Motorista!";
    }
    
    public String atualizar(int id,String ddd,String numero,int idMotorista){
        String erro = validar(id, ddd, numero, idMotorista);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Telefone do Motorista nao encontrado!";
        }
        motorista.setId(idMotorista);
        TelefoneMotoristaModel telefone = new TelefoneMotoristaModel(id, ddd, numero, motorista);
        if(dao.atualizar(telefone)){
            return "Telefone do Motorista atualizado!";
        }
        return "Nao foi possivel atualizar Telefone do Motorista!";
    }
    
    public String remover(int id){
        TelefoneMotoristaModel telefone = dao.buscar(id);
        if(telefone==null){
            return "Telefone do Motorista nao encontrado!";
        }
        if(dao.remover(telefone)){
            return "Telefone do Motorista removido!";
        }
        return "Nao foi possivel remover Telefone do Motorista!";
    }
    
    public ArrayList<TelefoneMotoristaModel> lista(){
        return dao.exibirTodos();
    }
}
