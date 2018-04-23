/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.Automovil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class AutomovilDao extends ConexionMySQL{
    
    private Automovil automovil;
    
    public Automovil obtenerAutomovilById(int id){        
        
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_auto_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                automovil = new Automovil();
                automovil.setIdAuto(res.getInt("id_auto"));
                automovil.setModelo(res.getString("modelo"));
                automovil.setMarca(res.getString("marca"));
                automovil.setPlacas(res.getString("placas"));
                automovil.setColor(res.getString("color"));
                automovil.setLinea(res.getString("linea"));
                automovil.setAnio(res.getInt("anio"));
                automovil.setKilometraje(res.getInt("kilometraje"));
                automovil.setSerie(res.getString("serie"));
                automovil.setIdCliente(res.getInt("id_cliente"));                
            }
            
            cn.close();
            res.close();
            call.close();
            
            return automovil;
            
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
    /* Metodo que regresa todos los automoviles registrados de la BD*/
    
    public ArrayList<Automovil> obtenerAutomovovilesFromClienteId(int id_cliente){  
        
        ArrayList<Automovil> a = new ArrayList<Automovil>();
        
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_autos_from_cliente(?)}");
            call.setInt(1, id_cliente);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                automovil = new Automovil();
                automovil.setIdAuto(res.getInt("id_auto"));
                automovil.setModelo(res.getString("modelo"));
                automovil.setMarca(res.getString("marca"));
                automovil.setPlacas(res.getString("placas"));
                automovil.setColor(res.getString("color"));
                automovil.setLinea(res.getString("linea"));
                automovil.setAnio(res.getInt("anio"));
                automovil.setKilometraje(res.getInt("kilometraje"));
                automovil.setSerie(res.getString("serie"));
                automovil.setIdCliente(res.getInt("id_cliente"));   
                a.add(automovil);
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
    
    public ArrayList<Automovil> obtenerAllAutomoviles(){  
        
        ArrayList<Automovil> a = new ArrayList<Automovil>();
        
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_autos_from_cliente(?)}");
            call.setInt(1, -2); //Traer todos los automóviles
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                automovil = new Automovil();
                automovil.setIdAuto(res.getInt("id_auto"));
                automovil.setModelo(res.getString("modelo"));
                automovil.setMarca(res.getString("marca"));
                automovil.setPlacas(res.getString("placas"));
                automovil.setColor(res.getString("color"));
                automovil.setLinea(res.getString("linea"));
                automovil.setAnio(res.getInt("anio"));
                automovil.setKilometraje(res.getInt("kilometraje"));
                automovil.setSerie(res.getString("serie"));
                automovil.setIdCliente(res.getInt("id_cliente"));   
                a.add(automovil);
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
    
    /* El método registra un nuevo auto si tiene id = 0 o lo actualiza si tiene un id existente de un auto */
    public Automovil registrarAutomovil(Automovil a){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_auto(?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, a.getIdAuto());
            call.setString(2, a.getModelo());
            call.setString(3, a.getMarca());
            call.setString(4, a.getLinea());
            call.setInt(5, a.getAnio());
            call.setString(6, a.getSerie());
            call.setString(7, a.getColor());
            call.setString(8, a.getPlacas());
            call.setInt(9, a.getKilometraje());
            call.setInt(10, a.getIdCliente());            
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                a.setIdAuto(res.getInt(3));
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
            }
            
            cn.close();
            res.close();
            call.close();
            
            return a;
            
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
        return a;
    }
    
    
    /* El método registra un nuevo auto si tiene id = 0 o lo actualiza si tiene un id existente de un auto */
    public boolean actualizarAutomovil(Automovil a){
        boolean actualizacion = false;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_auto(?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, a.getIdAuto());
            call.setString(2, a.getModelo());
            call.setString(3, a.getMarca());
            call.setString(4, a.getLinea());
            call.setInt(5, a.getAnio());
            call.setString(6, a.getSerie());
            call.setString(7, a.getColor());
            call.setString(8, a.getPlacas());
            call.setInt(9, a.getKilometraje());
            call.setInt(10, a.getIdCliente());            
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                actualizacion = true;
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                actualizacion = false;
            }
            
            cn.close();
            res.close();
            call.close();
            
            return actualizacion;
            
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
        return actualizacion;
    }
    
}
