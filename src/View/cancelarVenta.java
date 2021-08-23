package View;

import DAO.VentaDAO;
import Entity.Venta;

import java.util.Scanner;

public class cancelarVenta {
    private Scanner scanner = new Scanner(System.in);
    boolean elegir=true;
    Venta objventa;

    public void View(){
        System.out.println("Cancelar");
        System.out.println("Indica el ID de la Venta");
        objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
        System.out.println(objventa.toString());
        while (elegir) {
            System.out.println("1.Cancelar venta");
            System.out.println("2.Volver al menu");
            int cancelar = scanner.nextInt();
            if (cancelar == 1) {
                elegir = false;
                VentaDAO.getInstance().Cancelar(objventa.getID());
                System.out.println("Cancelado");
            } else if (cancelar == 2) {

            } else {
                System.out.println("Escoge entre los 2");
                elegir = true;
            }
        }

    }

}
