/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.RotaModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class RotaDao {
    Conexao conexao = new Conexao();
    
    private RotaModel montar(ResultSet rs) throws SQLException{
       // id, origem, destino, distancia_km, tempo_estimado
       RotaModel rota = new RotaModel();
       
       rota.setId(rs.getInt("id"));
       rota.setOrigem(rs.getString("origem"));
       rota.setDestino(rs.getString("destino"));
       rota.setDistanciaKm(rs.getFloat("distancia_km"));
       rota.setTempoEstimado(rs.getString("tempo_estimado"));
       
       return rota;
    }
    
    public ArrayList<RotaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from rota";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados das Rotas: " + e.getMessage(),e);
        }
    }
    
    public RotaModel buscar(int id){
        String sql = "select * from rota where id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados da Rota: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(RotaModel rota){
        String sql = "insert into rota(origem,destino,distancia_km,tempo_estimado,id) values(?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, rota.getOrigem());
            ps.setString(2, rota.getDestino());
            ps.setFloat(3, rota.getDistanciaKm());
            ps.setString(4, rota.getTempoEstimado());
            ps.setInt(5, rota.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Rota: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(RotaModel rota){
        String sql = "delete from rota where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, rota.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Rota: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(RotaModel rota){
        String sql = "update rota set origem=?,destino=?,distancia_km=?,tempo_estimado=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, rota.getOrigem());
            ps.setString(2, rota.getDestino());
            ps.setFloat(3, rota.getDistanciaKm());
            ps.setString(4, rota.getTempoEstimado());
            ps.setInt(5, rota.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Rota: " + e.getMessage(),e);
        }
    }
}
