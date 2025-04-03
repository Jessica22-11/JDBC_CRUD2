/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc_crud2;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author tatic
 */
public class CConexion {
    
    Connection conectar = null;
    
    String usuario ="root";
    String Contrasenia = "1234Sena";
    String bd = "dbescuelas";
    String ip ="localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar = DriverManager.getConnection(cadena,usuario,Contrasenia);
            
            JOptionPane.showMessageDialog(null, "La conexion se relaizo con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al conectrase con la base de datos" + e.toString());
        }
    return conectar;
    }
    
}
