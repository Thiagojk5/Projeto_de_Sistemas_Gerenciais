/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class ManutencaoModel {
   //id, data, tipo, custo, descricao, veiculo
    private int id;
    private float custo;
    private String tipo,data,descricao;
    private VeiculoModel veiculo;
    
    public ManutencaoModel(){
        id = 0;
        custo = 0;
        tipo = "";
        data = "";
        descricao = "";
        veiculo = new VeiculoModel();
    }

    public ManutencaoModel(int id, float custo, String tipo, String data, String descricao, VeiculoModel veiculo) {
        this.id = id;
        this.custo = custo;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public VeiculoModel getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoModel veiculo) {
        this.veiculo = veiculo;
    }
}
