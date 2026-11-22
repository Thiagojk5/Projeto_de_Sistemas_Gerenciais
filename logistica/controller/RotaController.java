/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.RotaDao;
import logistica.model.RotaModel;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class RotaController {
    RotaDao dao = new RotaDao();
    
    private String validar(int id, String origem, String destino, float distanciaKm, String tempoEstimado){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        if(origem == null || origem.isBlank() || origem.length() > 100){
            return "Origem invalida(texto muito longo)!";
        }
        if(destino == null || destino.isBlank() || destino.length() > 100){
            return "Destino invalida(texto muito longo)!";
        }
        if(distanciaKm < 0){
            return "Distancia invalida!";
        }
        if(tempoEstimado == null || tempoEstimado.isBlank()){
            return "Tempo invalido!";
        }
        return null;
    }
    
    public String cadastrar(int id, String origem, String destino, float distanciaKm, String tempoEstimado){
        String erro = validar(id, origem, destino, distanciaKm, tempoEstimado);
        
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) != null){
            return "Rota ja esta cadastrada!";
        }
        
        RotaModel rota = new RotaModel(id, distanciaKm, origem, destino, tempoEstimado);
        
        if(dao.inserir(rota)){
            return "Rota cadastrada!";
        }
        
        
        return "Nao foi possivel cadastrar Rota!";
    }
    
    public String atualizar(int id, String origem, String destino, float distanciaKm, String tempoEstimado){
        String erro = validar(id, origem, destino, distanciaKm, tempoEstimado);
        
        if(erro != null){
            return erro;
        }
        
        if(dao.buscar(id) == null){
            return "Rota nao encontrada!";
        }
        
        RotaModel rota = new RotaModel(id, distanciaKm, origem, destino, tempoEstimado);
        if(dao.atualizar(rota)){
            return "Rota atualizada!";
        }
        
        return "Nao foi possivel atualizar Rota!";
    }
    
    public String remover(int id){
        RotaModel rota = dao.buscar(id);
        
        if(rota == null){
            return "Rota nao encontrada!";
        }
        
        if(dao.remover(rota)){
            return "Rota removida!";
        }
        
        return "Nao foi possivel remover Rota!";
    }
    
    public ArrayList<RotaModel> listar(){
        return dao.exibirTodos();
    }
}
