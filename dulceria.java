/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
 * @author migue
 */
public class dulceria {
    //variables o campos de mi base de datos
    int id;
    String nombreProducto;
    int cantidad;

    public dulceria() {
    }

    public dulceria(int id, String nombreProducto, int cantidad) {
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
    
    public void insertarProductoDulceria(JTextField parametroId, JTextField parametroNombre, JTextField parametroCantidad){
        setId(Integer.parseInt(parametroId.getText()));
        setNombreProducto(parametroNombre.getText());
        setCantidad(Integer.parseInt(parametroCantidad.getText()));
        
        Conexion c = new Conexion();
        String consulta = "INSERT INTO dulceria (id, nombreProducto, cantidad) VALUES (?,?,?);";
        
        try {
            //enlazar conexion con la consulta(insert)
            CallableStatement cs = c.establecerConexion().prepareCall(consulta);
            //asignacion de nuevos datos
            cs.setInt(1, getId());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            //ejecucion
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el producto...");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar, intente nuevamente..."+"\nError:"+e.getMessage());
        }
    }
    
    public void mostrarProductos(JTable parametroMostrarTabla){
        Conexion c = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        parametroMostrarTabla.setRowSorter(ordenarTabla);
        
        String sql = "select * from dulceria;";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Cantidad");
        
        parametroMostrarTabla.setModel(modelo);
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            st = c.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            parametroMostrarTabla.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros, error:"
            + e.getMessage());
        }
        
        
    }
    
}
