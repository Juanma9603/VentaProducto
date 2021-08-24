package DAO;

import Entity.DetalleVenta;
import Entity.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class VentaDAO {
    private static VentaDAO Instance=null;
    Conexion con=Conexion.getInstance();

    public static VentaDAO getInstance(){
        if (Instance==null){
            Instance=new VentaDAO();
        }
        return Instance;
    }




    public void Registrar(Venta objVenta){
        try {
            String sql = "CALL sp_ventaINSERT(?,?,?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setDouble(1,objVenta.getIgv());
            ps.setDouble(2,objVenta.getDescuento());
            ps.setDouble(3,objVenta.getSubtotal());
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                objVenta.setID(rs.getInt(1));
            }
            new DetalleVentaDAO().Registrar(objVenta);

        }catch (SQLException er){
            System.out.println("SQL Error regis: "+ er);
        }

    }

    public void Modificar(Venta objVenta){
        try {
            String sql="CALL sp_ventaUPDATE(?,?,?,?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,objVenta.getID());
            ps.setDouble(2,objVenta.getIgv());
            ps.setDouble(3,objVenta.getDescuento());
            ps.setDouble(4,objVenta.getSubtotal());
            ps.execute();
            new DetalleVentaDAO().Registrar(objVenta);
        }catch (SQLException er){
            System.out.println("SQL Error mod: "+er);
        }
    }

    public Venta Consultar(int idVenta){
        Venta objVenta = new Venta();
        try{
            String sql="CALL sp_consultarVenta(?)";
            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setInt(1,idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                objVenta.setID(rs.getInt(1));
                objVenta.setIgv(rs.getDouble(2));
                objVenta.setDescuento(rs.getDouble(3));
                objVenta.setSubtotal(rs.getDouble(4));
                objVenta.setRegistro(rs.getDate(5));
            }
            objVenta.setLstDetalleVenta(DetalleVentaDAO.getInstance().getDetalleVenta(idVenta));
        }catch (SQLException er){
            System.out.println("SQL Error consul:"+er);
        }
        return objVenta;
    }

    public void Cancelar(int idVenta){
        try {
            String sql = "CALL sp_ventaDELETE(?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,idVenta);
            ps.execute();
        }catch (SQLException er){
            System.out.println("SQL Error cancel: "+er);
        }
    }

    public static ArrayList<Venta> list() throws Exception{
        ArrayList<Venta> listVentas = new ArrayList<>();
        try {
            Statement stmt=getInstance().con.getCon().createStatement();
            ResultSet rs= stmt.executeQuery("CALL sp_listarVentas();");
            while (rs.next()){
                Venta objventatmp=new Venta(
                        rs.getInt(1),
                        0.0,
                        0.0,
                        0.0,
                        rs.getDate(2),
                        new ArrayList<>()
                );
                listVentas.add(objventatmp);
            }
        }catch (SQLException er){
            System.out.println("SQL Error listventas"+er);
        }
        return listVentas;
    }
}
