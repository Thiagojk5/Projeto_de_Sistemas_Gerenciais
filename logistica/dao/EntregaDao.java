/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.dao;
import java.sql.*;
import logistica.model.EnderecoModel;
import logistica.model.ClienteModel;
import logistica.model.EntregaModel;
import logistica.model.RotaModel;
import logistica.model.FuncionarioModel;
import java.util.ArrayList;
/**
 *
 * @author Thiago
 */
public class EntregaDao {
    Conexao conexao = new Conexao();
    
    private EntregaModel montar(ResultSet rs) throws SQLException{
        EnderecoModel endereco = new EnderecoModel();
        ClienteModel cliente = new ClienteModel();
        FuncionarioModel funcionario = new FuncionarioModel();
        RotaModel rota = new RotaModel();
        EntregaModel entrega = new EntregaModel();
        //id, data_solicitacao, data_prevista, data_realizacao, status, 
        //valor_do_frete, id_cliente, id_endereco, id_rota, id_funcionario
        entrega.setId(rs.getInt("id"));
        entrega.setDataSolicitacao(rs.getString("data_solicitacao"));
        entrega.setDataPrevista(rs.getString("data_prevista"));
        entrega.setDataRealizacao(rs.getString("data_realizacao"));
        entrega.setStatus(rs.getString("status"));
        entrega.setValorDoFrete(rs.getFloat("valor_do_frete"));
        cliente.setId(rs.getInt("id_cliente"));
        cliente.setNome(rs.getString("nome_cliente"));
        endereco.setId(rs.getInt("id_endereco"));
        rota.setId(rs.getInt("id_rota"));
        rota.setOrigem(rs.getString("origem"));
        rota.setDestino(rs.getString("destino"));
        rota.setTempoEstimado(rs.getString("tempo_estimado"));
        funcionario.setId(rs.getInt("id_funcionario"));
        funcionario.setNome(rs.getString("nome_funcionario"));
        entrega.setCliente(cliente);
        entrega.setEndereco(endereco);
        entrega.setRota(rota);
        entrega.setFuncionario(funcionario);
        
        return entrega;
    }
    
    public ArrayList<EntregaModel> exibirTodos(){
        ArrayList lista = new ArrayList<>();
        String sql = "select e.id,e.data_solicitacao,e.data_prevista,e.data_realizacao,e.status,e.valor_do_frete,"
                + "e.id_cliente,e.id_endereco,e.id_rota,e.id_funcionario,c.nome AS nome_cliente,"
                + "f.nome AS nome_funcionario,r.origem,r.destino,r.tempo_estimado "
                + "from entrega e inner join cliente c on e.id_cliente=c.id "
                + "inner join funcionario f on e.id_funcionario=f.id "
                + "inner join rota r on e.id_rota=r.id";
        
        try(Connection c = conexao.conectar();Statement st = c.createStatement();ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(montar(rs));
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao carregar dados da Entrega: " + e.getMessage(),e);
        }
    }
    
    public EntregaModel buscar(int id){
        String sql = "select e.id,e.data_solicitacao,e.data_prevista,e.data_realizacao,e.status,e.valor_do_frete,"
                + "e.id_cliente,e.id_endereco,e.id_rota,e.id_funcionario,c.nome AS nome_cliente,"
                + "f.nome AS nome_funcionario,r.origem,r.destino,r.tempo_estimado "
                + "from entrega e inner join cliente c on e.id_cliente=c.id "
                + "inner join funcionario f on e.id_funcionario=f.id "
                + "inner join rota r on e.id_rota=r.id where e.id=?";
        
        try(Connection c = conexao.conectar(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar dados de Entrega: " + e.getMessage(),e);
        }
    }
    
    public boolean inserir(EntregaModel entrega){
        String sql = "insert into entrega(data_solicitacao,data_prevista,data_realizacao,status,"
                + "valor_do_frete,id_cliente,id_endereco,id_rota,id_funcionario,id) values(?,?,?,?,?,?,?,?,?,?)";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, entrega.getDataSolicitacao());
            ps.setString(2, entrega.getDataPrevista());
            ps.setString(3, entrega.getDataRealizacao());
            ps.setString(4, entrega.getStatus());
            ps.setFloat(5, entrega.getValorDoFrete());
            ps.setInt(6, entrega.getCliente().getId());
            ps.setInt(7, entrega.getEndereco().getId());
            ps.setInt(8, entrega.getRota().getId());
            ps.setInt(9, entrega.getFuncionario().getId());
            ps.setInt(10, entrega.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir Entrega: " + e.getMessage(),e);
        }
    }
    
    public boolean remover(EntregaModel entrega){
        String sql = "delete from entrega where id = ?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, entrega.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao excluir Entrega: " + e.getMessage(),e);
        }
    }
    
    public boolean atualizar(EntregaModel entrega){
        String sql = "update entrega set data_solicitacao=?,data_prevista=?,data_realizacao=?,status=?,"
                + "valor_do_frete=?,id_cliente=?,id_endereco=?,id_rota=?,id_funcionario=? where id=?";
        
        try(Connection c = conexao.conectar();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, entrega.getDataSolicitacao());
            ps.setString(2, entrega.getDataPrevista());
            ps.setString(3, entrega.getDataRealizacao());
            ps.setString(4, entrega.getStatus());
            ps.setFloat(5, entrega.getValorDoFrete());
            ps.setInt(6, entrega.getCliente().getId());
            ps.setInt(7, entrega.getEndereco().getId());
            ps.setInt(8, entrega.getRota().getId());
            ps.setInt(9, entrega.getFuncionario().getId());
            ps.setInt(10, entrega.getId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar Entrega: " + e.getMessage(),e);
        }
    }
}
