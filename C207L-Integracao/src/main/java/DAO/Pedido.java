package DAO;

import Model.ClientesModel;
import Model.PedidoModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Pedido extends Connection {
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPedido() {

        connectToDB();

        String sql = "INSERT INTO pedidos value ()";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public int selectPedido() {
        connectToDB();
        String sql = "SELECT idPedidos FROM Pedidos ORDER BY idPedidos DESC LIMIT 1";
        int ident = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if(rs.next()){
                ident  = rs.getInt("idPedidos");
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return ident;
    }

    public void selectAllPedidos() {
        connectToDB();
        String sql = "SELECT cp.Pedidos_idPedidos, Doces_NomeDoce, Clientes_CPF, Clientes_Endereços_Endereco FROM pedidos_has_doces AS pd JOIN clientes_has_pedidos AS cp ON pd.Pedidos_idPedidos = cp.Pedidos_idPedidos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de pedidos: ");
            if(!rs.next()){
                System.out.println("Sem pedidos!");
            }else {
                do{

                    //ClientesModel clientesMod = new ClientesModel(rs.getString("CPF"), rs.getString("NomeCliente"), rs.getString("Endereços_Endereco"));

                    PedidoModel pedidoMod = new PedidoModel();
                    System.out.println("Id do Pedido = " + rs.getInt("Pedidos_idPedidos"));
                    System.out.println("Nome do doce = " + rs.getString("Doces_NomeDoce"));
                    System.out.println("CPF = " + rs.getString("Clientes_CPF"));
                    System.out.println("Endereço de entrega = " + rs.getString("Clientes_Endereços_Endereco"));
                    System.out.println("--------------------------------");

                }while (rs.next());
                sucesso = true;
            }
        } catch (SQLException e) {
            //System.out.println("Erro: " + e.getMessage());
            System.out.println("Sem pedidos!");
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
