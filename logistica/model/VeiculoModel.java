/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class VeiculoModel {
    private int id, ano;
    private String placa, modelo, tipo, status;
    private float capacidadeDeCarga;
    
    public VeiculoModel(){
        id = 0;
        ano = 0;
        placa = "";
        modelo = "";
        tipo = "";
        status = "";
        capacidadeDeCarga = 0;
    }

    public VeiculoModel(int id, int ano, String placa, String modelo, String tipo, String status, float capacidadeDeCarga) {
        this.id = id;
        this.ano = ano;
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.status = status;
        this.capacidadeDeCarga = capacidadeDeCarga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getCapacidadeDeCarga() {
        return capacidadeDeCarga;
    }

    public void setCapacidadeDeCarga(float capacidadeDeCarga) {
        this.capacidadeDeCarga = capacidadeDeCarga;
    }
}
