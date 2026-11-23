/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class EmailClienteModel {
    private int id;
    private String email;
    private ClienteModel cliente;
    
    public EmailClienteModel(){
        id = 0;
        email = "";
        cliente = new ClienteModel();
    }

    public EmailClienteModel(int id, String email, ClienteModel cliente) {
        this.id = id;
        this.email = email;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
