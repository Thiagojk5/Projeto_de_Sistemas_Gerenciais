/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.ViagemModel;
import logistica.model.MotoristaModel;
import logistica.model.VeiculoModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class ViagemDao {
    Conexao conexao = new Conexao();
    
    private ViagemModel montar(ResultSet rs) throws SQLException{
        ViagemModel viagem = new ViagemModel();
        MotoristaModel motorista = new MotoristaModel();
        VeiculoModel veiculo = new VeiculoModel();
        
        viagem.setId(rs.getInt("id"));
        viagem.setDataSaida(rs.getString("data_saida"));
        viagem.setDataChegada(rs.getString("data_chegada"));
        viagem.setEntrega(rs.getString("id_entrega"));
        motorista.setId(rs.getInt("id_motorista"));
        motorista.setNome(rs.getString("nome_motorista"));
        viagem.setMotorista(motorista);
        veiculo.setId(rs.getInt("id_veiculo"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setModelo(rs.getString("modelo"));
        viagem.setVeiculo(veiculo);
        return viagem;
    }
    
    public ArrayList<ViagemModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select v.id, v.data_saida, v.data_chegada, v.id_entrega, m.id AS id_motorista, "
                + "m.nome AS nome_motorista, ve.id AS id_veiculo, ve.placa, ve.modelo from viagem v "
                + "inner join motorista m on v.id_motorista=m.id inner join veiculo ve on v.id_veiculo=ve.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados de Viagem: " + e.getMessage(),e);
        }
    }
    
    public ViagemModel buscar(int id){
        String sql = "select v.id, v.data_saida, v.data_chegada, v.id_entrega, m.id AS id_motorista, "
          + "m.nome AS nome_motorista, ve.id AS id_veiculo, ve.placa, ve.modelo from viagem v "
          + "inner join motorista m on v.id_motorista=m.id inner join veiculo ve on v.id_veiculo=ve.id where v.id = ?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Viagem: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(ViagemModel viagem){
        String sql = "insert into viagem(id,data_saida,data_chegada,id_entrega,id_motorista,id_veiculo) values(?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, viagem.getId());
            ps.setString(2, viagem.getDataSaida());
            ps.setString(3, viagem.getDataChegada());
            ps.setInt(4, Integer.parseInt(viagem.getEntrega()));
            ps.setInt(5, viagem.getMotorista().getId());
            ps.setInt(6, viagem.getVeiculo().getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Viagem: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(ViagemModel viagem){
        String sql = "delete from viagem where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, viagem.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Viagem: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(ViagemModel viagem){
        String sql = "update viagem set data_saida=?,data_chegada=?,id_entrega=?,id_motorista=?,id_veiculo=? where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, viagem.getDataSaida());
            ps.setString(2, viagem.getDataChegada());
            ps.setInt(3, Integer.parseInt(viagem.getEntrega()));
            ps.setInt(4, viagem.getMotorista().getId());
            ps.setInt(5, viagem.getVeiculo().getId());
            ps.setInt(6, viagem.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Viagem: " + e.getMessage(),e);
        }
    }
}