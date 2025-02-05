/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion{

    Connection con;

    String URL = "jdbc:mysql://localhost:3306/bd_ventas";
    String USERNAME = "root";
    String PASSWORD = "";

    public Connection Conexion() {
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion con la Base de datos exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }


}
    /*
       //SE UTILIZA UN CONSTRUCTOR PRIVADO PARA ASEGURAR LA IMPLEMENTACION DEL PATRON SINGLETON
    private conexion() {
        
        
    }
    
    //SE GUARDA EL ESTADO DE LA CONEXION A LA BD
    private static Connection conexion;
    
    //LA VARIABLE QUE SE INSTANCIA SOLO UNA VEZ (SINGLETON)
    private static conexion instancia;
    
    private static final String URL = "jdbc:mysql://localhost:3306/bd_ventas"; 
    private static final String USERNAME = "root"; 
     private static final String PASSWORD = "";

            
    public Connection conectar(){
        System.out.println("buena mani");
         try{
             
             Class.forName("com.mysql.cj.jdbc.Driver");
             conexion= DriverManager.getConnection(URL,USERNAME,PASSWORD);
             System.out.println("Conexion con la Base de datos exitosa");
             return conexion;
         }catch(ClassNotFoundException | SQLException e){
             System.out.println(e);
         }
        return conexion;
    }
    
    
    //PATRON DE DISEÑO SINGLETON
    
    public static conexion getInstance(){
        if(instancia == null){
            instancia = new conexion();
        }
        return instancia;
    
    }
}

*/