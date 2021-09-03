package Entity;

public class DetalleVenta {
    private int ID;
    private Producto objproducto;
    private int Cantidad;

    public DetalleVenta() {
        this.ID = 0;
        this.objproducto = new Producto();
        this.Cantidad = 0;
    }

    public DetalleVenta(int ID, Producto objproducto,int cantidad) {
        this.ID = ID;
        this.objproducto = objproducto;
        this.Cantidad = cantidad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Producto getObjproducto() {
        return objproducto;
    }

    public void setObjproducto(Producto objproducto) {
        this.objproducto = objproducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "ID=" + ID +
                ", objproducto=" + objproducto +
                ", Cantidad=" + Cantidad +
                '}';
    }
}
