/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class ViagemModel {
    // id, dataSaida, dataChegada, entrega, motorista, veiculo
    private int id;
    private String dataSaida, dataChegada, entrega;
    private MotoristaModel motorista;
    private VeiculoModel veiculo;
    
    public ViagemModel(){
        id = 0;
        dataSaida = "";
        dataChegada = "";
        entrega = "";
        motorista = new MotoristaModel();
        veiculo = new VeiculoModel();
    }

    public ViagemModel(int id, String dataSaida, String dataChegada, String entrega, MotoristaModel motorista, VeiculoModel veiculo) {
        this.id = id;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.entrega = entrega;
        this.motorista = motorista;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public MotoristaModel getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModel motorista) {
        this.motorista = motorista;
    }

    public VeiculoModel getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoModel veiculo) {
        this.veiculo = veiculo;
    }
}
