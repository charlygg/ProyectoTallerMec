/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.Empleado;
import com.taller.modelo.OrdenTrabajoDetalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class OrdenTrabajoDetalleDao extends ConexionMySQL{
    
    
    public OrdenTrabajoDetalle obtenerOrdenTrabajoDetalleById(int id){        
            OrdenTrabajoDetalle o = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_orden_trabajo_detalle_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                o = new OrdenTrabajoDetalle();
                o.setIdOrdenTrabajoDetalle(res.getInt(1));
                o.setIdOrdenTrabajo(res.getInt(2));
                o.setIdCatalogoServicio(res.getInt(3));
            }
            
            cn.close();
            res.close();
            call.close();
            
            return o;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();  
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return null;
    }
        
        
    public OrdenTrabajoDetalle registrarOrdenTrabajoDetalle(OrdenTrabajoDetalle o){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_orden_trabajo_detalle(?,?,?)}");
            call.setInt(1, o.getIdOrdenTrabajoDetalle());
            call.registerOutParameter(1, Types.INTEGER);
            call.setInt(2, o.getIdOrdenTrabajo());
            call.setInt(3, o.getIdCatalogoServicio());

            ResultSet res = call.executeQuery();
            
            if(res.next()){               
                o.setIdOrdenTrabajoDetalle(res.getInt(3));
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
            }
            
            cn.close();
            res.close();
            call.close();
            
            return o;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();                
            }
        }        
        return o;
    }
    
    public boolean actualizarOrdenTrabajoDetalle(OrdenTrabajoDetalle o){
        try{
            int actualizado = 0;
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_orden_trabajo_detalle(?,?,?)}");
            call.setInt(1, o.getIdOrdenTrabajoDetalle());
            call.registerOutParameter(1, Types.INTEGER);
            call.setInt(2, o.getIdOrdenTrabajo());
            call.setInt(3, o.getIdCatalogoServicio());

            ResultSet res = call.executeQuery();
            
            if(res.next()){    
                actualizado = res.getInt("actualizado");
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
            }
            
            cn.close();
            res.close();
            call.close();
            
            if(actualizado == 1){
                return true;
            }  
            return false;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();                
            }
        }        
        return false;
    }
    
    public ArrayList<OrdenTrabajoDetalle> obtenerAllOrdenTrabajoDetalle(){
        ArrayList<OrdenTrabajoDetalle> listaOrdenTrabajoDetalle = new ArrayList<OrdenTrabajoDetalle>();
        OrdenTrabajoDetalle o = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_orden_trabajo_detalle_by_id(?)}");
            call.setInt(1, -2);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                o = new OrdenTrabajoDetalle();
                o.setIdOrdenTrabajoDetalle(res.getInt(1));
                o.setIdOrdenTrabajo(res.getInt(2));
                o.setIdCatalogoServicio(res.getInt(3));
                listaOrdenTrabajoDetalle.add(o);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return listaOrdenTrabajoDetalle;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();  
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return null;
    }
    
    
    public ArrayList<OrdenTrabajoDetalle> obtenerDetalleFromOrdenId(int idOrdenTrabajo){  
        
        ArrayList<OrdenTrabajoDetalle> a = new ArrayList<OrdenTrabajoDetalle>();
        OrdenTrabajoDetalle o;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_orden_tra_det_from_orden_pri(?)}");
            call.setInt(1, idOrdenTrabajo);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                o = new OrdenTrabajoDetalle();
                o.setIdOrdenTrabajoDetalle(res.getInt(1));
                o.setIdOrdenTrabajo(res.getInt(2));
                o.setIdCatalogoServicio(res.getInt(3)); 
                a.add(o);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return a;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();  
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return null;
    }
    
    public boolean eliminarOrdenTrabajoDetalleById(int id){        
            OrdenTrabajoDetalle o = null;
            boolean eliminado = false;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call eliminar_detalle_servicio(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                eliminado = true;
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return eliminado;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            try {
                cerrar();
            } catch (SQLException ex) {
                ex.printStackTrace();  
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return eliminado;
    }
    
}
