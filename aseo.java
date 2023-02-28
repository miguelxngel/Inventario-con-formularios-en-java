/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.CallableStatement;
import javax.swing.JTextField;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class aseo {
    //variables o campos de mi base de datos
    int id;
    String nombreProducto;
    int cantidad;

    public aseo() {
    }

    public aseo(int id, String nombreProducto, int cantidad) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void insertarProductoAseo(JTextField parametroId, JTextField parametroNombre, JTextField parametroCantidad){
        setId(Integer.parseInt(parametroId.getText()));
        setNombreProducto(parametroNombre.getText());
        setCantidad(Integer.parseInt(parametroCantidad.getText()));
        
        Conexion c = new Conexion();
        String consulta = "INSERT INTO aseo (id, nombreProducto, cantidad) VALUES (?,?,?);";
        
        try {
            CallableStatement cs = c.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el producto...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar el producto, error: "+e.getMessage());
        }
    }
}
