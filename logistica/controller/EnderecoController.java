/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.controller;
import logistica.dao.EnderecoDao;
import logistica.model.ClienteModel;
import logistica.model.EnderecoModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EnderecoController {
    EnderecoDao dao = new EnderecoDao();
    ClienteModel cliente = new ClienteModel();
    
    private String validar(int id,String cep,String logradouro,String numero,String bairro,String cidade,String uf,int id_cliente){
        if(id < 0){
            return "Codigo de identificacao invalido";
        }
        if(cep==null || cep.isBlank() || cep.length()>8){
            return "Cep invalido!";
        }
        if(logradouro==null || logradouro.isBlank() || logradouro.length()>100){
            return "Logradouro invalido!";
        }
        if(numero==null || numero.isBlank() || numero.length()>10){
            return "Numero invalido!";
        }
        if(bairro==null || bairro.isBlank() || bairro.length()>60){
            return "Bairro invalido!";
        }
        if(cidade==null || cidade.isBlank() || cidade.length()>60){
            return "Cidade invalida!";
        }
        if(uf==null || uf.isBlank() || uf.length()>2){
            return "UF invalido!";
        }
        if(id_cliente<0){
            return "Codigo de identificacao do Cliente invalido!";
        }
        
        return null;
    }
    
    public String cadastrar(int id,String cep,String logradouro,String numero,String bairro,String cidade,String uf,int id_cliente){
        String erro = validar(id, cep, logradouro, numero, bairro, cidade, uf, id_cliente);
        
        if(erro != null){
            return erro;
        }
        if(dao.buscar(id) != null){
            return "Endereco ja cadastrado!";
        }
        cliente.setId(id_cliente);
        EnderecoModel endereco = new EnderecoModel(id, cep, logradouro, numero, bairro, cidade, uf, cliente);
        if(dao.inserir(endereco)){
            return "Endereco cadastrado!";
        }
        
        return "Nao foi possivel cadastrar Endereco!";
    }
    
    public String atualizar(int id,String cep,String logradouro,String numero,String bairro,String cidade,String uf,int id_cliente){
        String erro = validar(id, cep, logradouro, numero, bairro, cidade, uf, id_cliente);
        if(erro != null){
            return erro;
        }
        if(dao.buscar(id) == null){
            return "Endereco nao encontrado!";
        }
        cliente.setId(id_cliente);
        EnderecoModel endereco = new EnderecoModel(id, cep, logradouro, numero, bairro, cidade, uf, cliente);
        if(dao.atualizar(endereco)){
            return "Endereco atualizado!";
        }
        return "Nao foi possivel atualizar Endereco!";
    }
    
    public String remover(int id){
        EnderecoModel endereco = dao.buscar(id);
        if(endereco==null){
            return "Endereco nao encontrado!";
        }
        if(dao.remover(endereco)){
            return "Endereco removido!";
        }
        return "Nao foi possivel remover Endereco!";
    }
    
    public ArrayList<EnderecoModel> listar(){
        return dao.exibirTodos();
    }
}
