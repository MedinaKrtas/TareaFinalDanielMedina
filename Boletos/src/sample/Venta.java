package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Date;

public class Venta {

    private IntegerProperty idVenta = new SimpleIntegerProperty();
    private IntegerProperty idCliente = new SimpleIntegerProperty();
    private IntegerProperty idBoleto = new SimpleIntegerProperty();
    private Date fecha;
    private BooleanProperty pagado = new SimpleBooleanProperty();


    public int getIdVenta() {
        return idVenta.get();
    }

    public IntegerProperty idVentaProperty() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta.set(idVenta);
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

    public int getIdBoleto() {
        return idBoleto.get();
    }

    public IntegerProperty idBoletoProperty() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto.set(idBoleto);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Override
    public String toString(){
        return "Venta{"+
                "idVenta="+idVenta.get()+"," +
                "idCliente="+idCliente.get()+"," +
                "idBoleto="+idBoleto.get()+"," +
                "fecha="+fecha.toString()+"," +
                "pagado="+pagado.get()+"}";
     }
}
