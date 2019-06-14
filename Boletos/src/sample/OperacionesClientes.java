package sample;

import java.sql.*;

public class OperacionesClientes {
    private Connection conexion = null;


    public OperacionesClientes() {
        DBManager dbManager = null;
        try {
            dbManager = new DBManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conexion = dbManager.getConnection();
    }



    public void insertVenta(Cliente cliente,Seccion seccion,Venta venta){
        try{

            String sql = "INSERT INTO cliente (clienteID,nombre,apellidos,direccion)" +
                    "VALUES (?,?,?,?)";

            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,cliente.getIdCliente());
            ps.setString(2,cliente.getNombre());
            ps.setString(3,cliente.getApellidos());
            ps.setString(4,cliente.getDireccion());
            ps.execute();


            sql = "INSERT INTO seccion (seccionId,descripcion,precio)" +
                    "VALUES (?,?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getIdSeccion());
            ps.setString(2,seccion.getDescripcion());
            ps.setFloat(3,seccion.getPrecio());
            ps.execute();


            sql = "INSERT INTO boleto (numBoleto,seccionId) " +
                    "VALUES (?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,venta.getIdBoleto());
            ps.setInt(2,seccion.getIdSeccion());
            ps.execute();


            sql = "INSERT INTO compra (compraId,clienteId,numBoleto,fechaHora,pagado)" +
                    "VALUES (?,?,?,?,?)";

            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,venta.getIdVenta());
            ps.setInt(2,venta.getIdCliente());
            ps.setInt(3,venta.getIdBoleto());
            ps.setDate(4, new Date(venta.getFecha().getTime()));
            ps.setBoolean(5,venta.isPagado());
            ps.execute();

            System.out.println("Se realizo la inserción con exito");

        }catch (SQLException ex){
            ex.printStackTrace();

        }



    }


    //operaciones con el cliente

    public void deleteCliente(Cliente cliente){

        try{

            String sql = "DELETE FROM cliente WHERE clienteID = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,cliente.getIdCliente());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente){
        try{

            String sql = "UPDATE cliente set nombre=?,apellidos=?,direccion=?" +
                    " WHERE clienteID = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellidos());
            ps.setString(3,cliente.getDireccion());
            ps.setInt(4,cliente.getIdCliente());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){

        }

    }

    public ResultSet getClienteById(int clienteID){
        try{
            String sql = "SELECT * FROM cliente WHERE clienteID = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,clienteID);

            ResultSet resp = ps.executeQuery();
            return resp;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }



    public ResultSet getClientes(){
        try {

            String sql = "SELECT * FROM cliente;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }



    }




    //operaciones con la seccion


    public ResultSet getSeccionById(int seccionId){
        try{
            String sql = "SELECT s.seccionId,s.descripcion,s.precio" +
                    " FROM boleto b " +
                    "INNER JOIN seccion s ON (b.seccionId = s.seccionId)" +
                    " WHERE b.numBoleto = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccionId);

            ResultSet resp = ps.executeQuery();
            return resp;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }

    public void deleteSeccion(Seccion seccion){
        try{


            String sql ="DELETE FROM boleto WHERE seccionId = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getIdSeccion());
            ps.execute();

            sql = "DELETE FROM seccion WHERE seccionId = ?";
            ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,seccion.getIdSeccion());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public ResultSet getTodo(){
        try{
            String sql = "select c.compraId,c.clienteId,c.numBoleto,c.pagado,c.fechaHora,s.descripcion,cl.nombre\n" +
                    "from compra c\n" +
                    "INNER JOIN cliente cl on (cl.clienteID = c.clienteId)\n" +
                    "INNER JOIN boleto b on (b.numBoleto = c.numBoleto)\n" +
                    "INNER JOIN seccion s on (s.seccionId = b.seccionId)";
            PreparedStatement ps = this.conexion.prepareStatement(sql);

            ResultSet resp = ps.executeQuery();
            return resp;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }

        //return null;

    }


    public void updateSeccion(Seccion seccion){
        try{

            String sql = "UPDATE seccion set descripcion=?,precio=?" +
                    " WHERE seccionId = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setString(1,seccion.getDescripcion());
            ps.setFloat(2,seccion.getPrecio());
            ps.setInt(3,seccion.getIdSeccion());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet getSeccion(){
        try {

            String sql = "SELECT * FROM seccion;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }

    //operacion con la compra
    public void deleteCompra(VistaTodo venta){
        try{

            String sql = "DELETE FROM compra WHERE compraId = ?";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setInt(1,venta.getCompraId());
            ps.execute();
            System.out.println("Se elimino con exito");


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public void updateCompra(Venta venta){
        try{

            String sql = "UPDATE compra SET pagado=?" +
                    " WHERE compraId = ?";


            PreparedStatement ps = this.conexion.prepareStatement(sql);
            ps.setBoolean(1,venta.isPagado());
            ps.setInt(2,venta.getIdVenta());
            ps.execute();

            System.out.println("Actualizacion con exito");

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet getCompra(){
        try {

            String sql = "SELECT * FROM compra ;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);

            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet getCompraById(int id){
        try {

            String sql = "SELECT fechaHora FROM compra WHERE compraId=?;";
            PreparedStatement stmt = this.conexion.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs =  stmt.executeQuery();

            return rs;

        }catch (SQLException ex){

            ex.printStackTrace();
            return null;
        }
    }
}
