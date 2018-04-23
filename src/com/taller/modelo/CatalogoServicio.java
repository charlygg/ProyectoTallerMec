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
public class CatalogoServicio {
    
    private int idCatalogoServicio;
    private String nombre;
    private String detalle;
    private String activo;
    private double precio;

    public CatalogoServicio() {
    }

    public CatalogoServicio(int idCatalogoServicio, String nombre, String detalle, String activo, double precio) {
        this.idCatalogoServicio = idCatalogoServicio;
        this.nombre = nombre;
        this.detalle = detalle;
        this.activo = activo;
        this.precio = precio;
    }

    public int getIdCatalogoServicio() {
        return idCatalogoServicio;
    }

    public void setIdCatalogoServicio(int idCatalogoServicio) {
        this.idCatalogoServicio = idCatalogoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
