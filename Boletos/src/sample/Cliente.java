package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

    private IntegerProperty idCliente = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellidos = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();

    public Cliente(){

    }


    public int getIdCliente() {
        return idCliente.get();
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }


    @Override
    public String toString(){
        return "Cliente{"+
                "idCliente="+idCliente.get()+"," +
                "nombre="+nombre.get()+"," +
                "apellidos="+apellidos.get()+"," +
                "direccion="+direccion.get()+"}";
    }
}
