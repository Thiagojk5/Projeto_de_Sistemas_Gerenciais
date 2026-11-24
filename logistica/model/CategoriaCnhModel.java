/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logistica.model;

/**
 *
 * @author Thiago
 */
public class CategoriaCnhModel {
    private int id;
    private String tipoCnh;
    
    public CategoriaCnhModel(){
        id = 0;
        tipoCnh = "";
    }

    public CategoriaCnhModel(int id, String tipoCnh) {
        this.id = id;
        this.tipoCnh = tipoCnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoCnh() {
        return tipoCnh;
    }

    public void setTipoCnh(String tipoCnh) {
        this.tipoCnh = tipoCnh;
    }
}
