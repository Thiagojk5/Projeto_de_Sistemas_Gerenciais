/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.EnderecoModel;
import logistica.model.ClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EnderecoDao {
    Conexao conexao = new Conexao();
    
    private EnderecoModel montar(ResultSet rs) throws SQLException{
        EnderecoModel endereco = new EnderecoModel();
        ClienteModel cliente = new ClienteModel();
        
        endereco.setId(rs.getInt("id"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCep(rs.getString("cep"));
        endereco.setCidade(rs.getString("cidade"));
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        endereco.setCliente(cliente);
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setUf(rs.getString("uf"));
        return endereco;
    }
    
    public ArrayList<EnderecoModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select e.id,e.cep, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, c.id AS id_cliente, "
                + "c.nome AS nome_cliente from endereco e inner join cliente c on e.id_cliente=c.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Endereco: " + e.getMessage(),e);
        }
    }
    
    public EnderecoModel buscar(int id){
        String sql = "select e.id,e.cep, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, c.id AS id_cliente, "
          + "c.nome AS nome_cliente from endereco e inner join cliente c on e.id_cliente=c.id where e.id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Endereco: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(EnderecoModel endereco){
        String sql = "insert into endereco(cep,logradouro,numero,bairro,cidade,uf,id_cliente,id) values(?,?,?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setInt(7, endereco.getCliente().getId());
            ps.setInt(8, endereco.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Endereco: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(EnderecoModel endereco){
        String sql = "delete from endereco where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, endereco.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Endereco: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(EnderecoModel endereco){
        String sql = "update endereco set cep=?,logradouro=?,numero=?,bairro=?,cidade=?,uf=?,id_cliente=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setInt(7, endereco.getCliente().getId());
            ps.setInt(8, endereco.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Endeteco: " + e.getMessage(),e);
        }
    }
}
