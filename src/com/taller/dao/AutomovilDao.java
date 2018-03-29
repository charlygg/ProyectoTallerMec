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
                automovil.setModelo(res.getInt("modelo"));
                automovil.setMarca(res.getString("marca"));
                automovil.setPlacas(res.getString("placas"));
                automovil.setColor(res.getString("color"));
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
    
    /* El método registra un nuevo auto si tiene id = 0 o lo actualiza si tiene un id existente de un auto */
    public boolean registrarOActualizarAutomovil(Automovil a){
        boolean actualizacion = false;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_auto(?,?,?,?,?)}");
            call.setInt(1, a.getIdAuto());
            call.setInt(2, a.getModelo());
            call.setString(3, a.getMarca());
            call.setString(4, a.getPlacas());
            call.setString(5, a.getColor());
            
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
