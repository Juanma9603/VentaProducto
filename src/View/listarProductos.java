package View;

import DAO.ProductoDAO;
import Entity.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class listarProductos {
    private Scanner scanner = new Scanner(System.in);
    public void View(){
        try {
            System.out.println("Listar Productos");
            ArrayList<Producto> listproductos= ProductoDAO.list();
            for (int i=0;i< listproductos.size();i++){
                System.out.println(listproductos.get(i).toString());
            }
        }catch (Exception e){
            System.out.println("ERROR: "+e);
        }
        System.out.println("1.Volver al menu");
    }
}
