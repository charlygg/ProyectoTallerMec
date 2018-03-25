/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taller.bd;

import java.sql.*;
/**
 *
 * @author Usuario
 */
public class ConexionMySQL {
    
    public Connection cnx = null;

   public Connection conectar() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/proyectotallermec", "root", "");
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return cnx;
   }    
    
   public void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   } 
}
