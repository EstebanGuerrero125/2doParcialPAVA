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

/**
 *
 * @author Santiago Pineda
 */
public class VentaDAO {
    Connection con;
    conexion cn= new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public String GenerarSerie(){
        String numeroserie="";
        String sql="select max(NumeroSerie) from ventas";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                numeroserie=rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error en  la serie: " + e.getMessage());
        }
        return numeroserie;
    }
     public String IdVentas(){
        String idventas="";
        String sql="select max(IdVentas) from ventas";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idventas=rs.getString(1);
            }
        } catch (SQLException e) {
           System.out.println("Error al lista IdVentas: " + e.getMessage());
        }
        return idventas;
    }
     
    public int guardarVentas(Venta v) {
        String sql = "INSERT INTO ventas (IdCliente, IdEmpleado, NumeroSerie,FechaVentas,Monto, Estado) VALUES(?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdcliente());
            ps.setInt(2, v.getIdempleado());
            ps.setString(3, v.getNumserie());
            ps.setString(4, v.getFecha());
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar Venta: " + e.getMessage());
        }
        return r;
    }
    public int guardarDetalleventas(Venta v) {
        String sql = "INSERT INTO detalle_ventas (IdVentas, IdProducto, Cantidad,PrecioVenta) VALUES(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getId());
            ps.setInt(2, v.getIdproducto());
            ps.setInt(3, v.getCantidad());
            ps.setDouble(4, v.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar Detalle de Venta: " + e.getMessage());
        }
        return r;
    }
    
    
    
    
    
}
