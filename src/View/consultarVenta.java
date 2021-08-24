package View;

import DAO.VentaDAO;
import Entity.Venta;

import java.util.ArrayList;
import java.util.Scanner;

public class consultarVenta {
    private Scanner scanner = new Scanner(System.in);
    boolean volver=true;
    Venta objventa;

    public void View(){
        try {
            System.out.println("Lista de Ventas");
            ArrayList<Venta> listVentas=VentaDAO.list();
            for (int i=0;i< listVentas.size();i++){
                System.out.println(listVentas.get(i).getID()+"   "+listVentas.get(i).getRegistro().toString());
            }
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
        System.out.println("Consulta de Venta");
        while (volver) {
            System.out.println("Indica el Id de la Venta");
            objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
            if (objventa.getID()==0){
                System.out.println("Indica un ID existente");
            }else {
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
                        break;
                    case 2:
                        volver = false;
                        break;
                    default:
                        System.out.println("Escoge entre los 2");
                        volver=true;
                        break;
                }
            }


        }
    }
}
