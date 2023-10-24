/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
        
/**
 *
 * @author Santiago Pineda
 */
public class ProductoDAO {
    conexion cn= new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public List listar(){
        String sql="select * from producto";
        List<Producto> lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto em=new Producto();
                em.setId(rs.getInt(1));
                em.setNom(rs.getString(2));
                em.setPrecio(rs.getDouble(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em); 
            }
        } catch (Exception e) {
             System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Producto em) {
        String sql = "INSERT INTO producto (Nombres, Precio, Stock, Estado) VALUES(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setDouble(2, em.getPrecio());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        return r;
    }
    
    public Producto listarId(int id) {
        Producto pr= new Producto();
        String sql = "select * from producto where IdProducto="+id;
       try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
             System.out.println("Error al listar: " + e.getMessage());
        }
        return pr;
    }
    
    public int actualizar(Producto em) {
        String sql = "UPDATE producto set Nombres=?, Precio=?, Stock=?, Estado=? WHERE IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setDouble(2, em.getPrecio());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        return r;
    }
    
     public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
    
    
}
