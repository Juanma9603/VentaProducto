package View;

import DAO.VentaDAO;
import Entity.Venta;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

public class cancelarVenta {
    private Scanner scanner = new Scanner(System.in);
    boolean elegir=true;
    Venta objventa;
    static Logger logger=Logger.getLogger(cancelarVenta.class);

    public void View(){
        PropertyConfigurator.configure("src/log.properties");
        System.out.println("Cancelar");
        while (elegir) {
        System.out.println("Indica el ID de la Venta");
        objventa = VentaDAO.getInstance().Consultar(scanner.nextInt());
        if (objventa!=new Venta()){
            logger.info("Realizando consulta");
        }
        if (objventa.getID()==0){
            logger.warn("Se intenta vulnerar el programa");
            System.out.println("Indica un ID existente");
        }else {
            System.out.println(objventa.toString());

                System.out.println("1.Cancelar venta");
                System.out.println("2.Volver al menu");
                switch (scanner.nextInt()) {
                    case 1:
                        elegir = false;
                        if (elegir==false){
                            logger.info("Cancelando venta");
                        }
                        VentaDAO.getInstance().Cancelar(objventa.getID());
                        System.out.println("Cancelado");
                        break;
                    case 2:
                        elegir=false;
                        if (elegir==false){
                            logger.info("Regresando al menu");
                        }
                        break;
                    default:
                        System.out.println("Escoge entre los 2");
                        elegir=true;
                        if (elegir==true){
                            logger.warn("Se intento vulnerar el programa");
                        }
                        break;

                }
            }
        }
    }

}
