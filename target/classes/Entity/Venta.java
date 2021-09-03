package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private int ID;
    private double Igv;
    private double Descuento;
    private double Subtotal;
    private Date Registro;
    private ArrayList<DetalleVenta> lstDetalleVenta;


    public Venta() {
        this.ID = 0;
        this.Igv = 0.0;
        this.Descuento = 0.0;
        this.Subtotal = 0.0;
        this.Registro = new Date();
        this.lstDetalleVenta=new ArrayList<DetalleVenta>();
    }

    public Venta(int ID, double igv, double descuento, double subtotal, Date registro, ArrayList<DetalleVenta> lstDetalleVenta) {
        this.ID = ID;
        this.Igv = igv;
        this.Descuento = descuento;
        this.Subtotal = subtotal;
        this.Registro = registro;
        this.lstDetalleVenta= lstDetalleVenta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getIgv() {
        return Igv;
    }

    public void setIgv(double igv) {
        this.Igv = igv;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double descuento) {
        this.Descuento = descuento;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.Subtotal = subtotal;
    }

    public Date getRegistro() {
        return Registro;
    }

    public void setRegistro(Date registro) {
        this.Registro = registro;
    }

    public ArrayList<DetalleVenta> getLstDetalleVenta() {
        return lstDetalleVenta;
    }

    public void setLstDetalleVenta(ArrayList<DetalleVenta> lstDetalleVenta) {
        this.lstDetalleVenta = lstDetalleVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "ID=" + ID +
                ", Igv=" + Igv +
                ", Descuento=" + Descuento +
                ", Subtotal=" + Subtotal +
                ", Registro=" + Registro +
                ", lstDetalleVenta=" + lstDetalleVenta +
                '}';
    }
}
