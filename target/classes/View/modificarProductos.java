package View;

import DAO.ProductoDAO;
import Entity.Producto;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class modificarProductos {
    private Scanner scanner = new Scanner(System.in);
    static Logger logger=Logger.getLogger(modificarProductos.class);

    public void View(){
        try {
            Producto objproducto=new Producto();
            System.out.println("Modificar");
            System.out.println("Indicar ID del Producto");
            objproducto= ProductoDAO.Consultar(scanner.nextInt());
            if (objproducto!=new Producto()){
                logger.info("Realizando la consulta");
            }
            if (objproducto.getID()==0){
                logger.warn("Se intenta vulnerar el programa");
                System.out.println("Indica un ID existente");
            }else {
                System.out.println("Indicar Nombre");
                objproducto.setNombre(scanner.next());
                System.out.println("Indicar Costo");
                objproducto.setCosto(scanner.nextDouble());

                ProductoDAO.Modificar(objproducto);
                if (objproducto!=null){
                    logger.info("modificando producto");
                }
            }
        }catch (Exception e){
            if (e!=null){
                logger.warn("Error al modificar");
            }
            System.out.println("ERROR: "+e);
        }
    }
}
