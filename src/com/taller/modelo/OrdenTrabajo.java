/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class OrdenTrabajo {
    
    private int idOrdenTrabajo;
    private String folio;
    private int idCliente;
    private int idAuto;
    private Date fechaRegistro;
    private String estado;
    private Date fechaCompletado;
    private int idUsuario;
    private int idEmpleadoAsignado;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(int idOrdenTrabajo, String folio, int idCliente, int idAuto, Date fechaRegistro, String estado, Date fechaCompletado, int idUsuario, int idEmpleadoAsignado) {
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.folio = folio;
        this.idCliente = idCliente;
        this.idAuto = idAuto;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.fechaCompletado = fechaCompletado;
        this.idUsuario = idUsuario;
        this.idEmpleadoAsignado = idEmpleadoAsignado;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(Date fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpleadoAsignado() {
        return idEmpleadoAsignado;
    }

    public void setIdEmpleadoAsignado(int idEmpleadoAsignado) {
        this.idEmpleadoAsignado = idEmpleadoAsignado;
    }
    
    
    
}
