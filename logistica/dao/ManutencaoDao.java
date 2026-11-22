/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ManutencaoModel;
import logistica.model.VeiculoModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ManutencaoDao {
    Conexao conexao = new Conexao();
    
    private ManutencaoModel montar(ResultSet rs) throws SQLException{
        ManutencaoModel manutencao = new ManutencaoModel();
        VeiculoModel veiculo = new VeiculoModel();
        
        manutencao.setId(rs.getInt("id"));
        manutencao.setData(rs.getString("data"));
        manutencao.setTipo(rs.getString("tipo"));
        manutencao.setCusto(rs.getFloat("custo"));
        manutencao.setDescricao(rs.getString("descricao"));
        veiculo.setId(rs.getInt("id_veiculo"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setModelo(rs.getString("modelo"));
        manutencao.setVeiculo(veiculo);
        return manutencao;
    }
    
    public ArrayList<ManutencaoModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select m.id, m.data, m.tipo, m.custo, m.descricao, v.id AS id_veiculo, "
                + "v.placa, v.modelo from manutencao m inner join veiculo v on m.id_veiculo=v.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Manutencao: " + e.getMessage(),e);
        }
    }
    
    public ManutencaoModel buscar(int id){
        String sql = "select m.id, m.data, m.tipo, m.custo, m.descricao, v.id AS id_veiculo, "
          + "v.placa, v.modelo from manutencao m inner join veiculo v on m.id_veiculo=v.id where m.id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Manutencao: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(ManutencaoModel manutencao){
        String sql = "insert into manutencao(data,tipo,custo,descricao,id_veiculo,id) values(?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, manutencao.getData());
            ps.setString(2, manutencao.getTipo());
            ps.setFloat(3, manutencao.getCusto());
            ps.setString(4, manutencao.getDescricao());
            ps.setInt(5, manutencao.getVeiculo().getId());
            ps.setInt(6, manutencao.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Manutencao: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(ManutencaoModel manutencao){
        String sql = "delete from manutencao where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, manutencao.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Manutencao: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(ManutencaoModel manutencao){
        String sql = "update manutencao set data=?,tipo=?,custo=?,descricao=?,id_veiculo=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, manutencao.getData());
            ps.setString(2, manutencao.getTipo());
            ps.setFloat(3, manutencao.getCusto());
            ps.setString(4, manutencao.getDescricao());
            ps.setInt(5, manutencao.getVeiculo().getId());
            ps.setInt(6, manutencao.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Manutencao: " + e.getMessage(),e);
        }
    }
}