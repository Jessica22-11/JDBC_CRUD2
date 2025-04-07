/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc_crud2;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql="select * from alumnos;";
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        
        String[] datos =new String[3];
        Statement st;
        
        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs =st.executeQuery(sql);
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los alumnos, Error: " +e.toString());
        }
        
    }
    
    public void SeleccionarAlumnos(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombre, JTextField paramApellido){
        
        try {
            
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila>=0) {
                
                paramId.setText((String)(paramTablaAlumnos.getValueAt(fila, 0)));
                paramNombre.setText((String)(paramTablaAlumnos.getValueAt(fila, 1)));
                paramApellido.setText((String) (paramTablaAlumnos.getValueAt(fila, 2))); 
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " +e.toString());
        }
        
    }
    
    public  void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "update alumnos set alumnos.nombre =?, alumnos.apellido =? where alumnos.id = ?;";
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar Alumno error: " +e.toString());
        }
    }
    
    public void EliminarAlumnos(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "delete from alumnos where alumnos.id =?;";
        
        try {
            
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamante el Alumno");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar , Error: " +e.toString());
        }
        
    }
}
