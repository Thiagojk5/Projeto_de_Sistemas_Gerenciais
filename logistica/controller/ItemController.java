/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.ItemDao;
import logistica.model.ItemModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ItemController {
    ItemDao dao = new ItemDao();
    
    private String validar(float peso,float volume,float valorDeclarado,String descricao,int idEntrega){
        if(peso<0){
            return "Peso do Item invalido!";
        }
        if(volume<0){
            return "Volume do Item invalido!";
        }
        if(valorDeclarado<0){
            return "Valor Declarado do Item invalido!";
        }
        if(descricao==null || descricao.trim().isEmpty() || descricao.length()>150){
            return "Descricao do Item invalida!";
        }
        if(idEntrega<0){
            return "Codigo de identificacao de Entrega invalido!";
        }
        return null;
    }
    
    public String cadastrar(float peso,float volume,float valorDeclarado,String descricao,int idEntrega){
        String erro = validar(peso, volume, valorDeclarado, descricao, idEntrega);
        if(erro!=null){
            return erro;
        }
        ItemModel item = new ItemModel(0, peso, volume, valorDeclarado, descricao, String.valueOf(idEntrega));
        if(dao.inserir(item)){
            return "Item foi Cadastrado com SUCESSO!";
        }
        return "Item NAO foi Cadastrado com SUCESSO!";
    }
    
    public String atualizar(int id,float peso,float volume,float valorDeclarado,String descricao,int idEntrega){
        if(id<0){
            return "Codigo de identificacao de Item invalido!";
        }
        String erro = validar(peso, volume, valorDeclarado, descricao, idEntrega);
        if(erro!=null){
            return erro;
        }
        if(dao.buscar(id)==null){
            return "Item NAO Cadastrado!";
        }
        ItemModel item = new ItemModel(id, peso, volume, valorDeclarado, descricao, String.valueOf(idEntrega));
        if(dao.atualizar(item)){
            return "Item foi Atualizado com SUCESSO!";
        }
        return "Item NAO foi Atualizado com SUCESSO!";
    }
    
    
    public String remover(int id){
        ItemModel item = dao.buscar(id);
        if(item==null){
            return "Item NAO Cadastrado!";
        }
        if(dao.remover(item)){
            return "Item foi Removido com SUCESSO!";
        }
        return "Item NAO foi Removido com SUCESSO!";
    }
    
    public ArrayList<ItemModel> listar(){
        return dao.exibirTodos();
    }
}