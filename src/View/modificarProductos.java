package View;

import DAO.ProductoDAO;
import Entity.Producto;

import java.util.Scanner;

public class modificarProductos {
    private Scanner scanner = new Scanner(System.in);
    public void View(){
        try {
            Producto objproducto=new Producto();
            System.out.println("Modificar");
            System.out.println("Indicar ID del Producto");
            objproducto= ProductoDAO.Consultar(scanner.nextInt());
            if (objproducto.getID()==0){
                System.out.println("Indica un ID existente");
            }else {
                System.out.println("Indicar Nombre");
                objproducto.setNombre(scanner.next());
                System.out.println("Indicar Costo");
                objproducto.setCosto(scanner.nextDouble());

                ProductoDAO.Modificar(objproducto);
            }
        }catch (Exception e){
            System.out.println("ERROR: "+e);
        }
    }
}
