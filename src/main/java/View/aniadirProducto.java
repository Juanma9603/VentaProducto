package View;

import DAO.ProductoDAO;
import Entity.Producto;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

public class aniadirProducto {
    private Scanner scanner = new Scanner(System.in);
    static Logger logger=Logger.getLogger(aniadirProducto.class);
    public void View(){
        PropertyConfigurator.configure("src/log.properties");
        Producto objproducto=new Producto();
        System.out.println("Añadir");
        System.out.println("Indicar Nombre");
        objproducto.setNombre(scanner.next());
        System.out.println("Indicar Costo");
        objproducto.setCosto(scanner.nextDouble());
        if (objproducto!=new Producto()){
            logger.info("Datos obtenidos");
        }

        ProductoDAO.Aniadir(objproducto);
        if (objproducto!=null){
            logger.info("añadiendo producto");
        }
    }
}
