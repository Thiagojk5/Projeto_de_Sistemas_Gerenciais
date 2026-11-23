/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.FuncionarioModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class FuncionarioDao {
    Conexao conexao = new Conexao();
    
    private FuncionarioModel montar(ResultSet rs) throws SQLException{
       FuncionarioModel funcionario = new FuncionarioModel();
       
       funcionario.setId(rs.getInt("id"));
       funcionario.setNome(rs.getString("nome"));
       funcionario.setCargo(rs.getString("cargo"));
       
       return funcionario;
    }
    
    public ArrayList<FuncionarioModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from funcionario";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Funcionarios: " + e.getMessage(),e);
        }
    }
    
    public FuncionarioModel buscar(int id){
        String sql = "select * from funcionario where id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Funcionario: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(FuncionarioModel funcionario){
        String sql = "insert into funcionario(nome,cargo,id) values(?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCargo());
            ps.setInt(3, funcionario.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Funcionario: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(FuncionarioModel funcionario){
        String sql = "delete from funcionario where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, funcionario.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Funcionario: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(FuncionarioModel funcionario){
        String sql = "update funcionario set nome = ?, cargo = ? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCargo());
            ps.setInt(3, funcionario.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Funcionario: " + e.getMessage(),e);
        }
    }
}
