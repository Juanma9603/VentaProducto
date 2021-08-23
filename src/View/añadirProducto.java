package View;

import DAO.ProductoDAO;
import Entity.Producto;

import java.util.Scanner;

public class añadirProducto {
    private Scanner scanner = new Scanner(System.in);
    public void View(){
        Producto objproducto=new Producto();
        System.out.println("Añadir");
        System.out.println("Indicar Nombre");
        objproducto.setNombre(scanner.next());
        System.out.println("Indicar Costo");
        objproducto.setCosto(scanner.nextDouble());

        ProductoDAO.Añadir(objproducto);
    }
}
