/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.dao;

import com.mysql.jdbc.CallableStatement;
import com.taller.bd.ConexionMySQL;
import com.taller.modelo.Cliente;
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
public class ClienteDao extends ConexionMySQL{
        
    public Cliente obtenerClienteById(int id){        
            Cliente c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_cliente_by_id(?)}");
            call.setInt(1, id);
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                c = new Cliente();
                c.setIdCliente(res.getInt(1));
                c.setNombreCompleto(res.getString(2));
                c.setCompania(res.getString(3));
                c.setRfc(res.getString(4));
                c.setCalle(res.getString(5));                
                c.setNumeroExterior(res.getString(6));
                c.setNumeroInterior(res.getString(7));
                c.setCodigoPostal(res.getInt(8));
                c.setEstado(res.getString(9));
                c.setTelefono(res.getString(10));                
                c.setEmail(res.getString(11));
                c.setActivo(res.getString(12));
                c.setCiudad(res.getString(13));
                c.setFax(res.getString(14));                
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
        
        
    public Cliente registrarCliente(Cliente c){
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, c.getIdCliente());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, c.getNombreCompleto());
            call.setString(3, c.getCompania());
            call.setString(4, c.getRfc());            
            call.setString(5, c.getCalle());
            call.setString(6, c.getNumeroExterior());
            call.setString(7, c.getNumeroInterior());
            call.setInt(8, c.getCodigoPostal());            
            call.setString(9, c.getEstado());
            call.setString(10, c.getTelefono());
            call.setString(11, c.getEmail());
            call.setString(12, c.getActivo());
            call.setString(13, c.getCiudad());
            call.setString(14, c.getFax());

            ResultSet res = call.executeQuery();
            
            if(res.next()){               
                c.setIdCliente(res.getInt(3));
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
    
    public boolean actualizarCliente(Cliente c){
        try{
            int actualizado = 0;
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, c.getIdCliente());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, c.getNombreCompleto());
            call.setString(3, c.getCompania());
            call.setString(4, c.getRfc());            
            call.setString(5, c.getCalle());
            call.setString(6, c.getNumeroExterior());
            call.setString(7, c.getNumeroInterior());
            call.setInt(8, c.getCodigoPostal());            
            call.setString(9, c.getEstado());
            call.setString(10, c.getTelefono());
            call.setString(11, c.getEmail());
            call.setString(12, c.getActivo());
            call.setString(13, c.getCiudad());
            call.setString(14, c.getFax());

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
    
    public ArrayList<Cliente> obtenerAllClientes(){
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        Cliente c = null;
        try{
            Connection cn = conectar();
            CallableStatement call = (CallableStatement) cn.prepareCall("{call obtener_cliente_by_id(?)}");
            call.setInt(1, -2);
            
            ResultSet res = call.executeQuery();
            
            while(res.next()){
                c = new Cliente();
                c.setIdCliente(res.getInt(1));
                c.setNombreCompleto(res.getString(2));
                c.setCompania(res.getString(3));
                c.setRfc(res.getString(4));
                c.setCalle(res.getString(5));                
                c.setNumeroExterior(res.getString(6));
                c.setNumeroInterior(res.getString(7));
                c.setCodigoPostal(res.getInt(8));
                c.setEstado(res.getString(9));
                c.setTelefono(res.getString(10));                
                c.setEmail(res.getString(11));
                c.setActivo(res.getString(12));
                c.setCiudad(res.getString(13));
                c.setFax(res.getString(14)); 
                listaCliente.add(c);
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
