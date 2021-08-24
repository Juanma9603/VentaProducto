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
        while (elegir) {
        System.out.println("Indica el ID de la Venta");
        objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
        if (objventa.getID()==0){
            System.out.println("Indica un ID existente");
        }else {
            System.out.println(objventa.toString());
                System.out.println("1.Cancelar venta");
                System.out.println("2.Volver al menu");
                switch (scanner.nextInt()) {
                    case 1:
                        elegir = false;
                        VentaDAO.getInstance().Cancelar(objventa.getID());
                        System.out.println("Cancelado");
                        break;
                    case 2:
                        elegir=false;
                        break;
                    default:
                        System.out.println("Escoge entre los 2");
                        elegir=true;
                        break;

                }
            }
        }
    }

}
