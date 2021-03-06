/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.Empleado;
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
public class EmpleadoDao extends ConexionMySQL{
    
      
    public Empleado obtenerEmpleadoById(int id){        
            Empleado c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_empleado_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                c = new Empleado();
                c.setIdEmpleado(res.getInt(1));
                c.setNombre(res.getString(2));
                c.setApePat(res.getString(3));
                c.setApeMat(res.getString(4));
                c.setHabilitado(res.getInt(5));
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
        
        
    public Empleado registrarEmpleado(Empleado e){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_empleado(?,?,?,?,?)}");
            call.setInt(1, e.getIdEmpleado());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, e.getNombre());
            call.setString(3, e.getApePat());
            call.setString(4, e.getApeMat());
            call.setInt(5, e.getHabilitado());

            ResultSet res = call.executeQuery();
            
            if(res.next()){               
                e.setIdEmpleado(res.getInt(3));
                String mensaje = res.getString("mensaje");
                System.out.println(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
            }
            
            cn.close();
            res.close();
            call.close();
            
            return e;
            
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
        return e;
    }
    
    public boolean actualizarEmpleado(Empleado e){
        try{
            int actualizado = 0;
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_empleado(?,?,?,?,?)}");
            call.setInt(1, e.getIdEmpleado());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, e.getNombre());
            call.setString(3, e.getApePat());
            call.setString(4, e.getApeMat());
            call.setInt(5, e.getHabilitado());

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
    
    public ArrayList<Empleado> obtenerAllEmpleados(){
        ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
        Empleado c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_empleado_by_id(?)}");
            call.setInt(1, -2);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                c = new Empleado();
                c.setIdEmpleado(res.getInt(1));
                c.setNombre(res.getString(2));
                c.setApePat(res.getString(3));
                c.setApeMat(res.getString(4));
                c.setHabilitado(res.getInt(5));
                listaEmpleado.add(c);
            }
            
            cn.close();
            res.close();
            call.close();
            
            return listaEmpleado;
            
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
