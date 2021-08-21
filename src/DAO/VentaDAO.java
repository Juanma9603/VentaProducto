package DAO;

import Entity.DetalleVenta;
import Entity.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VentaDAO {
    Conexion con=new Conexion();

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


                //System.out.println(objVenta.getID());

            }
            new DetalleVentaDAO().Registrar(objVenta);

        }catch (SQLException er){
            System.out.println("SQL Error: "+ er);
        }

    }
}
