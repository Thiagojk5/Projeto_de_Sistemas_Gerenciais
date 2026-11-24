/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.VeiculoModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class VeiculoDao {
    Conexao conexao = new Conexao();
    
    private VeiculoModel montar(ResultSet rs) throws SQLException{
        VeiculoModel veiculo = new VeiculoModel();
        
        veiculo.setId(rs.getInt("id"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setTipo(rs.getString("tipo"));
        veiculo.setCapacidadeDeCarga(rs.getFloat("capacidade_de_carga"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setStatus(rs.getString("status"));
        
        return veiculo;
    }
    
    public ArrayList<VeiculoModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select * from veiculo";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados dos Veiculos: " + e.getMessage(),e);
        }
    }
    
    public VeiculoModel buscar(int id){
        String sql = "select * from veiculo where id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Veiculo: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(VeiculoModel veiculo){
        String sql = "insert into veiculo(placa,modelo,tipo,capacidade_de_carga,ano,status,id) values(?,?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getTipo());
            ps.setFloat(4, veiculo.getCapacidadeDeCarga());
            ps.setInt(5, veiculo.getAno());
            ps.setString(6, veiculo.getStatus());
            ps.setInt(7, veiculo.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Veiculo: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(VeiculoModel veiculo){
        String sql = "delete from veiculo where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, veiculo.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Veiculo: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(VeiculoModel veiculo){
        String sql = "update veiculo set placa=?,modelo=?,tipo=?,capacidade_de_carga=?,ano=?,status=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getTipo());
            ps.setFloat(4, veiculo.getCapacidadeDeCarga());
            ps.setInt(5, veiculo.getAno());
            ps.setString(6, veiculo.getStatus());
            ps.setInt(7, veiculo.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Veiculo: " + e.getMessage(),e);
        }
    }
}
