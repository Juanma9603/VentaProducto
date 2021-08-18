package Entity;

public class Producto {
    private int ID;
    private double Igv;
    private double Descuento;
    private double Subtotal;
    private String Registro;


    public Producto() {
        this.ID = 0;
        Igv = 0.0;
        Descuento = 0.0;
        Subtotal = 0.0;
        Registro = "";
    }

    public Producto(int ID, double igv, double descuento, double subtotal, String registro) {
        this.ID = ID;
        Igv = igv;
        Descuento = descuento;
        Subtotal = subtotal;
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

    public String getRegistro() {
        return Registro;
    }

    public void setRegistro(String registro) {
        this.Registro = registro;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "ID=" + ID +
                ", Igv=" + Igv +
                ", Descuento=" + Descuento +
                ", Subtotal=" + Subtotal +
                ", Registro='" + Registro + '\'' +
                '}';
    }
}
