import DAO.Conexion;
import DAO.ProductoDAO;
import DAO.VentaDAO;
import Entity.DetalleVenta;
import Entity.Producto;
import Entity.Venta;
import View.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Conexion con = Conexion.getInstance();
        Scanner scanner = new Scanner(System.in);
        Producto objproducto =new Producto();
        ProductoDAO ProductoDAO;
        Venta objventa=new Venta();
        VentaDAO VentaDAO;

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
                            new consultarVenta().View();
                            break;
                        case 2:
                            System.out.println("Administrar");
                            System.out.println("1.Añadir Productos");
                            System.out.println("2.Modificar Productos");
                            System.out.println("3.Eliminar Productos");
                            System.out.println("4.Listar Productos");
                            int administrar = scanner.nextInt();
                                    switch (administrar) {
                                        case 1:
                                            new añadirProducto().View();
                                            break;
                                        case 2:
                                            new modificarProductos().View();
                                            break;
                                        case 3:
                                            new eliminarProducto().View();
                                            break;
                                        case 4:
                                            new listarProductos().View();
                                            break;
                                    }
                                break;
                        case 3:
                            new registrarVenta().View();
                            break;

                        case 4:
                            new cancelarVenta().View();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Saliste");
                    iniciar = false;
                    break;

            }
        }


    }
}

