/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.MotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class MotoristaDao {
    Conexao conexao = new Conexao();
    
    private MotoristaModel montar(ResultSet rs) throws SQLException{
       MotoristaModel motorista = new MotoristaModel();
       
       motorista.setId(rs.getInt("id"));
       motorista.setNome(rs.getString("nome"));
       motorista.setCpf(rs.getString("cpf"));
       motorista.setNumeroCnh(rs.getString("numero_cnh"));
       motorista.setValidadeCnh(rs.getString("validade_cnh"));
       return motorista;
    }
    
    public ArrayList<MotoristaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from motorista";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados dos Motoristas: " + e.getMessage(),e);
        }
    }
    
    public MotoristaModel buscar(int id){
        String sql = "select * from motorista where id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(MotoristaModel motorista){
        String sql = "insert into motorista(nome,cpf,numero_cnh,validade_cnh,id) values(?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, motorista.getNome());
            ps.setString(2, motorista.getCpf());
            ps.setString(3, motorista.getNumeroCnh());
            ps.setString(4, motorista.getValidadeCnh());
            ps.setInt(5, motorista.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(MotoristaModel motorista){
        String sql = "delete from motorista where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, motorista.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(MotoristaModel motorista){
        String sql = "update motorista set nome = ?, cpf = ?, numero_cnh=?, validade_cnh=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, motorista.getNome());
            ps.setString(2, motorista.getCpf());
            ps.setString(3, motorista.getNumeroCnh());
            ps.setString(4, motorista.getValidadeCnh());
            ps.setInt(5, motorista.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Motorista: " + e.getMessage(),e);
        }
    }
}
