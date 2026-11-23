/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ClienteModel;
import logistica.model.EmailClienteModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EmailClienteDao {
    Conexao conexao = new Conexao();
    
    private EmailClienteModel montar(ResultSet rs) throws SQLException{
        ClienteModel cliente = new ClienteModel();
        EmailClienteModel email = new EmailClienteModel();
        
        email.setId(rs.getInt("id"));
        email.setEmail(rs.getString("nome_email"));
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        email.setCliente(cliente);
        return email;
    }
    
    public ArrayList<EmailClienteModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select e.id, e.nome_email, c.id AS id_cliente, c.nome AS nome_cliente "
                + "from email_cliente e inner join cliente c on e.id_cliente=c.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar os Emails de Clientes: " + e.getMessage(),e);
        }
    }
    
    public EmailClienteModel buscar(int id){
        String sql = "select e.id, e.nome_email, c.id AS id_cliente, c.nome AS nome_cliente "
                + "from email_cliente e inner join cliente c on e.id_cliente=c.id where e.id=?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Email de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(EmailClienteModel email){
        String sql = "insert into email_cliente(nome_email,id_cliente,id) values(?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email.getEmail());
            ps.setInt(2, email.getCliente().getId());
            ps.setInt(3, email.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Email de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(EmailClienteModel email){
        String sql = "delete from email_cliente where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, email.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Email de Cliente: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(EmailClienteModel email){
        String sql = "update email_cliente set nome_email=?,id_cliente=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email.getEmail());
            ps.setInt(2, email.getCliente().getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Email de Cliente: " + e.getMessage(),e);
        }
    }
}
