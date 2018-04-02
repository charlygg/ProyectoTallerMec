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
import java.sql.SQLType;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ClienteDao extends ConexionMySQL{
    
    private Cliente cliente;
    
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
                c.setNombre(res.getString(2));
                c.setApellidoPaterno(res.getString(3));
                c.setApellidoMaterno(res.getString(4));
                c.setEdad(res.getInt(5));
                c.setEmail(res.getString(6));
                c.setActivo(res.getString(7));
                c.setCalle(res.getString(8));
                c.setNumeroExterior(res.getString(9));
                c.setNumeroInterior(res.getString(10));
                c.setCodigoPostal(res.getInt(11));
                c.setEstado(res.getString(12));
                c.setTelefono(res.getString(13));
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
            CallableStatement call = (CallableStatement) cn.prepareCall("{call agregar_modificar_cliente(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, c.getIdCliente());
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, c.getNombre());
            call.setString(3, c.getApellidoPaterno());
            call.setString(4, c.getApellidoMaterno());
            call.setInt(5, c.getEdad());
            call.setString(6, c.getEmail());
            call.setString(7, c.getActivo());
            call.setString(8, c.getCalle());
            call.setString(9, c.getNumeroExterior());
            call.setString(10, c.getNumeroInterior());
            call.setInt(11, c.getCodigoPostal());
            call.setString(12, c.getEstado());
            call.setString(13, c.getTelefono());
            
            ResultSet res = call.executeQuery();
            
            if(res.next()){
                c.setIdCliente(res.getInt(1));
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
    
}
