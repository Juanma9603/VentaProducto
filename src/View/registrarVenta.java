package View;

import DAO.ProductoDAO;
import DAO.VentaDAO;
import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;


import java.math.BigDecimal;
import java.math.RoundingMode;
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
                if (objtmpproducto.getID()==0){
                    System.out.println("Indica uno Existente");
                    volver=true;
                }else {
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
                    BigDecimal montDecimal = new BigDecimal(SumaSubt);
                    montDecimal = montDecimal.setScale(2, RoundingMode.HALF_UP);

                    System.out.println("Monto: S/" + montDecimal);
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

                    BigDecimal descuentoDecimal = new BigDecimal(Descuento);
                    descuentoDecimal = descuentoDecimal.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal sumaDecimal = new BigDecimal(SumaSubt);
                    sumaDecimal = sumaDecimal.setScale(2, RoundingMode.HALF_UP);

                    Igv = (18 * SumaSubt) / 100;
                    BigDecimal Igvdecimal = new BigDecimal(Igv);
                    Igvdecimal = Igvdecimal.setScale(2, RoundingMode.HALF_UP);
                    System.out.println("Descuento: S/" + descuentoDecimal.doubleValue());
                    System.out.println("IGV: S/" + Igvdecimal);
                    double total = SumaSubt + Igv;
                    BigDecimal totalDecimal = new BigDecimal(total);
                    totalDecimal = totalDecimal.setScale(2, RoundingMode.HALF_UP);
                    System.out.println("Total con Dscto+IGV: S/" + totalDecimal.doubleValue());

                    Venta objVenta = new Venta(
                            0,
                            Igvdecimal.doubleValue(),
                            descuentoDecimal.doubleValue(),
                            sumaDecimal.doubleValue(),
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
                            boolean modificar = true;
                            while (modificar) {
                                System.out.println("lista");
                                for (int i = 0; i < listDetalle.size(); i++) {
                                    System.out.println(i + 1 + ".-" + listDetalle.get(i).toString());
                                }
                                System.out.println("Indica el item a modificar");
                                int item = scanner.nextInt();
                                System.out.println("Indica el ID del producto nuevo");
                                int nuevopro = scanner.nextInt();
                                if (nuevopro == 0) {
                                    listDetalle.remove(item - 1);

                                } else if (nuevopro > 0) {
                                    Producto objtproductomod = ProductoDAO.Consultar(nuevopro);
                                    if (objtproductomod.getID()==0){
                                        System.out.println("Indica un Producto existente");
                                    }else {
                                        System.out.println("Indica la Cantidad");
                                        int modcantidad = scanner.nextInt();
                                        listDetalle.get(item - 1).setObjproducto(objtproductomod);
                                        listDetalle.get(item - 1).setCantidad(modcantidad);
                                    }
                                }
                                SumaSubt = 0;
                                for (int i = 0; i < listDetalle.size(); i++) {
                                    SumaSubt = SumaSubt + (listDetalle.get(i).getObjproducto().getCosto() * listDetalle.get(i).getCantidad());
                                }
                                BigDecimal montmodDecimal = new BigDecimal(SumaSubt);
                                montmodDecimal = montmodDecimal.setScale(2, RoundingMode.HALF_UP);
                                System.out.println("Monto: S/" + montmodDecimal);

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

                                BigDecimal descuentomodDecimal = new BigDecimal(Descuento);
                                descuentomodDecimal = descuentomodDecimal.setScale(2, RoundingMode.HALF_UP);
                                BigDecimal sumamodDecimal = new BigDecimal(SumaSubt);
                                sumamodDecimal = sumamodDecimal.setScale(2, RoundingMode.HALF_UP);

                                Igv = (18 * SumaSubt) / 100;
                                BigDecimal Igvmoddecimal = new BigDecimal(Igv);
                                Igvmoddecimal = Igvmoddecimal.setScale(2, RoundingMode.HALF_UP);
                                System.out.println("Descuento: S/" + descuentoDecimal.doubleValue());
                                System.out.println("IGV: S/" + Igvmoddecimal);
                                total = SumaSubt + Igv;
                                BigDecimal totalmodDecimal = new BigDecimal(total);
                                totalmodDecimal = totalmodDecimal.setScale(2, RoundingMode.HALF_UP);
                                System.out.println("Total con Dscto+IGV: S/" + totalmodDecimal.doubleValue());

                                objVenta = new Venta(
                                        0,
                                        Igvmoddecimal.doubleValue(),
                                        descuentomodDecimal.doubleValue(),
                                        sumamodDecimal.doubleValue(),
                                        new Date(),
                                        listDetalle
                                );

                                System.out.println("1.Listo a pagar");
                                System.out.println("2.Seguir modificando");

                                switch (scanner.nextInt()) {
                                    case 1:

                                        System.out.println("Listo a Pagar");
                                        if (total > 0 && total <= 6000) {
                                            System.out.println("1.Efectivo");
                                            System.out.println("2.Tarjeta");

                                            switch (scanner.nextInt()) {
                                                case 1:
                                                    System.out.println("Pagado");
                                                    VentaDAO.Registrar(objVenta);
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                                case 2:
                                                    System.out.println("Pagado");
                                                    VentaDAO.Registrar(objVenta);
                                                    modificar = false;
                                                    volver = false;
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
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                                case 2:
                                                    System.out.println("Cancelado");
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                            }
                                            break;

                                        }

                                        break;
                                    case 2:
                                        System.out.println("seguir modificando");
                                        modificar = true;
                                        break;
                                }
                            }
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

    }

