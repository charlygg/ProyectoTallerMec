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
public class Empleado {
    
    private int idEmpleado;
    private String nombre;
    private String apePat;
    private String apeMat;
    private int habilitado;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre, String apePat, String apeMat, int habilitado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;
        this.habilitado = habilitado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }
    
    
    
}
