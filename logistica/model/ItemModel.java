/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class ItemModel {
    // id, descricao, peso, volume, valorDeclarado, entrega
    private int id;
    private float peso, volume, valorDeclarado;
    private String descricao, entrega;
    
    public ItemModel(){
        id = 0;
        peso = 0;
        volume = 0;
        valorDeclarado = 0;
        descricao = "";
        entrega = "";
    }

    public ItemModel(int id, float peso, float volume, float valorDeclarado, String descricao, String entrega) {
        this.id = id;
        this.peso = peso;
        this.volume = volume;
        this.valorDeclarado = valorDeclarado;
        this.descricao = descricao;
        this.entrega = entrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(float valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
}
