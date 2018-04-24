/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.OrdenTrabajo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class OrdenTrabajoDao extends ConexionMySQL{
    
    public OrdenTrabajo obtenerOrdenTrabajoById(int id){
           OrdenTrabajo o = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_orden_trabajo_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                o = new OrdenTrabajo();
                o.setIdOrdenTrabajo(res.getInt(1));
                o.setFolio(res.getString(2));
                o.setIdCliente(res.getInt(3));
                o.setIdAuto(res.getInt(4));
                o.setFechaRegistro(res.getDate(5));
                o.setEstado(res.getString(6));
                o.setFechaCompletado(res.getDate(7));
                o.setIdUsuario(res.getInt(8));
                o.setIdEmpleadoAsignado(res.getInt(9));
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
    
    public boolean actualizarOrdenDeTrabajo(OrdenTrabajo o){
        try{
            int actualizado = 0;
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_orden_trabajo(?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, o.getIdOrdenTrabajo());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, o.getFolio());
            call.setInt(3, o.getIdCliente());
            call.setInt(4, o.getIdAuto());
//            call.setDate(5, (Date) o.getFechaRegistro());
            call.setDate(5, new java.sql.Date(o.getFechaRegistro().getTime()));
            call.setString(6, o.getEstado());
//            call.setDate(7, (Date) o.getFechaCompletado());

            if(o.getFechaCompletado() == null){
                call.setDate(7, null);
            } else {
                call.setDate(7, new java.sql.Date(o.getFechaCompletado().getTime()));
            }
            
            call.setInt(8, o.getIdUsuario());            
            call.setInt(9, o.getIdEmpleadoAsignado());

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
    
    public OrdenTrabajo registrarCliente(OrdenTrabajo o){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_orden_trabajo(?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, o.getIdOrdenTrabajo());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, o.getFolio());
            call.setInt(3, o.getIdCliente());
            call.setInt(4, o.getIdAuto());
            call.setDate(5, new java.sql.Date(o.getFechaRegistro().getTime()));
            call.setString(6, o.getEstado());
            
            if(o.getFechaCompletado() == null){
                call.setDate(7, null);
            } else {
                call.setDate(7, new java.sql.Date(o.getFechaCompletado().getTime()));
            }
            
            call.setInt(8, o.getIdUsuario());            
            call.setInt(9, o.getIdEmpleadoAsignado());

            ResultSet res = call.executeQuery();
            
            if(res.next()){               
                o.setIdOrdenTrabajo(res.getInt(3));
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
    
    public ArrayList<OrdenTrabajo> obtenerAllClientes(){
        ArrayList<OrdenTrabajo> listaCliente = new ArrayList<OrdenTrabajo>();
        OrdenTrabajo o = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_orden_trabajo_by_id(?)}");
            call.setInt(1, -2);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                o = new OrdenTrabajo();
                o = new OrdenTrabajo();
                o.setIdOrdenTrabajo(res.getInt(1));
                o.setFolio(res.getString(2));
                o.setIdCliente(res.getInt(3));
                o.setIdAuto(res.getInt(4));
                o.setFechaRegistro(res.getDate(5));
                o.setEstado(res.getString(6));
                o.setFechaCompletado(res.getDate(8));
                o.setIdUsuario(res.getInt(9));
                o.setIdEmpleadoAsignado(res.getInt(10));
                listaCliente.add(o);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return listaCliente;
            
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
    
}
