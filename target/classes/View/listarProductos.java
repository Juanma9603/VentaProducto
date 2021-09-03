package View;

import DAO.ProductoDAO;
import Entity.Producto;
import Entity.Venta;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Scanner;

public class listarProductos {
    private Scanner scanner = new Scanner(System.in);
    static Logger logger=Logger.getLogger(listarProductos.class);
    public void View(){
        try {
            PropertyConfigurator.configure("src/log.properties");
            System.out.println("Listar Productos");
            ArrayList<Producto> listproductos= ProductoDAO.list();
            for (int i=0;i< listproductos.size();i++){
                System.out.println(listproductos.get(i).toString());
            }
            if (listproductos!=new ArrayList<Producto>()){
                logger.info("Presentada la lista de productos");
            }
        }catch (Exception e){
            if (e!=null){
                logger.warn("Se intento vulnerar el programa");
            }
            System.out.println("ERROR: "+e);
        }
        System.out.println("Volviendo al menu");
    }
}
