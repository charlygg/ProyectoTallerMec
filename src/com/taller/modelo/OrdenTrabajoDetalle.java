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
public class OrdenTrabajoDetalle {
    
    private int idOrdenTrabajoDetalle;
    private int idOrdenTrabajo;
    private int idCatalogoServicio;

    public OrdenTrabajoDetalle() {
    }

    public OrdenTrabajoDetalle(int idOrdenTrabajoDetalle, int idOrdenTrabajo, int idCatalogoServicio) {
        this.idOrdenTrabajoDetalle = idOrdenTrabajoDetalle;
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.idCatalogoServicio = idCatalogoServicio;
    }

    public int getIdOrdenTrabajoDetalle() {
        return idOrdenTrabajoDetalle;
    }

    public void setIdOrdenTrabajoDetalle(int idOrdenTrabajoDetalle) {
        this.idOrdenTrabajoDetalle = idOrdenTrabajoDetalle;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public int getIdCatalogoServicio() {
        return idCatalogoServicio;
    }

    public void setIdCatalogoServicio(int idCatalogoServicio) {
        this.idCatalogoServicio = idCatalogoServicio;
    }
    
    
    
}
