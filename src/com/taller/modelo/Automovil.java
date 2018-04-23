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
    private String modelo;
    private String marca;
    private String placas;
    private String color;
    private String linea;
    private int anio;
    private int kilometraje;
    private String serie;
    private int idCliente;

    public Automovil() {
    }

    public Automovil(int idAuto, String modelo, String marca, String placas, String color, String linea, int anio, int kilometraje, String serie, int idCliente) {
        this.idAuto = idAuto;
        this.modelo = modelo;
        this.marca = marca;
        this.placas = placas;
        this.color = color;
        this.linea = linea;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.serie = serie;
        this.idCliente = idCliente;
    }
    
    

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
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

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    

    @Override
    public String toString() {
        return "Automovil => [ Id = "+getIdAuto()+" | Marca = " + getMarca() + " | Color = "+getColor()+" | Placas = "+getPlacas()+""
                + " | Modelo = "+getModelo()+" ]";
    }
    
}
