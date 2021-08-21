package DAO;

import Entity.DetalleVenta;
import Entity.Venta;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetalleVentaDAO {
    Conexion con=new Conexion();

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
}
