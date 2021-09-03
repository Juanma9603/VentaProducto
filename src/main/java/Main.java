import DAO.Conexion;

import View.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import java.util.Scanner;

public class Main {

    static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Conexion con = Conexion.getInstance();
        Scanner scanner = new Scanner(System.in);
        /*BasicConfigurator.configure();*/
        PropertyConfigurator.configure("src/log.properties");
        logger.info("Iniciando app");


        boolean iniciar = true;


        while (iniciar) {

            System.out.println("Bienvenido a la Venta de Productos");
            System.out.println("Desea realizar alguna operacion?");
            System.out.println("1.Empezar");
            System.out.println("2.Salir");
            int option = scanner.nextInt();
            if (option==1) {
                logger.info("Se ha iniciado la operacion");
            } else if (option==2){
                logger.info("Seleccionado salir");
            }else {
                logger.warn("Se intenta vulnerar el sistema");}
            switch (option) {
                case 1:
                    System.out.println("1.Consulta de Venta");
                    System.out.println("2.Administrar Productos");
                    System.out.println("3.Registrar Venta");
                    System.out.println("4.Cancelar Venta");
                    int option1 = scanner.nextInt();
                    if (option1==1){
                        logger.info("Consulta de venta: utilizado");
                    }else if (option1==2){
                        logger.info("Administrar productos: utilizado");
                    }else if (option1==3){
                        logger.info("Registra venta: utilizado");
                    }else if (option1==4){
                        logger.info("Cancela venta: utilizado");
                    }else {
                        logger.warn("Se intenta vulnerar el sistema");
                    }
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
                            if (administrar==1){
                                logger.info("Añadir productos: utilizado");
                            }else if (administrar==2){
                                logger.info("Modificar productos: utilizado");
                            }else if (administrar==3){
                                logger.info("Eliminar productos: utilizado");
                            }else if (administrar==4){
                                logger.info("Listar productos: utilizado");
                            }else {
                                logger.warn("Se intenta vulnerar el sistema");
                            }
                                    switch (administrar) {
                                        case 1:
                                            new aniadirProducto().View();
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
                    if (iniciar==false)logger.info("Finalizando el programa");
                    break;

            }
        }


    }
}

