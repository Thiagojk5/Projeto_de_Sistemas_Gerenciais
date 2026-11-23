/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.ManutencaoDao;
import logistica.model.VeiculoModel;
import logistica.model.ManutencaoModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ManutencaoController {
    ManutencaoDao dao = new ManutencaoDao();
    VeiculoModel veiculo = new VeiculoModel();
    
    private String validar(float custo,String tipo,String data,String descricao,int idVeiculo){
        if(custo<0){
            return "Custo da Manutencao invalido!";
        }
        if(tipo==null || tipo.trim().isEmpty() || tipo.length()>50){
            return "Tipo de Manutencao invalido!";
        }
        if(data==null || data.trim().length()!=10){
            return "Data da Manutencao invalida!";
        }
        if(descricao!=null && descricao.length()>255){
            return "Descricao da Manutencao invalida!";
        }
        if(idVeiculo<0){
            return "Codigo de identificacao de Veiculo invalido!";
        }
        return null;
    }
    
    public String cadastrar(float custo,String tipo,String data,String descricao,int idVeiculo){
        String erro = validar(custo, tipo, data, descricao, idVeiculo);
        if(erro!=null){
            return erro;
        }
        veiculo.setId(idVeiculo);
        ManutencaoModel manutencao = new ManutencaoModel(0, custo, tipo, data, descricao, veiculo);
        if(dao.inserir(manutencao)){
            return "Manutencao foi Cadastrada com SUCESSO!";
        }
        return "Manutencao NAO foi Cadastrada com SUCESSO!";
    }
    
    public String atualizar(int id,float custo,String tipo,String data,String descricao,int idVeiculo){
        if(id<0){
            return "Codigo de identificacao de Manutencao invalido!";
        }
        String erro = validar(custo, tipo, data, descricao, idVeiculo);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Manutencao NAO Cadastrada!";
        }
        veiculo.setId(idVeiculo);
        ManutencaoModel manutencao = new ManutencaoModel(id, custo, tipo, data, descricao, veiculo);
        if(dao.atualizar(manutencao)){
            return "Manutencao foi Atualizada com SUCESSO!";
        }
        return "Manutencao NAO foi Atualizada com SUCESSO!";
    }
    
    
    public String remover(int id){
        ManutencaoModel manutencao = dao.buscar(id);
        if(manutencao==null){
            return "Manutencao NAO Cadastrada!";
        }
        if(dao.remover(manutencao)){
            return "Manutencao foi Removida com SUCESSO!";
        }
        return "Manutencao NAO foi Removida com SUCESSO!";
    }
    
    public ArrayList<ManutencaoModel> listar(){
        return dao.exibirTodos();
    }
}