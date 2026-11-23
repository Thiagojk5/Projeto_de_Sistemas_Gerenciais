/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.MotoristaCategoriaModel;
import logistica.model.MotoristaModel;
import logistica.model.CategoriaCnhModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class MotoristaCategoriaDao {
    Conexao conexao = new Conexao();
    
    private MotoristaCategoriaModel montar(ResultSet rs) throws SQLException{
        MotoristaCategoriaModel motoristaCategoria = new MotoristaCategoriaModel();
        CategoriaCnhModel categoria = new CategoriaCnhModel();
        MotoristaModel motorista = new MotoristaModel();
        
        motorista.setId(rs.getInt("id"));
        categoria.setId(rs.getInt("id"));
        motoristaCategoria.setMotorista(motorista);
        motoristaCategoria.setCategoria(categoria);
        categoria.setTipoCnh(rs.getString("tipo_cnh"));
        motorista.setNome(rs.getString("nome"));
       
        return motoristaCategoria;
    }
    
    public ArrayList<MotoristaCategoriaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select mc.id_motorista, m.nome AS nome_motorista, mc.id_categoria, c.tipo_cnh "
                + "from motorista_categoria mc inner join motorista m on mc.id_motorista=m.id "
                + "inner join categoria_cnh c on mc.id_categoria=c.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar os Categoria de Motorista: " + e.getMessage(),e);
        }
    }
    
    public MotoristaCategoriaModel buscar(int id){
        String sql = "select mc.id_motorista, m.nome AS nome_motorista, mc.id_categoria, c.tipo_cnh "
                + "from motorista_categoria mc inner join motorista m on mc.id_motorista=m.id "
                + "inner join categoria_cnh c on mc.id_categoria=c.id where mc.id_motorista=?";
        
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar Categoria de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(MotoristaCategoriaModel motoristaCategoria){
        String sql = "insert into motorista_categoria(id_motorista,id_categoria) values(?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, motoristaCategoria.getMotorista().getId());
            ps.setInt(2, motoristaCategoria.getCategoria().getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Categoria de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(MotoristaCategoriaModel motoristaCategoria){
        String sql = "delete from motorista_categoria where id_motorista=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, motoristaCategoria.getMotorista().getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Categoria de Motorista: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(MotoristaCategoriaModel motoristaCategoria){
        String sql = "update motorista_categoria set id_motorista=?, id_categoria=? where id_motorista=?";
        CategoriaCnhModel categoria = new CategoriaCnhModel();
        MotoristaModel motorista = new MotoristaModel();
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, motorista.getId());
            ps.setInt(2, categoria.getId());
            ps.setInt(3, motoristaCategoria.getMotorista().getId()); 
            // OU ps.setInt(1, motorista.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Categoria de Motorista: " + e.getMessage(),e);
        }
    }
}
