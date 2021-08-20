import DAO.Conexion;
import DAO.ProductoDAO;
import DAO.VentaDAO;
import Entity.Producto;
import Entity.Venta;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Conexion con = new Conexion();
        Scanner scanner = new Scanner(System.in);
        Producto objproducto =new Producto();
        ProductoDAO ProductoDAO;
        Venta objventa=new Venta();
        VentaDAO VentaDAO;
        double Subtotal = 0;
        double Igv=0;
        double Descuento=0;
        int cantidad=0;
        double SumaSubt=0;
        boolean iniciar = true;


        while (iniciar) {

            System.out.println("Bienvenido a la Venta de Productos");
            System.out.println("Desea realizar alguna operacion?");
            System.out.println("1.Empezar");
            System.out.println("2.Salir");
            int option = scanner.nextInt();

            switch (option) {
                case 1:

                    System.out.println("1.Consulta de Venta");
                    System.out.println("2.Administrar Productos");
                    System.out.println("3.Registrar Venta");
                    System.out.println("4.Cancelar Venta");
                    int option1 = scanner.nextInt();

                    switch (option1) {
                        case 1:
                            System.out.println("Consulta de Venta");
                            System.out.println("Indica el Id de la Venta");


                            System.out.println("1.Consultar otra venta");
                            System.out.println("2.Volver al menu");

                            break;
                        case 2:
                            System.out.println("Administrar");
                            System.out.println("1.Añadir Productos");
                            System.out.println("2.Modificar Productos");
                            System.out.println("3.Eliminar Productos");
                            System.out.println("4.Listar Productos");
                            int administrar = scanner.nextInt();
                            ProductoDAO=new ProductoDAO();
                            switch (administrar) {
                                case 1:

                                    objproducto=new Producto();
                                    System.out.println("Añadir");
                                    System.out.println("Indicar Nombre");
                                    objproducto.setNombre(scanner.next());
                                    System.out.println("Indicar Costo");
                                    objproducto.setCosto(scanner.nextDouble());

                                    ProductoDAO.Añadir(objproducto);

                                    break;
                                case 2:
                                    objproducto=new Producto();
                                    System.out.println("Modificar");
                                    System.out.println("Indicar ID del Producto");
                                    objproducto.setID(scanner.nextInt());
                                    System.out.println("Indicar Nombre");
                                    objproducto.setNombre(scanner.next());
                                    System.out.println("Indicar Costo");
                                    objproducto.setCosto(scanner.nextDouble());

                                    ProductoDAO.Modificar(objproducto);

                                    break;
                                case 3:
                                    int eliminar;

                                    System.out.println("Eliminar");
                                    System.out.println("Indicar el Id del Producto");
                                    Producto objtmpproducto=ProductoDAO.Consultar(scanner.nextInt());
                                    System.out.println(objtmpproducto.toString());
                                    System.out.println("1.Eliminar Producto");
                                    System.out.println("2.Volver al menu");
                                    eliminar= scanner.nextInt();
                                    if (eliminar==1){
                                        ProductoDAO.Eliminar(objtmpproducto.getID());
                                        System.out.println("Eliminado");
                                    }else if(eliminar==2) {
                                        System.out.println("volver al menu");
                                        break;
                                    }else{
                                        System.out.println("Escoge entre los 2");

                                    }

                                    break;
                                case 4:
                                    ArrayList<Producto> list=new ArrayList<>();
                                    try {
                                    System.out.println("Listar Productos");
                                        ArrayList<Producto> listproductos= ProductoDAO.list();
                                        for (int i=0;i< listproductos.size();i++){
                                            System.out.println(listproductos.get(i).toString());
                                        }
                                    }catch (Exception e){
                                        System.out.println("ERROR: "+e);
                                    }

                                    System.out.println("1.Volver al menu");

                                    break;


                            }
                            break;
                        case 3:

                            ProductoDAO=new ProductoDAO();
                            System.out.println("Venta");
                            boolean volver=true;
                            while (volver) {
                            System.out.println("Lista de Productos");
                            ArrayList<Producto> list=new ArrayList<>();

                            try {

                                ArrayList<Producto> listproductos= ProductoDAO.list();
                                for (int i=0;i<listproductos.size();i++){
                                    System.out.println(listproductos.get(i).toString());
                                }
                            }catch (Exception e){
                                System.out.println("ERROR: "+e);
                            }

                                System.out.println("Registrar Productos");
                                System.out.println("Indica el Item");
                                Producto objtmpproducto = ProductoDAO.Consultar(scanner.nextInt());
                                System.out.println("Indica la Cantidad");
                                cantidad = scanner.nextInt();
                                Subtotal = objtmpproducto.getCosto() * cantidad;
                                Igv = (18 * Subtotal) / 100;
                                objventa.setSubtotal(objtmpproducto.getCosto() * cantidad);
                                objventa.setIgv(18 * Subtotal / 100);
                                objventa.setDescuento(0);
                                ArrayList<Venta> list1 = new ArrayList<>();
                                list1.add(objventa);

                                System.out.println("1.Seguir registrando");
                                System.out.println("2.Terminar de registrar");
                                int escoger=scanner.nextInt();
                                switch (escoger){
                                    case 1:
                                        volver=true;
                                        break;
                                    case 2:
                                        volver=false;
                                        break;
                                }

                                for (int i=0;i<list1.size();i++){
                                    SumaSubt=SumaSubt+list1.get(i).getSubtotal();
                                    System.out.println(SumaSubt);
                                    if (SumaSubt<50){
                                        System.out.println("No  hay descuento");
                                    }else if (SumaSubt>51 && SumaSubt<100){
                                        System.out.println("Descuento de 5%");
                                        Descuento=5*SumaSubt/100;
                                        SumaSubt=SumaSubt-Descuento;
                                        System.out.println(SumaSubt);
                                    }else {
                                        System.out.println("Descuento de 10%");
                                        Descuento=10*SumaSubt/100;
                                        SumaSubt=SumaSubt-Descuento;
                                        System.out.println(SumaSubt);
                                    }
                                }
                            }

                            System.out.println("2.Confirmar venta");
                            System.out.println("3.Volver al menu");


                            int venta= scanner.nextInt();
                            switch (venta){
                                case 1:
                                    break;
                                case 2:
                                    System.out.println("Lista");


                                    System.out.println("Modificar");
                                    System.out.println("1.Si");
                                    System.out.println("2.No");
                                    int modificar= scanner.nextInt();
                                    switch (modificar){
                                        case 1:
                                            System.out.println("Modificar");
                                            System.out.println("Indica el ID del Producto a Modificar");


                                            System.out.println("En caso de no desearlo utilizar 0 en ID y Cantidad");
                                            System.out.println("Indica el ID del Producto");

                                            System.out.println("Indica Cantidad");


                                            System.out.println("1.Volver a Menu");
                                            break;
                                        case 2:
                                            break;
                                    }

                                    System.out.println("Listo a Pagar");
                                    System.out.println("1.Si");
                                    System.out.println("2.No");

                                    int listo=scanner.nextInt();
                                    switch (listo){
                                        case 1:
                                            System.out.println("Listo a Pagar");


                                            System.out.println("1.Efectivo");

                                            System.out.println("2.Tarjeta");

                                            break;
                                        case 2:

                                            /*volver a venta*/
                                            break;
                                    }


                                    break;
                                case 3:
                                    break;
                            }

                    }
                    break;
                case 4:
                    System.out.println("Cancelar");
                    System.out.println("Indica el ID de la Venta");


                    System.out.println("1.Cancelar venta");
                    System.out.println("2.Volver al menu");
                    break;


                case 2:
                    System.out.println("Saliste");
                    iniciar = false;
                    break;

            }
        }


    }
}

