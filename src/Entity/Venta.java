package Entity;

public class Venta {
    private int ID;
    private String Nombre;
    private double Costo;
    private int Cantidad;
    private String Registro;

    public Venta() {
        this.ID = 0;
        this.Nombre = "";
        this.Costo = 0.0;
        this.Cantidad = 0;
        this.Registro = "";
    }

    public Venta(int ID, String nombre, double costo, int cantidad, String registro) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Costo = costo;
        this.Cantidad = cantidad;
        this.Registro = registro;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        this.Costo = costo;
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
                ", Nombre='" + Nombre + '\'' +
                ", Costo=" + Costo +
                ", Cantidad=" + Cantidad +
                ", Registro='" + Registro + '\'' +
                '}';
    }
}
