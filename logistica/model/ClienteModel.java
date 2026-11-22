/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class ClienteModel {
    private int id;
    private String nome, cpfCNPJ;
    
    public ClienteModel(){
        id = 0;
        nome = "";
        cpfCNPJ = "";
    }

    public ClienteModel(int id, String nome, String cpfCNPJ) {
        this.id = id;
        this.nome = nome;
        this.cpfCNPJ = cpfCNPJ;
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

    public String getCpfCNPJ() {
        return cpfCNPJ;
    }

    public void setCpfCNPJ(String cpfCNPJ) {
        this.cpfCNPJ = cpfCNPJ;
    }
}
