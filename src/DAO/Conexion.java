package DAO;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion implements Serializable {
    private static Conexion Instance=null;
    private Connection con=null;

    public static Conexion getInstance(){
        if (Instance==null){
            Instance=new Conexion();
        }
        return Instance;
    }

    private Conexion(){
        con=Conexion.Conectar();
    }

    public Connection getCon() {
        return con;
    }

    public static Connection Conectar(){
        Connection c=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://192.168.100.174:3306/ventadeproducto","newuser","123456789");
            System.out.println("Conectado");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        return c;
    }

}
