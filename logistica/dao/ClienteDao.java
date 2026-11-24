/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ClienteDao {
    Conexao conexao = new Conexao();
    
    private ClienteModel montar(ResultSet rs) throws SQLException{
        ClienteModel cliente = new ClienteModel();
        
        cliente.setId(rs.getInt("id"));
        cliente.setCpfCNPJ(rs.getString("cpf_cnpj"));
        cliente.setNome(rs.getString("nome"));
        return cliente;
    }
    
    public ArrayList<ClienteModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from cliente";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Clientes: " + e.getMessage(),e);
        }
    }
    
    public ClienteModel buscar(int id){
        String sql = "select * from cliente where id= ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Clientes: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(ClienteModel cliente){
        String sql = "insert into cliente(nome,cpf_cnpj,id) values(?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpfCNPJ());
            ps.setInt(3, cliente.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(ClienteModel cliente){
        String sql = "delete from cliente where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, cliente.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(ClienteModel cliente){
        String sql = "update cliente set nome = ?, cpf_cnpj = ? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpfCNPJ());
            ps.setInt(3, cliente.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Cliente: " + e.getMessage(),e);
        }
    }
}
