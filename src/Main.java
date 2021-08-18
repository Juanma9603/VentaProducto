import DAO.Conexion;
import DAO.ProductoDAO;
import Entity.Producto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Conexion con = new Conexion();
        Scanner scanner = new Scanner(System.in);
        Producto objproducto =new Producto();
        ProductoDAO ProductoDAO;
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
                                    System.out.println("Listar Productos");


                                    System.out.println("1.Volver al menu");

                                    break;


                            }
                            break;
                        case 3:
                            System.out.println("Venta");
                            System.out.println("Lista de Prodcutos");


                            System.out.println("Registrar Productos");
                            System.out.println("Indica el Item");

                            System.out.println("Indica la Cantidad");


                            System.out.println("1.Seguir registrando");
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

