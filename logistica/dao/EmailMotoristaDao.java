/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.MotoristaModel;
import logistica.model.EmailMotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EmailMotoristaDao {
    Conexao conexao = new Conexao();
    
    private EmailMotoristaModel montar(ResultSet rs) throws SQLException{
        MotoristaModel motorista = new MotoristaModel();
        EmailMotoristaModel email = new EmailMotoristaModel();
        
        email.setId(rs.getInt("id"));
        email.setNomeEmail(rs.getString("nome_email"));
        motorista.setId(rs.getInt("id_motorista"));
        motorista.setNome(rs.getString("nome_motorista"));
        email.setMotorista(motorista);
        return email;
    }
    
    public ArrayList<EmailMotoristaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select e.id, e.nome_email, m.id AS id_motorista, m.nome AS nome_motorista "
                + "from email_motorista e inner join motorista m on e.id_motorista=m.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar os Emails de Motorista: " + e.getMessage(),e);
        }
    }
    
    public EmailMotoristaModel buscar(int id){
        String sql = "select e.id, e.nome_email, m.id AS id_motorista, m.nome AS nome_motorista "
                + "from email_motorista e inner join motorista m on e.id_motorista=m.id where e.id=?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Email de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(EmailMotoristaModel email){
        String sql = "insert into email_motorista(nome_email,id_motorista,id) values(?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email.getNomeEmail());
            ps.setInt(2, email.getMotorista().getId());
            ps.setInt(3, email.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Email de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(EmailMotoristaModel email){
        String sql = "delete from email_motorista where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, email.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Email de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(EmailMotoristaModel email){
        String sql = "update email_motorista set nome_email=?,id_motorista=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, email.getNomeEmail());
            ps.setInt(2, email.getMotorista().getId());
            ps.setInt(3, email.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Email de Motorista: " + e.getMessage(),e);
        }
    }
    
}
