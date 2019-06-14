package sample;

import javafx.beans.property.*;

import java.util.Date;

public class VistaTodo {
    private IntegerProperty compraId = new SimpleIntegerProperty();
    private IntegerProperty clienteId = new SimpleIntegerProperty();
    private IntegerProperty numBoleto = new SimpleIntegerProperty();
    private BooleanProperty pagado = new SimpleBooleanProperty();
    private Date fechaHora;
    private StringProperty descripcion = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public int getCompraId() {
        return compraId.get();
    }

    public IntegerProperty compraIdProperty() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId.set(compraId);
    }

    public int getClienteId() {
        return clienteId.get();
    }

    public IntegerProperty clienteIdProperty() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }

    public int getNumBoleto() {
        return numBoleto.get();
    }

    public IntegerProperty numBoletoProperty() {
        return numBoleto;
    }

    public void setNumBoleto(int numBoleto) {
        this.numBoleto.set(numBoleto);
    }

    public boolean isPagado() {
        return pagado.get();
    }

    public BooleanProperty pagadoProperty() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado.set(pagado);
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
}
