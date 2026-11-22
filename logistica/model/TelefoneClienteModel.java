/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class TelefoneClienteModel {
    private int id;
    private String ddd, numero;
    private ClienteModel cliente;
    
    public TelefoneClienteModel(){
        id = 0;
        ddd = "";
        numero = "";
        cliente = new ClienteModel();
    }

    public TelefoneClienteModel(int id, String ddd, String numero, ClienteModel cliente) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.cliente = cliente;
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

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
