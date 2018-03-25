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
            }
            
            cn.close();
            res.close();
            call.close();
            
            return automovil;
            
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
        return null;
    }
    
}
