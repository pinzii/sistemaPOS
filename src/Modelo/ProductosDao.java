
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ProductosDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProducto(Productos pro){
        String sql = "INSERT INTO productos(codigo, nombre, proveedor, cantidad, precio) VALUES (?,?,?,?,?)";
         try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getCantidad());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
             System.out.println(e.toString());
             return false;
        }finally{
             try {
                 con.close();
             } catch (SQLException e) {
                 System.out.println(e.toString());
             }
         }
    }
    
    public List ListarProducto(){
        List<Productos> Listaprod = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos prod = new Productos();
                prod.setId(rs.getInt("id"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setPrecio(rs.getDouble("precio"));
                Listaprod.add(prod);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listaprod;
    }
    
    public void ConsultarProv(JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public boolean EliminarProductos(int id){
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarProductos(Productos prod){
        String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, cantidad=?, precio=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getProveedor());
            ps.setInt(4, prod.getCantidad());
            ps.setDouble(5, prod.getPrecio());
            ps.setInt(6, prod.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public Productos BuscarProd(String cod){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if(rs.next()){
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    } 
    
    public Config BuscarDatos(){
        Config config = new Config();
        String sql = "SELECT * FROM config";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);            
            rs = ps.executeQuery();
            if(rs.next()){
                config.setId(rs.getInt("id"));
                config.setCedula(rs.getString("cedula"));
                config.setNombre(rs.getString("nombre"));
                config.setTelefono(rs.getString("telefono"));
                config.setDireccion(rs.getString("direccion"));
                config.setRazon(rs.getString("razon"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return config;
    } 
    
    public boolean ModificarDatos(Config config){
        String sql = "UPDATE config SET cedula=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, config.getCedula());
            ps.setString(2, config.getNombre());
            ps.setString(3, config.getTelefono());
            ps.setString(4, config.getDireccion());
            ps.setString(5, config.getRazon());
            ps.setInt(6, config.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
