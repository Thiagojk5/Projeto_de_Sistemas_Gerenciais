/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ClienteModel;
import logistica.model.TelefoneClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class TelefoneClienteDao {
    Conexao conexao = new Conexao();
    
    private TelefoneClienteModel montar(ResultSet rs) throws SQLException{
        ClienteModel cliente = new ClienteModel();
        TelefoneClienteModel telefone = new TelefoneClienteModel();
        
        telefone.setId(rs.getInt("id"));
        telefone.setDdd(rs.getString("ddd"));
        telefone.setNumero(rs.getString("numero"));
        cliente.setId(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("nome_cliente"));
        telefone.setCliente(cliente);
        return telefone;
    }
    
    public ArrayList<TelefoneClienteModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select t.id, t.ddd, t.numero, c.id AS id_cliente, c.nome AS nome_cliente "
                + "from telefone_cliente t inner join cliente c on t.id_cliente=c.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar os Telefone de Clientes: " + e.getMessage(),e);
        }
    }
    
    public TelefoneClienteModel buscar(int id){
        String sql = "select t.id, t.ddd, t.numero, c.id AS id_cliente, c.nome AS nome_cliente "
                + "from telefone_cliente t inner join cliente c on t.id_cliente=c.id where t.id=?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Telefone de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(TelefoneClienteModel telefone){
        String sql = "insert into telefone_cliente(ddd,numero,id_cliente,id) values(?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, telefone.getDdd());
            ps.setString(2, telefone.getNumero());
            ps.setInt(3, telefone.getCliente().getId());
            ps.setInt(4, telefone.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Telefone de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(TelefoneClienteModel telefone){
        String sql = "delete from telefone_cliente where id=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, telefone.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Telefone de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(TelefoneClienteModel telefone){
        String sql = "update telefone_cliente set ddd=?,numero=?,id_cliente=? where id=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, telefone.getDdd());
            ps.setString(2, telefone.getNumero());
            ps.setInt(3, telefone.getCliente().getId());
            ps.setInt(4, telefone.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Telefone de Cliente: " + e.getMessage(),e);
        }
    }
}
