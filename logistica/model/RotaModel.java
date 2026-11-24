/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class RotaModel {
    // id, origem, destino, distanciaKm, tempoEstimado
    private int id;
    private float distanciaKm;
    private String origem, destino, tempoEstimado;
    
    public RotaModel(){
        id = 0;
        distanciaKm = 0;
        origem = "";
        destino =  "";
        tempoEstimado =  "";
    }

    public RotaModel(int id, float distanciaKm, String origem, String destino, String tempoEstimado) {
        this.id = id;
        this.distanciaKm = distanciaKm;
        this.origem = origem;
        this.destino = destino;
        this.tempoEstimado = tempoEstimado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(float distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(String tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
