/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.modelo;

/**
 *
 * @author Usuario
 */
public class Automovil {
    
    private int idAuto;
    private int modelo;
    private String marca;
    private String placas;
    private String color;

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Automovil => [ Id = "+getIdAuto()+" | Marca = " + getMarca() + " | Color = "+getColor()+" | Placas = "+getPlacas()+""
                + " | Modelo = "+getModelo()+" ]";
    }
    
}
