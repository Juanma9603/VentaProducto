package View;

import DAO.ProductoDAO;
import Entity.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class modificarVentaRegistrada {
    private Scanner scanner = new Scanner(System.in);


    public void View(){
        System.out.println("Modificar");

        System.out.println("Indica el ID del Producto a Modificar");
        Producto objtmpmod = ProductoDAO.Consultar(scanner.nextInt());
        System.out.println("1.En caso de no desear el Producto");
        System.out.println("2.En caso de cambiar el Producto");
        switch (scanner.nextInt()){
            case 1:
                /*sp_detalledelete*/
            case 2:
                try {
                    System.out.println("Lista de Productos");
                    ArrayList<Producto> listproductos= ProductoDAO.list();
                    for (int i=0;i<listproductos.size();i++){
                        System.out.println(listproductos.get(i).toString());
                        System.out.println("Escoger");




                    }
                }catch (Exception e){
                    System.out.println("ERROR: "+e);
                }

        }

        System.out.println("Indica el ID del Producto");
        System.out.println("Indica Cantidad");
    }
}
