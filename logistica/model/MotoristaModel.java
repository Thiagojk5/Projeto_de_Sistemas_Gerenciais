/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class MotoristaModel {
    private int id;
    private String nome, cpf, numeroCnh, validadeCnh;
    
    public MotoristaModel(){
        id = 0;
        nome = "";
        cpf = "";
        numeroCnh = "";
        validadeCnh = "";
    } 

    public MotoristaModel(int id, String nome, String cpf, String numeroCnh, String validadeCnh) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCnh = numeroCnh;
        this.validadeCnh = validadeCnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public String getValidadeCnh() {
        return validadeCnh;
    }

    public void setValidadeCnh(String validadeCnh) {
        this.validadeCnh = validadeCnh;
    }
}
