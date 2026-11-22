/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class TelefoneMotoristaModel {
    private int id;
    private String ddd, numero;
    private MotoristaModel motorista;
    
    public TelefoneMotoristaModel(){
        id = 0;
        ddd = "";
        numero = "";
        motorista = new MotoristaModel();
    }

    public TelefoneMotoristaModel(int id, String ddd, String numero, MotoristaModel motorista) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.motorista = motorista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public MotoristaModel getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModel motorista) {
        this.motorista = motorista;
    }
}
