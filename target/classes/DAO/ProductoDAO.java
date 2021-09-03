package DAO;

import Entity.Producto;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoDAO {
    private static ProductoDAO Instance=null;
    static Conexion con=Conexion.getInstance();
    static Logger logger=Logger.getLogger(ProductoDAO.class);

    public static ProductoDAO getInstance(){
        PropertyConfigurator.configure("src/log.properties");
        if (Instance==null){
            Instance=new ProductoDAO();
        }
        return Instance;
    }


    public static void Aniadir(Producto objproducto){
        try {
            String sql= "CALL sp_productoINSERT(?,?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setString(1,objproducto.getNombre());
            ps.setDouble(2,objproducto.getCosto());
            if (ps.execute()){
                logger.info("Correcto call sp_productoINSERT");
            }
        }catch (SQLException er){
            PropertyConfigurator.configure("src/log.properties");
                logger.error("Error call sp_productoINSERT "+er);

            /*System.out.println("SQL Error: "+ er);*/
        }
    }

    public static void Modificar(Producto objproducto){
        try {
            String sql="CALL sp_productoUPDATE(?,?,?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,objproducto.getID());
            ps.setString(2, objproducto.getNombre());
            ps.setDouble(3,objproducto.getCosto());
            if (ps.execute()){
                logger.info("Correcto call sp_productoUPDATE");
            }
        }catch (SQLException er){
            PropertyConfigurator.configure("src/log.properties");
                logger.error("Error call sp_productoUPDATE "+er);

            /*System.out.println("SQL Error: "+er);*/
        }
    }

    public static Producto Consultar(int ID){
        Producto objproducto=new Producto();

        try {
            String sql = "CALL sp_consultarProducto(?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,ID);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                objproducto=new Producto(
                        rs.getInt("Id_Producto"),
                        rs.getString("Nombre"),
                        rs.getDouble("Costo"),
                        rs.getDate("Registro")
                );
            }
            if (rs!=null){
                logger.info("Correcto call sp_consultarProducto");
            }
        }catch (SQLException er){
            PropertyConfigurator.configure("src/log.properties");
                logger.error("Error call sp_consultarProducto "+er);

            /*System.out.println("SQL Error: "+er);*/
        }
            return objproducto;
    }


    public static void Eliminar(int ID){
        try {
            String sql="CALL sp_productoDELETE (?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,ID);
            if (ps.execute()){
                logger.info("Correcto call sp_productoDELTE");
            }
        }catch (SQLException er){
            PropertyConfigurator.configure("src/log.properties");
                logger.error("Error call sp_productoDELETE "+er);

            /*System.out.println("SQL Error: "+er);*/
        }

    }

    public static ArrayList<Producto>list() throws Exception{
        ArrayList<Producto> listproductos=new ArrayList<>();

        try {
            Statement stm=con.getCon().createStatement();
            ResultSet rs=stm.executeQuery("CALL sp_listarProductos();");
            while (rs.next()){
                Producto objtmpproducto=new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                );
                listproductos.add(objtmpproducto);
            }
            if (rs!=null){
                logger.info("Correcto call sp_listarProductos");
            }
        }catch (SQLException er){
            PropertyConfigurator.configure("src/log.properties");
                logger.error("Error call sp_listarProductos "+er);

            /*System.out.println("SQL Error: "+er);*/
        }
        return listproductos;
    }
}
