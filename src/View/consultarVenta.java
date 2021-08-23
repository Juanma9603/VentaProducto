package View;

import DAO.VentaDAO;
import Entity.Venta;

import java.util.Scanner;

public class consultarVenta {
    private Scanner scanner = new Scanner(System.in);
    boolean volver=true;
    Venta objventa;

    public void View(){
        System.out.println("Consulta de Venta");
        while (volver) {
            System.out.println("Indica el Id de la Venta");
            objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
            System.out.println(objventa.toString());
            System.out.println("1.Consultar otra venta");
            System.out.println("2.Volver al menu");
            switch (scanner.nextInt()) {
                case 1:
                    volver = true;
                case 2:
                    volver = false;
            }
        }
    }
}
