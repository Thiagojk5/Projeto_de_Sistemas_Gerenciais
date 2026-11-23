/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.MotoristaModel;
import logistica.model.TelefoneMotoristaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class TelefoneMotoristaDao {
    Conexao conexao = new Conexao();
    
    private TelefoneMotoristaModel montar(ResultSet rs) throws SQLException{
        MotoristaModel motorista = new MotoristaModel();
        TelefoneMotoristaModel telefone = new TelefoneMotoristaModel();
        
        telefone.setId(rs.getInt("id"));
        telefone.setDdd(rs.getString("ddd"));
        telefone.setNumero(rs.getString("numero"));
        motorista.setId(rs.getInt("id"));
        motorista.setNome(rs.getString("nome"));
        telefone.setMotorista(motorista);
        return telefone;
    }
    
    public ArrayList<TelefoneMotoristaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select t.id, t.ddd, t.numero, m.id AS id_motorista, m.nome AS nome_motorista"
                + "from telefone_motorista t inner join motorista m on e.id_motorista=m.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar os Telefone de Motorista: " + e.getMessage(),e);
        }
    }
    
    public TelefoneMotoristaModel buscar(int id){
        String sql = "select t.id, t.ddd, t.numero, m.id AS id_motorista, m.nome AS nome_motorista"
                + "from telefone_motorista t inner join motorista m on e.id_motorista=m.id where t.id=?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Telefone de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(TelefoneMotoristaModel telefone){
        String sql = "insert into telefone_motorista(ddd,numero,id_motorista,id) values(?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, telefone.getDdd());
            ps.setString(2, telefone.getNumero());
            ps.setInt(3, telefone.getMotorista().getId());
            ps.setInt(4, telefone.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Telefone de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(TelefoneMotoristaModel telefone){
        String sql = "delete from telefone_motorista where id=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, telefone.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Telefone de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(TelefoneMotoristaModel telefone){
        String sql = "update telefone_cliente set ddd=?,numero=?,id_motorista=? where id=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, telefone.getDdd());
            ps.setString(2, telefone.getNumero());
            ps.setInt(3, telefone.getMotorista().getId());
            ps.setInt(4, telefone.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Telefone de Motorista: " + e.getMessage(),e);
        }
    }
}
