package Entity;

public class Venta {
    private int ID;
    private double Igv;
    private double Descuento;
    private double Subtotal;
    private int Cantidad;
    private String Registro;


    public Venta() {
        this.ID = 0;
        Igv = 0.0;
        Descuento = 0.0;
        Subtotal = 0.0;
        this.Cantidad = 0;
        Registro = "";
    }

    public Venta(int ID, double igv, double descuento, double subtotal, int cantidad, String registro) {
        this.ID = ID;
        Igv = igv;
        Descuento = descuento;
        Subtotal = subtotal;
        this.Cantidad = cantidad;
        Registro = registro;
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public String getRegistro() {
        return Registro;
    }

    public void setRegistro(String registro) {
        this.Registro = registro;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "ID=" + ID +
                ", Igv=" + Igv +
                ", Descuento=" + Descuento +
                ", Subtotal=" + Subtotal +
                        ", Cantidad=" + Cantidad +
        ", Registro='" + Registro + '\'' +
                '}';
    }
}
