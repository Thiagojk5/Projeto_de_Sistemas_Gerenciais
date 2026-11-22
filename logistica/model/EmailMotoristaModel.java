/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class EmailMotoristaModel {
    private int id;
    private String nomeEmail;
    private MotoristaModel motorista;
    
    public EmailMotoristaModel(){
        id = 0;
        nomeEmail = "";
        motorista = new MotoristaModel();
    }

    public EmailMotoristaModel(int id, String nomeEmail, MotoristaModel motorista) {
        this.id = id;
        this.nomeEmail = nomeEmail;
        this.motorista = motorista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmail() {
        return nomeEmail;
    }

    public void setNomeEmail(String nomeEmail) {
        this.nomeEmail = nomeEmail;
    }

    public MotoristaModel getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModel motorista) {
        this.motorista = motorista;
    }
}
