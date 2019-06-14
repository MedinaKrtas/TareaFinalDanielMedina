package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class RegistrarVenta implements Initializable {

    @FXML private TextField field1;
    @FXML private TextField field2;
    @FXML private TextField field3;
    @FXML private TextField field4;
    @FXML private TextField field5;
    @FXML private TextField field6;
    @FXML private TextField field7;
    @FXML private TextField field8;
    @FXML private TextField field9;
    @FXML private TextField field10;
    @FXML private RadioButton radioButton1;
    @FXML private RadioButton radioButton2;
    @FXML private Button button;
    private OperacionesClientes operacionesClientes;

    private final ToggleGroup toggleGroup = new ToggleGroup();

    private Venta venta;
    private Seccion seccion;
    private Cliente cliente;

    private Controller controller;
    private String bandera = "Insertar";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venta = new Venta();
        seccion = new Seccion();
        cliente = new Cliente();
        operacionesClientes = new OperacionesClientes();


        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton2.setSelected(true);


        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(newValue.length() <= 9){
                    venta.setIdVenta(Integer.parseInt(newValue));
                }
            }
        });

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
                if(radioButton.getText().equals("Pagado")){
                    venta.setPagado(true);
                } else {
                    venta.setPagado(false);
                }
            }
        });

        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(newValue.length() < 9){
                    seccion.setIdSeccion(Integer.parseInt(newValue));
                }
            }
        });


        seccion.descripcionProperty().bind(field4.textProperty());

        field5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                seccion.setPrecio(Float.parseFloat(newValue));
            }
        });

        field6.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() < 9 ){
                    cliente.setIdCliente(Integer.parseInt(newValue));
                }
            }
        });

        field10.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() < 9){
                    venta.setIdBoleto(Integer.parseInt(newValue));
                }
            }
        });

        //binds gg

        cliente.nombreProperty().bind(field7.textProperty());
        cliente.apellidosProperty().bind(field8.textProperty());
        cliente.direccionProperty().bind(field9.textProperty());
        venta.idClienteProperty().bind(cliente.idClienteProperty());


        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    venta.setFecha(new SimpleDateFormat("yyyy-mm-dd").parse(field2.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(bandera.equals("Insertar")){
                    operacionesClientes.insertVenta(cliente,seccion,venta);
                } else {
                    operacionesClientes.updateCliente(cliente);
                    operacionesClientes.updateSeccion(seccion);
                    operacionesClientes.updateCompra(venta);

                }
                controller.getVentas();
                Stage stage =(Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.close();
            }
        });


    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setVenta(VistaTodo venta,Controller controller){
        this.controller = controller;
        field1.setDisable(true);
        field6.setDisable(true);
        field10.setDisable(true);
        field3.setDisable(true);
        field10.setText(venta.getNumBoleto()+"");
        button.setText("Actualizar");
        bandera = "Actualizar";
        field1.setText(venta.getCompraId()+"");

        if(venta.isPagado()){
            radioButton1.setSelected(true);
        } else {
            radioButton2.setSelected(true);
        }

        //field2.setText(venta.getFechaHora().toString());


        try{



            ResultSet resp = operacionesClientes.getCompraById(venta.getCompraId());

            while(resp.next()){
                field2.setText(resp.getDate("fechaHora").toString());
            }




            resp = operacionesClientes.getClienteById(venta.getClienteId());

            while(resp.next()){
                field6.setText(resp.getInt("clienteID")+"");
                field7.setText(resp.getString("nombre"));
                field8.setText(resp.getString("apellidos"));
                field9.setText(resp.getString("direccion"));

            }
            resp = operacionesClientes.getSeccionById(venta.getNumBoleto());

            while(resp.next()){
                field3.setText(resp.getInt("seccionId")+"");
                field4.setText(resp.getString("descripcion"));
                field5.setText(resp.getFloat("precio")+"");

            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }




    }
}
