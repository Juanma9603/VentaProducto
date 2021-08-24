package View;

import DAO.ProductoDAO;
import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class modificarVentaRegistrada {
    private Scanner scanner = new Scanner(System.in);
    double Subtotal=0;
    double Igv=0;
    double Descuento = 0;
    double SumaSubt = 0;


    public void View() {
        System.out.println("Modificar");

        boolean modificar=true;
        while (modificar) {


            System.out.println("lista");
            for (int i = 0; i < listDetalle.size(); i++) {
                System.out.println(i + 1 + ".-" + listDetalle.get(i).toString());
            }
            System.out.println("Indica el item a modificar");
            int item = scanner.nextInt();
            System.out.println("Indica el ID del producto nuevo");
            Producto objtproductomod = ProductoDAO.Consultar(scanner.nextInt());
            System.out.println("Indica la Cantidad");
            int modcantidad = scanner.nextInt();
            listDetalle.get(item - 1).setObjproducto(objtproductomod);
            listDetalle.get(item - 1).setCantidad(modcantidad);

            for (int i = 0; i < listDetalle.size(); i++) {
                SumaSubt = SumaSubt + (listDetalle.get(i).getObjproducto().getCosto() * listDetalle.get(i).getCantidad());
            }
            System.out.println("Monto: S/" + SumaSubt);

            if (SumaSubt < 50) {
                System.out.println("No  hay descuento");
            } else if (SumaSubt > 51 && SumaSubt <= 100) {
                System.out.println("Descuento de 5%");
                Descuento = 5 * SumaSubt / 100;
                SumaSubt = SumaSubt - Descuento;
            } else {
                System.out.println("Descuento de 10%");
                Descuento = 10 * SumaSubt / 100;
                SumaSubt = SumaSubt - Descuento;
            }
            Igv = (18 * SumaSubt) / 100;
            System.out.println("IGV: S/" + Igv);
            double total = SumaSubt + Igv;
            System.out.println("Total con Dscto+IGV: S/" + total);

            Venta objVenta = new Venta(
                    0,
                    Igv,
                    Descuento,
                    SumaSubt,
                    new Date(),
                    listDetalle
            );
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Listo a Pagar");
                    modificar = false;
                    break;
                case 2:
                    System.out.println("seguir modificando");
                    modificar = true;
                    break;
            }
        }
    }
}
