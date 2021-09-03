package Entity;

import java.util.Date;

public class Producto {

    private int ID;
    private String Nombre;
    private double Costo;
    private Date Registro;

    public Producto() {
        this.ID = 0;
        this.Nombre = "";
        this.Costo = 0.0;
        this.Registro = new Date();
    }

    public Producto(int ID, String nombre, double costo, Date registro) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Costo = costo;
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

    public Date getRegistro() {
        return Registro;
    }

    public void setRegistro(Date registro) {
        this.Registro = registro;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", Costo=" + Costo +
                ", Registro='" + Registro + '\'' +
                '}';
    }
}
