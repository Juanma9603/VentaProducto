package View;

import DAO.ProductoDAO;
import DAO.VentaDAO;
import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class registrarVenta {
    private Scanner scanner = new Scanner(System.in);
    private VentaDAO VentaDAO;
    private ProductoDAO ProductoDAO;
    static Logger logger=Logger.getLogger(registrarVenta.class);


    public void View(){
        PropertyConfigurator.configure("src/log.properties");
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
                    if (listproductos!=new ArrayList<Producto>()){
                        logger.info("Listando productos");
                    }
                } catch (Exception e) {
                    if (e!=null){
                        logger.warn("Error al listar");
                    }
                    System.out.println("ERROR: " + e);
                }
                System.out.println("Registrar Productos");
                System.out.println("Indica el ID");
                Producto objtmpproducto = ProductoDAO.Consultar(scanner.nextInt());
                if (objtmpproducto!=new Producto()){
                    logger.info("Realizando consulta");
                }
                if (objtmpproducto.getID()==0){
                    logger.warn("Se intento vulnerar el programa");
                    System.out.println("Indica uno Existente");
                    volver=true;
                }else {
                    logger.info("Recolectando datos");
                    System.out.println("Indica la Cantidad");
                    int cantidad = scanner.nextInt();
                    DetalleVenta objDetalleVentaTmp = new DetalleVenta(
                            0,
                            objtmpproducto,
                            cantidad
                    );
                    listDetalle.add(objDetalleVentaTmp);
                    if (objDetalleVentaTmp!=new DetalleVenta()){
                        logger.info("Datos recolectados");
                    }

                    for (int i = 0; i < listDetalle.size(); i++) {
                        SumaSubt = SumaSubt + (listDetalle.get(i).getObjproducto().getCosto() * listDetalle.get(i).getCantidad());
                    }
                    if (SumaSubt!=0){
                        logger.info("Calculo realizado");
                    }
                    BigDecimal montDecimal = new BigDecimal(SumaSubt);
                    montDecimal = montDecimal.setScale(2, RoundingMode.HALF_UP);

                    System.out.println("Monto: S/" + montDecimal);
                    if (SumaSubt < 50) {
                        logger.info("El monto no aplica descuento");
                        System.out.println("No  hay descuento");
                    } else if (SumaSubt > 51 && SumaSubt <= 100) {
                        logger.info("El monto aplica a un descuento de 5%");
                        System.out.println("Descuento de 5%");
                        Descuento = 5 * SumaSubt / 100;
                        SumaSubt = SumaSubt - Descuento;

                    } else {
                        logger.info("El monto aplica a un descuento de 10%");
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
                    if (objVenta!=new Venta()){
                        logger.info("Venta recopilada");
                    }

                    System.out.println("1.Seguir registrando");
                    System.out.println("2.Modificar");
                    System.out.println("3.Listo a pagar");
                    switch (scanner.nextInt()) {
                        case 1:
                            volver = true;
                            if (volver==true){
                                logger.info("Sigue registro");
                            }
                            break;
                        case 2:
                            boolean modificar = true;
                            int contador=0;
                            while (modificar) {
                                System.out.println("lista");
                                for (int i = 0; i < listDetalle.size(); i++) {
                                    System.out.println(i + 1 + ".-" + listDetalle.get(i).toString());
                                    contador=contador+1;
                                }
                                if (listDetalle!=new ArrayList<DetalleVenta>()){
                                    logger.info("Indicando lista de venta");
                                }
                                System.out.println("Indica el item a modificar");
                                int item = scanner.nextInt();
                                if (item>0 & item<=contador){
                                    if (item>=0){
                                        logger.info("Recopilando datos");
                                    }

                                    System.out.println("Indica el ID del producto nuevo");
                                    int nuevopro = scanner.nextInt();
                                    if (nuevopro == 0) {
                                        logger.info("Removiendo producto");
                                        listDetalle.remove(item-1);

                                    } else if (nuevopro > 0) {
                                        Producto objtproductomod = ProductoDAO.Consultar(nuevopro);
                                        if (objtproductomod!=new Producto()){
                                            logger.info("Se realizo consulta");
                                        }
                                        if (objtproductomod.getID()==0){
                                            logger.warn("Se intento vulnerar el programa");
                                            System.out.println("Indica un Producto existente");
                                        }else {
                                            System.out.println("Indica la Cantidad");
                                            int modcantidad = scanner.nextInt();
                                            listDetalle.get(item - 1).setObjproducto(objtproductomod);
                                            listDetalle.get(item - 1).setCantidad(modcantidad);
                                            logger.info("Recopilado de datos para modificar");
                                        }
                                    }
                                } else {
                                    System.out.println("Indica un numero dentro de la lista");
                                    modificar=true;
                                    PropertyConfigurator.configure("src/log.properties");
                                    logger.warn("Se intento vulnerar el programa");
                                }


                                SumaSubt = 0;
                                for (int i = 0; i < listDetalle.size(); i++) {
                                    SumaSubt = SumaSubt + (listDetalle.get(i).getObjproducto().getCosto() * listDetalle.get(i).getCantidad());
                                }
                                if (SumaSubt!=0){
                                    logger.info("Calculo realizado");
                                }
                                BigDecimal montmodDecimal = new BigDecimal(SumaSubt);
                                montmodDecimal = montmodDecimal.setScale(2, RoundingMode.HALF_UP);
                                System.out.println("Monto: S/" + montmodDecimal);

                                if (SumaSubt < 50) {
                                    logger.info("El monto no aplica descuento");
                                    System.out.println("No  hay descuento");
                                } else if (SumaSubt > 51 && SumaSubt <= 100) {
                                    logger.info("El monto aplica a un descuento de 5%");
                                    System.out.println("Descuento de 5%");
                                    Descuento = 5 * SumaSubt / 100;
                                    SumaSubt = SumaSubt - Descuento;
                                } else {
                                    logger.info("El monto aplica a un descuento de 10%");
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
                                if (objVenta!=new Venta()){
                                    logger.info("Venta recopilada");
                                }

                                System.out.println("1.Listo a pagar");
                                System.out.println("2.Seguir modificando");

                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Listo a Pagar");
                                        if (total > 0 && total <= 6000) {
                                            logger.info("Accediendo a pago 2 opciones");
                                            System.out.println("1.Efectivo");
                                            System.out.println("2.Tarjeta");

                                            switch (scanner.nextInt()) {
                                                case 1:
                                                    System.out.println("Pagado");
                                                    VentaDAO.Registrar(objVenta);
                                                    if (objVenta!=new Venta()){
                                                        logger.info("Pago realizado");
                                                    }
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                                case 2:
                                                    System.out.println("Pagado");
                                                    VentaDAO.Registrar(objVenta);
                                                    if (objVenta!=new Venta()){
                                                        logger.info("Pago realizado");
                                                    }
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                                default:
                                                    System.out.println("Escoge 1");
                                                    logger.warn("Se intenta vulnerar el programa");
                                                    break;
                                            }
                                        } else if (total > 6001) {
                                            logger.info("Accediendo a pago 1 opcion");
                                            System.out.println("El monto es muy alto, solo puede pagar con tajeta");
                                            System.out.println("1.Tarjeta");
                                            System.out.println("2.Cancelar venta");
                                            int pagar1 = scanner.nextInt();
                                            switch (pagar1) {
                                                case 1:
                                                    System.out.println("Pagado");
                                                    VentaDAO.Registrar(objVenta);
                                                    if (objVenta!=new Venta()){
                                                        logger.info("Pago realizado");
                                                    }
                                                    modificar = false;
                                                    volver = false;
                                                    break;
                                                case 2:
                                                    System.out.println("Cancelado");
                                                    modificar = false;
                                                    volver = false;
                                                    if (modificar==false & volver==false){
                                                        logger.info("Cancelo venta");
                                                    }
                                                    break;
                                            }
                                            break;

                                        }

                                        break;
                                    case 2:
                                        System.out.println("seguir modificando");
                                        modificar = true;
                                        if (modificar==true){
                                            logger.info("Sigue modificando");
                                        }
                                        break;
                                }
                            }
                            break;
                        case 3:
                            volver = false;
                            System.out.println("Listo a Pagar");
                            if (total > 0 && total <= 6000) {
                                logger.info("Accediendo a pago 2 opciones");
                                System.out.println("1.Efectivo");
                                System.out.println("2.Tarjeta");

                                switch (scanner.nextInt()) {
                                    case 1:
                                        System.out.println("Pagado");
                                        VentaDAO.Registrar(objVenta);
                                        if (objVenta!=new Venta()){
                                            logger.info("Pago realizado");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Pagado");
                                        VentaDAO.Registrar(objVenta);
                                        if (objVenta!=new Venta()){
                                            logger.info("Pago realizado");
                                        }
                                        break;
                                    default:
                                        System.out.println("Escoge 1");
                                        logger.warn("Se intenta vulnerar el programa");
                                        break;
                                }
                            } else if (total > 6001) {
                                logger.info("Accediendo a pago 1 opcion");
                                System.out.println("El monto es muy alto, solo puede pagar con tajeta");
                                System.out.println("1.Tarjeta");
                                System.out.println("2.Cancelar venta");
                                int pagar1 = scanner.nextInt();
                                switch (pagar1) {
                                    case 1:
                                        System.out.println("Pagado");
                                        VentaDAO.Registrar(objVenta);
                                        if (objVenta!=new Venta()){
                                            logger.info("Pago realizado");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Cancelado");
                                        logger.info("Cancelo venta");
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

