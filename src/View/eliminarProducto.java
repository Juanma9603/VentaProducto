package View;

import DAO.ProductoDAO;
import Entity.Producto;

import java.util.Scanner;

public class eliminarProducto {
    private Scanner scanner = new Scanner(System.in);
    boolean elegir=true;
    public void View(){
        int eliminar;
        System.out.println("Eliminar");
        System.out.println("Indicar el Id del Producto");
        Producto objtmpproducto= ProductoDAO.Consultar(scanner.nextInt());
        System.out.println(objtmpproducto.toString());
        while (elegir){
            System.out.println("1.Eliminar Producto");
            System.out.println("2.Volver al menu");
            eliminar = scanner.nextInt();
            if (eliminar == 1) {
                elegir=false;
                ProductoDAO.Eliminar(objtmpproducto.getID());
                System.out.println("Eliminado");
            } else if (eliminar == 2) {

            } else {
                System.out.println("Escoge entre los 2");
                elegir=true;
            }
        }
    }
}
