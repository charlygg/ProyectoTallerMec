/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.CatalogoServicio;
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
public class CatalogoServicioDao extends ConexionMySQL{
    
    public CatalogoServicio obtenerServicioById(int id){        
            CatalogoServicio c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_servicio_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                c = new CatalogoServicio();
                c.setIdCatalogoServicio(res.getInt(1));
                c.setNombre(res.getString(2));
                c.setDetalle(res.getString(3));
                c.setActivo(res.getString(4));
                c.setPrecio(res.getDouble(5));
            }
            
            cn.close();
            res.close();
            call.close();
            
            return c;
            
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
        
        
    public CatalogoServicio registrarCatalogoServicio(CatalogoServicio c){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_servicios(?,?,?,?,?)}");
            call.setInt(1, c.getIdCatalogoServicio() );
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, c.getNombre());
            call.setString(3, c.getDetalle());
            call.setString(4, c.getActivo());
            call.setDouble(5, c.getPrecio());
            ResultSet res = call.executeQuery();
            
            if(res.next()){               
                c.setIdCatalogoServicio(res.getInt(3));
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
            }
            
            cn.close();
            res.close();
            call.close();
            
            return c;
            
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
        return c;
    }
    
    public boolean actualizarCatalogoServicio(CatalogoServicio c){
        try{
            int actualizado = 0;
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_servicios(?,?,?,?,?,?)}");
            call.setInt(1, c.getIdCatalogoServicio() );
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, c.getNombre());
            call.setString(3, c.getDetalle());
            call.setString(4, c.getActivo());
            call.setDouble(5, c.getPrecio());
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
    
    public ArrayList<CatalogoServicio> obtenerAllServicios(){
        ArrayList<CatalogoServicio> listaCatalogoServicio = new ArrayList<CatalogoServicio>();
        CatalogoServicio c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_servicio_by_id(?)}");
            call.setInt(1, -2);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                c = new CatalogoServicio();
                c.setIdCatalogoServicio(res.getInt(1));
                c.setNombre(res.getString(2));
                c.setDetalle(res.getString(3));
                c.setActivo(res.getString(4));
                c.setPrecio(res.getDouble(5)); 
                listaCatalogoServicio.add(c);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return listaCatalogoServicio;
            
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
