/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.MotoristaCategoriaDao;
import logistica.model.CategoriaCnhModel;
import logistica.model.MotoristaModel;
import logistica.model.MotoristaCategoriaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class MotoristaCategoriaController {
    MotoristaCategoriaDao dao = new MotoristaCategoriaDao();
    MotoristaModel motorista = new MotoristaModel();
    CategoriaCnhModel categoria = new CategoriaCnhModel();
    
    private String validar(int idMotorista,int idCategoria){
        if(idMotorista<0){
            return "Codigo de identificacao de Motorista invalido!";
        }
        if(idCategoria<0){
            return "Codigo de identificao de Categoria invalida!";
        }
        return null;
    }
    
    public String cadastrar(int idMotorista,int idCategoria){
        String erro = validar(idMotorista, idCategoria);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(idMotorista)!=null){
            return "Motorista ja possui Categoria de CNH Cadastrada!";
        }
        motorista.setId(idMotorista);
        categoria.setId(idCategoria);
        MotoristaCategoriaModel motoCategoria = new MotoristaCategoriaModel(motorista, categoria);
        if(dao.inserir(motoCategoria)){
            return "Categoria de CNH do Motorista foi Cadastrado com SUCESSO!";
        }
        return "Categoria de CNH do Motorista NAO foi Cadastrado com SUCESSO!";
    }
    
    public String atualizar(int idMotorista,int idCategoria){
        String erro = validar(idMotorista, idCategoria);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(idMotorista)==null){
            return "Motorista NAO possui Categoria de CNH Cadastrada!";
        }
        motorista.setId(idMotorista);
        categoria.setId(idCategoria);
        MotoristaCategoriaModel motoCategoria = new MotoristaCategoriaModel(motorista, categoria);
        if(dao.atualizar(motoCategoria)){
            return "Categoria de CNH do Motorista foi Atualizado com SUCESSO!";
        }
        return "Categoria de CNH do Motorista NAO foi Atualizado com SUCESSO!";
    }
    
    
    public String remover(int idMotorista){
        MotoristaCategoriaModel motoCategoria = dao.buscar(idMotorista);
        if(motoCategoria==null){
            return "Motorista NAO possui Categoria de CNH Cadastrada!";
        }
        if(dao.remover(motoCategoria)){
            return "Categoria de CNH do Motorista foi Removido com SUCESSO!";
        }
        return "Categoria de CNH do Motorista NAO foi Removido com SUCESSO!";
    }
    
    public ArrayList<MotoristaCategoriaModel> listar(){
        return dao.exibirTodos();
    }
}
