package View;

import DAO.ProductoDAO;
import DAO.VentaDAO;
import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class registrarVenta {
    private Scanner scanner = new Scanner(System.in);
    private VentaDAO VentaDAO;
    private ProductoDAO ProductoDAO;


    public void View(){

        double Subtotal=0;
        double Igv=0;
        double Descuento = 0;
        double SumaSubt = 0;


            ArrayList<DetalleVenta> listDetalle = new ArrayList<>();
            ProductoDAO = DAO.ProductoDAO.getInstance();
            VentaDAO = DAO.VentaDAO.getInstance();
            System.out.println("Venta");
            boolean volver = true;
            while (volver) {
                System.out.println("Lista de Productos");
                try {
                    ArrayList<Producto> listproductos = ProductoDAO.list();
                    for (int i = 0; i < listproductos.size(); i++) {
                        System.out.println(listproductos.get(i).toString());
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
                System.out.println("Registrar Productos");
                System.out.println("Indica el ID");
                Producto objtmpproducto = ProductoDAO.Consultar(scanner.nextInt());
                System.out.println("Indica la Cantidad");
                int cantidad = scanner.nextInt();
                DetalleVenta objDetalleVentaTmp = new DetalleVenta(
                        0,
                        objtmpproducto,
                        cantidad
                );
                listDetalle.add(objDetalleVentaTmp);

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

                System.out.println("1.Seguir registrando");
                System.out.println("2.Modificar");
                System.out.println("3.Listo a pagar");
                switch (scanner.nextInt()) {
                    case 1:
                        volver = true;
                        break;
                    case 2:
                        new modificarVentaRegistrada().View();
                        break;
                    case 3:
                        volver = false;
                        System.out.println("Listo a Pagar");
                        if (total > 0 && total <= 6000) {
                            System.out.println("1.Efectivo");
                            System.out.println("2.Tarjeta");

                            switch (scanner.nextInt()) {
                                case 1:
                                    System.out.println("Pagado");
                                    VentaDAO.Registrar(objVenta);
                                    break;
                                case 2:
                                    System.out.println("Pagado");
                                    VentaDAO.Registrar(objVenta);
                                    break;
                                default:
                                    System.out.println("Escoge 1");
                                    break;
                            }
                        } else if (total > 6001) {
                            System.out.println("El monto es muy alto, solo puede pagar con tajeta");
                            System.out.println("1.Tarjeta");
                            System.out.println("2.Cancelar venta");
                            int pagar1 = scanner.nextInt();
                            switch (pagar1) {
                                case 1:
                                    System.out.println("Pagado");
                                    VentaDAO.Registrar(objVenta);
                                    break;
                                case 2:
                                    System.out.println("Cancelado");
                                    break;
                            }
                            break;
                        }
                        break;
                }

            }
        }

    }

