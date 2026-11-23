/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.ViagemDao;
import logistica.model.MotoristaModel;
import logistica.model.VeiculoModel;
import logistica.model.ViagemModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ViagemController {
    ViagemDao dao = new ViagemDao();
    MotoristaModel motorista = new MotoristaModel();
    VeiculoModel veiculo = new VeiculoModel();
    
    private String validar(String dataSaida,String dataChegada,int idEntrega,int idMotorista,int idVeiculo){
        if(dataSaida!=null && !dataSaida.trim().isEmpty() && dataSaida.trim().length()!=10){
            return "Data de Saida invalida!";
        }
        if(dataChegada!=null && !dataChegada.trim().isEmpty() && dataChegada.trim().length()!=10){
            return "Data de Chegada invalida!";
        }
        if(idEntrega<0){
            return "Codigo de identificacao de Entrega invalido!";
        }
        if(idMotorista<0){
            return "Codigo de identificacao de Motorista invalido!";
        }
        if(idVeiculo<0){
            return "Codigo de identificacao de Veiculo invalido!";
        }
        return null;
    }
    
    public String cadastrar(String dataSaida,String dataChegada,int idEntrega,int idMotorista,int idVeiculo){
        String erro = validar(dataSaida, dataChegada, idEntrega, idMotorista, idVeiculo);
        if(erro!=null){
            return erro;
        }
        motorista.setId(idMotorista);
        veiculo.setId(idVeiculo);
        ViagemModel viagem = new ViagemModel(0, dataSaida, dataChegada, String.valueOf(idEntrega), motorista, veiculo);
        if(dao.inserir(viagem)){
            return "Viagem foi Cadastrada com SUCESSO!";
        }
        return "Viagem NAO foi Cadastrada com SUCESSO!";
    }
    
    public String atualizar(int id,String dataSaida,String dataChegada,int idEntrega,int idMotorista,int idVeiculo){
        if(id<0){
            return "Codigo de identificacao de Viagem invalido!";
        }
        String erro = validar(dataSaida, dataChegada, idEntrega, idMotorista, idVeiculo);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Viagem NAO Cadastrada!";
        }
        motorista.setId(idMotorista);
        veiculo.setId(idVeiculo);
        ViagemModel viagem = new ViagemModel(id, dataSaida, dataChegada, String.valueOf(idEntrega), motorista, veiculo);
        if(dao.atualizar(viagem)){
            return "Viagem foi Atualizada com SUCESSO!";
        }
        return "Viagem NAO foi Atualizada com SUCESSO!";
    }
    
    
    public String remover(int id){
        ViagemModel viagem = dao.buscar(id);
        if(viagem==null){
            return "Viagem NAO Cadastrada!";
        }
        if(dao.remover(viagem)){
            return "Viagem foi Removida com SUCESSO!";
        }
        return "Viagem NAO foi Removida com SUCESSO!";
    }
    
    public ArrayList<ViagemModel> listar(){
        return dao.exibirTodos();
    }
}