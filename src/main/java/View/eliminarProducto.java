package View;

import DAO.ProductoDAO;
import Entity.Producto;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

public class eliminarProducto {
    private Scanner scanner = new Scanner(System.in);
    boolean elegir=true;
    static Logger logger=Logger.getLogger(eliminarProducto.class);

    public void View(){
        PropertyConfigurator.configure("src/log.properties");
        int eliminar;
        System.out.println("Eliminar");
        System.out.println("Indicar el Id del Producto");
        Producto objtmpproducto= ProductoDAO.Consultar(scanner.nextInt());
        if (objtmpproducto!=new Producto()){
            logger.info("Realizando consulta");
        }
        if (objtmpproducto.getID()==0){
            logger.warn("Se intento vulnerar el programa");
            System.out.println("Indica un ID existente");
        }else {
            System.out.println(objtmpproducto.toString());
            logger.info("Seleccionando el producto");
            while (elegir){
                System.out.println("1.Eliminar Producto");
                System.out.println("2.Volver al menu");
                eliminar = scanner.nextInt();
                if (eliminar == 1) {
                    elegir=false;
                    ProductoDAO.Eliminar(objtmpproducto.getID());
                    logger.info("Eliminando producto");
                    System.out.println("Eliminado");
                } else if (eliminar == 2) {
                    elegir=false;
                    logger.info("Regresando al menu");
                } else {
                    System.out.println("Escoge entre los 2");
                    elegir=true;
                    logger.warn("Se intento vulnerar el programa");
                }
            }
        }

    }
}
