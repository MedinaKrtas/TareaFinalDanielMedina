package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private TableView tableVentas;
    private OperacionesClientes operacionesClientes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableVentas.getColumns().clear();
        tableVentas.getItems().clear();

        operacionesClientes = new OperacionesClientes();

        //nombre de las tablas principales
        TableColumn VentaId = new TableColumn<>("ID venta");
        TableColumn ClienteId = new TableColumn<>("ID Cliente");
        TableColumn boletoId = new TableColumn<>("ID Boleto");
        TableColumn fecha = new TableColumn<>("Fecha venta");
        TableColumn pagado = new TableColumn<>("Pagado");
        TableColumn descripcion = new TableColumn<>("Descripcion");
        TableColumn nombre = new TableColumn<>("Cliente Nombre");

        tableVentas.getColumns().addAll(VentaId,ClienteId,boletoId,fecha,pagado,descripcion,nombre);


        VentaId.setCellValueFactory(new PropertyValueFactory<>("compraId"));
        ClienteId.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        boletoId.setCellValueFactory(new PropertyValueFactory<>("numBoleto"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        pagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        getVentas();


        button1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader loader = new FXMLLoader();
                try {
                    AnchorPane root = loader.load(getClass().getResourceAsStream("registrar.fxml"));
                    RegistrarVenta registrarVenta = (RegistrarVenta)loader.getController();
                    registrarVenta.setController(Controller.this);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //para modificar
        button2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row = tableVentas.getSelectionModel().getSelectedIndex();
                if(row != -1){
                    VistaTodo venta =(VistaTodo)tableVentas.getItems().get(row);
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        AnchorPane root = loader.load(getClass().getResourceAsStream("registrar.fxml"));
                        RegistrarVenta registrarVenta = (RegistrarVenta)loader.getController();
                        registrarVenta.setVenta(venta,Controller.this);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //para eliminar
        button3.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int row = tableVentas.getSelectionModel().getSelectedIndex();
                if(row != -1){ //si no hay nada
                    VistaTodo venta = (VistaTodo) tableVentas.getItems().get(row);
                    operacionesClientes.deleteCompra(venta);
                    getVentas();
                }
            }
        });
    }


    public void getVentas(){
        tableVentas.getItems().clear();
        try {
            ResultSet resp = operacionesClientes.getTodo();

            while (resp.next()) {
                VistaTodo vistaTodo = new VistaTodo();
                vistaTodo.setCompraId(resp.getInt("compraId"));
                vistaTodo.setClienteId(resp.getInt("clienteId"));
                vistaTodo.setNumBoleto(resp.getInt("numBoleto"));
                vistaTodo.setPagado(resp.getBoolean("pagado"));
                vistaTodo.setDescripcion(resp.getString("descripcion"));
                vistaTodo.setNombre(resp.getString("nombre"));
                tableVentas.getItems().add(vistaTodo);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
