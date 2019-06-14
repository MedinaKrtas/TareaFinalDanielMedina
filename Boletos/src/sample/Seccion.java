package sample;


import javafx.beans.property.*;

public class Seccion {
    private IntegerProperty idSeccion = new SimpleIntegerProperty();
    private StringProperty descripcion = new SimpleStringProperty();
    private FloatProperty precio = new SimpleFloatProperty();

    public int getIdSeccion() {
        return idSeccion.get();
    }

    public IntegerProperty idSeccionProperty() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion.set(idSeccion);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    @Override
    public String toString(){
        return "Venta{"+
                "idSeccion="+idSeccion.get()+"," +
                "descripcion="+descripcion.get()+"," +
                "precio="+precio.get()+"}";

    }
}
