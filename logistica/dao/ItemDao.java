/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ItemModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ItemDao {
    Conexao conexao = new Conexao();
    
    private ItemModel montar(ResultSet rs) throws SQLException{
        ItemModel item = new ItemModel();
        
        item.setId(rs.getInt("id"));
        item.setDescricao(rs.getString("descricao"));
        item.setPeso(rs.getFloat("peso"));
        item.setVolume(rs.getFloat("volume"));
        item.setValorDeclarado(rs.getFloat("valor_declarado"));
        item.setEntrega(rs.getString("id_entrega"));
        return item;
    }
    
    public ArrayList<ItemModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select i.id, i.descricao, i.peso, i.volume, i.valor_declarado, i.id_entrega from item i";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Item: " + e.getMessage(),e);
        }
    }
    
    public ItemModel buscar(int id){
        String sql = "select i.id, i.descricao, i.peso, i.volume, i.valor_declarado, i.id_entrega from item i where i.id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Item: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(ItemModel item){
        String sql = "insert into item(descricao,peso,volume,valor_declarado,id_entrega,id) values(?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, item.getDescricao());
            ps.setFloat(2, item.getPeso());
            ps.setFloat(3, item.getVolume());
            ps.setFloat(4, item.getValorDeclarado());
            ps.setInt(5, Integer.parseInt(item.getEntrega()));
            ps.setInt(6, item.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Item: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(ItemModel item){
        String sql = "delete from item where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, item.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Item: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(ItemModel item){
        String sql = "update item set descricao=?,peso=?,volume=?,valor_declarado=?,id_entrega=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, item.getDescricao());
            ps.setFloat(2, item.getPeso());
            ps.setFloat(3, item.getVolume());
            ps.setFloat(4, item.getValorDeclarado());
            ps.setInt(5, Integer.parseInt(item.getEntrega()));
            ps.setInt(6, item.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Item: " + e.getMessage(),e);
        }
    }
}