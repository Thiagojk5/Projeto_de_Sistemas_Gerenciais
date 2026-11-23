/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.VeiculoDao;
import logistica.model.VeiculoModel;
import java.util.ArrayList;
import java.time.Year;
/**
 *
 * @author aluno
 */
public class VeiculoController {
    VeiculoDao dao  = new VeiculoDao();
    
    private String validar(int id, String placa, String modelo, String tipo, float capacidadeDeCarga, int ano, String status){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        if(placa == null || placa.isBlank() || placa.length() > 8){
            return "Placa invalida!";
        }
        if(modelo == null || modelo.isBlank() || modelo.length() > 50){
            return "Modelo de placa invalido!";
        }
        if(tipo == null || tipo.isBlank() || tipo.length() > 30){
            return "Tipo da placa invalido!";
        }
        if(capacidadeDeCarga < 0){
            return "Capacidade de carga invalida!";
        }
        // validação de ano não pode ano>anoAtual ano < 1935
        if(ano < 1935 || ano > Year.now().getValue()){
            return "Ano do Veiculo invalido!";
        }
        if(status == null || status.isBlank() || status.length() > 20){
            return "Status de Veiculo invalido!";
        }
        if(!status.equalsIgnoreCase("Disponivel") && !status.equalsIgnoreCase("Indisponivel")){
            return "Status de Veiculo invalido!";
        }
        
        return null;
    }
    
    public String atualizar(int id, String placa, String modelo, String tipo, float capacidadeDeCarga, int ano, String status){
        String erro = validar(id, placa, modelo, tipo, capacidadeDeCarga, ano, status);
        
        if(erro != null){
            return erro;
        }
        if(dao.buscar(id) == null){
            return "Veiculo ja esta cadastrado!";
        }
        
        VeiculoModel veiculo = new VeiculoModel(id, ano, placa, modelo, tipo, status, capacidadeDeCarga);
        if(dao.atualizar(veiculo)){
            return "Veiculo cadastrado";
        }
        
        return "Nao foi possivel casdatrar Veiculo";
    }
    
    public String cadastrar(int id,String placa, String modelo, String tipo, float capacidadeDeCarga, int ano, String status){
        String erro = validar(id, placa, modelo, tipo, capacidadeDeCarga, ano, status);
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) != null){
            return "Veiculo ja esta cadastrado!";
        }
        
        VeiculoModel veiculo = new VeiculoModel(id, ano, placa, modelo, tipo, status, capacidadeDeCarga);
        if(dao.inserir(veiculo)){
            return "Veiculo cadastrado!";
        }
        
        return "Nao foi possivel cadastrar Veiculo!";
        
    }
    
    public String remover(int id){
       VeiculoModel veiculo = dao.buscar(id);
       if(veiculo == null){
           return "Veiculo nao encontrado!";
       }
       if(dao.remover(veiculo)){
           return "Veiculo removido!";
       }
       
       return "Nao foi possivel remover Veiculo";
    }
    
    public ArrayList<VeiculoModel> listar(){
        return dao.exibirTodos();
    }
}
