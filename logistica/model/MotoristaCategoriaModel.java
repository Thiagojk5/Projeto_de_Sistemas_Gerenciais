/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class MotoristaCategoriaModel {
    private MotoristaModel motorista;
    private CategoriaCnhModel categoria;
    
    public MotoristaCategoriaModel(){
        motorista = new MotoristaModel();
        categoria = new CategoriaCnhModel();
    }

    public MotoristaCategoriaModel(MotoristaModel motorista, CategoriaCnhModel categoria) {
        this.motorista = motorista;
        this.categoria = categoria;
    }

    public MotoristaModel getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModel motorista) {
        this.motorista = motorista;
    }

    public CategoriaCnhModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCnhModel categoria) {
        this.categoria = categoria;
    }
}
