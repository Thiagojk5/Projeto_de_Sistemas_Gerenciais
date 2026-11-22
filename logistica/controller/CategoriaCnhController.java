/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.CategoriaCnhDao;
import logistica.model.CategoriaCnhModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class CategoriaCnhController {
    CategoriaCnhDao dao = new CategoriaCnhDao();
    
    private String validar(int id, String tipoCnh){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        
        if(tipoCnh == null || tipoCnh.isBlank() || tipoCnh.length() > 2){
            return "Categoria de CNH invalido";
        }
        
        return null;
    }
    
    public String cadastrar(int id, String tipoCnh){
        String erro = validar(id, tipoCnh);
        
        if(erro != null){
            return erro;
        }
        // Validação Se Categoria de CNH ja estiver cadastrado independente do id,
        // Por exemplo se ja tem cadastrado a Categoria A não pode cadastrar ou A
        //Reutilizavel em Motorista
        tipoCnh = tipoCnh.trim().toUpperCase(); // ignora se é a ou A
        
        if(dao.buscarPorTipo(tipoCnh) != null){
           return "Categoria de CNH ja cadastrada";
        }
        
        CategoriaCnhModel categoria = new CategoriaCnhModel(id, tipoCnh);
        if(dao.inserir(categoria)){
            return "Categoria de CNH cadastrado";
        }
        
        return "Nao foi possivel cadastrar Categoria de CNH";
    }
    
    public String atualizar(int id,String tipoCnh){
        String erro = validar(id, tipoCnh);
        if(erro != null){
            return erro;
        }
        if(dao.buscar(id) == null){
            return "Categoria de CNH nao encontrada!";
        }
        tipoCnh = tipoCnh.trim().toUpperCase();
        // o tipo novo so nao pode pertencer a OUTRA categoria
        CategoriaCnhModel existente = dao.buscarPorTipo(tipoCnh);
        if(existente != null && existente.getId() != id){
            return "Categoria de CNH ja cadastrada";
        }
        CategoriaCnhModel categoria = new CategoriaCnhModel(id, tipoCnh);
        if(dao.atualizar(categoria)){
            return "Categoria de CNH atualizada!";
        }
        return "Nao foi possivel atualizar Categoria de CNH!";
    }
    
    public String removerPorId(int id){
        CategoriaCnhModel categoria = dao.buscar(id);
        
        if(categoria == null){
            return "Categoria de CNH nao encontrado!";
        }
        
        if(dao.removerPorId(categoria)){
            return "Categoria de CNH removido!";
        }
        return "Nao foi possivel remover Categoria de CNH!";
    }
    
    public String remover(String tipoCnh){
        CategoriaCnhModel categoria = dao.buscarPorTipo(tipoCnh == null ? "" : tipoCnh.trim().toUpperCase());
        if(categoria == null){
            return "Categoria de CNH nao encontrado!";
        }
        if(dao.remover(categoria)){
            return "Categoria de CNH removido!";
        }
        return "Nao foi possivel remover Categoria de CNH!";
    }
    
    public ArrayList<CategoriaCnhModel> listar(){
        return dao.exibirTodos();
    }
}
