package DAO;

import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DetalleVentaDAO {
    private static DetalleVentaDAO Instance=null;
    Conexion con=Conexion.getInstance();

    public static DetalleVentaDAO getInstance(){
        if (Instance==null){
            Instance=new DetalleVentaDAO();
        }
        return Instance;
    }

    public ArrayList<DetalleVenta> getDetalleVenta(int idVenta){
        ArrayList<DetalleVenta> listDetalle =new ArrayList<>();
        try {
            String sql = "CALL sp_consultarVentaProducto(?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            ps.setInt(1,idVenta);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                    listDetalle.add(
                            new DetalleVenta(
                                    0,
                                    new Producto(0,
                                            rs.getString(1),
                                            rs.getDouble(2),
                                    new Date()),
                                    rs.getInt(3)
                            )
                    );
            }
        }catch (SQLException er){
            System.out.println("SQL Errorlist: "+er);
        }
        return listDetalle;
    }




    public void Registrar(Venta objventa){
        try {
            String sql = "CALL sp_detalleventaINSERT(?,?,?)";
            PreparedStatement ps=con.getCon().prepareStatement(sql);
            for (int i=0;i< objventa.getLstDetalleVenta().size();i++){
                ps.setInt(1,objventa.getID());
                ps.setInt(2,objventa.getLstDetalleVenta().get(i).getObjproducto().getID());
                ps.setInt(3,objventa.getLstDetalleVenta().get(i).getCantidad());
                ps.execute();
            }


        }catch (SQLException er){
            System.out.println("SQL Errordetalle: " +er);
        }
    }

    public void Eliminar(){

    }
}
