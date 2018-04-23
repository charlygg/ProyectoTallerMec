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
public class Factura {
    
    private int idFactura;
    private Date fechaEmision;
    private Date fechaPago;
    private int idOrdenTrabajo;

    public Factura() {
    }

    public Factura(int idFactura, Date fechaEmision, Date fechaPago, int idOrdenTrabajo) {
        this.idFactura = idFactura;
        this.fechaEmision = fechaEmision;
        this.fechaPago = fechaPago;
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }
    
    
    
}
