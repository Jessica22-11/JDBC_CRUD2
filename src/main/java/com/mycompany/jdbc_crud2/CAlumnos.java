/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc_crud2;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author tatic
 */
public class CAlumnos {
    int codigo;
    String Nombre;
    String Apellido;  

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
  
    public void InsertarAlumnos(JTextField paramNombres, JTextField paramApellidos){
        setNombre(paramNombres.getText());
        setApellido(paramApellidos.getText());
        
        CConexion objetoconexion = new CConexion();
        String consulta = "insert into alumnos (nombre, apellido) values (?, ?);";
        
        try {
            
            CallableStatement cs = objetoconexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            
        cs.execute();
        
            JOptionPane.showMessageDialog(null, "Se inserto el alumno correctamante");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamante el alumno, error: " +e.toString());
        }
    }
}
