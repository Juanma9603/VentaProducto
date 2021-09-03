package View;

import DAO.VentaDAO;
import Entity.Venta;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Scanner;

public class consultarVenta {
    private Scanner scanner = new Scanner(System.in);
    boolean volver=true;
    Venta objventa;
    static Logger logger=Logger.getLogger(consultarVenta.class);

    public void View(){
        try {
            PropertyConfigurator.configure("src/log.properties");
            System.out.println("Lista de Ventas");
            ArrayList<Venta> listVentas=VentaDAO.list();
            for (int i=0;i< listVentas.size();i++){
                System.out.println(listVentas.get(i).getID()+"   "+listVentas.get(i).getRegistro().toString());
            }
            if (listVentas!=new ArrayList<Venta>()){
                logger.info("Presentada la lista de ventas");
            }
        }catch (Exception e){
            if (e!=null){
                logger.warn("Error al listar");
            }
            System.out.println("Error: "+e);
        }
        System.out.println("Consulta de Venta");
        while (volver) {
            System.out.println("Indica el Id de la Venta");
            objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
            if (objventa!=new Venta()){
                logger.info("Realizando la consulta");
            }
            if (objventa.getID()==0){
                logger.warn("Se intenta vulnerar el programa");
                System.out.println("Indica un ID existente");
            }else {
                logger.info("Presentando datos hallados");
                System.out.println("ID de la Venta: "+objventa.getID()+'\''+
                        "  Igv: "+objventa.getIgv()+'\''+
                        "  Descuento: "+objventa.getDescuento()+'\''+
                        "  Monto: "+objventa.getSubtotal()+'\''+
                        "  Total: "+(objventa.getSubtotal()+ objventa.getIgv()-objventa.getDescuento())+'\''+
                        "  Registro: "+objventa.getRegistro());
                for (int i=0;i<objventa.getLstDetalleVenta().size();i++)
                    System.out.println("Nombre: "+objventa.getLstDetalleVenta().get(i).getObjproducto().getNombre()+'\''+
                            " Costo: "+objventa.getLstDetalleVenta().get(i).getObjproducto().getCosto()+'\''+
                            " Registro: "+objventa.getLstDetalleVenta().get(i).getObjproducto().getRegistro()+'\''+
                            " Cantidad: "+objventa.getLstDetalleVenta().get(i).getCantidad());
                System.out.println("1.Consultar otra venta");
                System.out.println("2.Volver al menu");
                switch (scanner.nextInt()) {
                    case 1:
                        volver = true;
                        if (volver==true){
                            logger.info("Consultando otra venta");
                        }
                        break;
                    case 2:
                        volver = false;
                        if (volver==false){
                            logger.info("Regresando al menu");
                        }
                        break;
                    default:
                        System.out.println("Escoge entre los 2");
                        volver=true;
                        if (volver==true){
                            logger.warn("Se intento vulnerar el programa");
                        }
                        break;
                }
            }


        }
    }
}
