/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.CategoriaCnhModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class CategoriaCnhDao {
    Conexao conexao = new Conexao();
    
    private CategoriaCnhModel montar(ResultSet rs) throws SQLException{
       CategoriaCnhModel categoria = new CategoriaCnhModel();
       
       categoria.setId(rs.getInt("id"));
       categoria.setTipoCnh(rs.getString("tipo_cnh"));
       return categoria;
    }
    
    public ArrayList<CategoriaCnhModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from categoria_cnh";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados dos Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
    
    public CategoriaCnhModel buscar(int id){
        String sql = "select * from categoria_cnh where id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
    
    public CategoriaCnhModel buscarPorTipo(String tipoCnh){
        String sql = "select * from categoria_cnh where tipo_cnh = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps =c.prepareStatement(sql)){
            ps.setString(1, tipoCnh);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Categoria de Habilitacao por tipo: " + e.getMessage(),e);
        }
    }
    // conferir je tem inserção de id na tela
    public boolean inserir(CategoriaCnhModel categoria){
        String sql = "insert into categoria_cnh(tipo_cnh,id) values(?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, categoria.getTipoCnh());
            ps.setInt(2, categoria.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
    
    public boolean removerPorId(CategoriaCnhModel categoria){
        String sql = "delete from categoria_cnh where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, categoria.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(CategoriaCnhModel categoria){
        String sql = "delete from categoria_cnh where tipo_cnh = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, categoria.getTipoCnh());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(CategoriaCnhModel categoria){
        String sql = "update categoria_cnh set tipo_cnh=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, categoria.getTipoCnh());
            ps.setInt(2, categoria.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Categoria de Habilitacao: " + e.getMessage(),e);
        }
    }
}
