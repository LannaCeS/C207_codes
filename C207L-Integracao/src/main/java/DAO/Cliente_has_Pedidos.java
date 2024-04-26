package DAO;

import Model.EnderecosModel;
import Model.PedidosModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente_has_Pedidos extends Connection{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPedidos(PedidosModel pedidosModel) {

        connectToDB();

        String sql = "INSERT INTO clientes_has_pedidos (Clientes_CPF, Clientes_Endereços_Endereco, Pedidos_idPedidos) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,pedidosModel.getClientes_CPF());
            pst.setString(2,pedidosModel.getClientes_Enderecos_Endereco());
            pst.setInt(3,pedidosModel.getPedidos_idPedidos());
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

    //UPDATE
    public boolean updatePedidos(String newEndereco, int id_Pedido) {
        connectToDB();
        String sql = "UPDATE clientes_has_pedidos SET Clientes_Endereços_Endereco=? where Pedidos_idPedido=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, newEndereco);
            pst.setInt(2, id_Pedido);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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

    //DELETE
    public boolean deletePedidos(int id_pedidos) {
        connectToDB();
        String sql = "DELETE FROM clientes_has_pedidos where Pedidos_idPedido=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id_pedidos);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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
    public ArrayList<PedidosModel> selectPedidos() {
        ArrayList<PedidosModel> pedidosM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM clientes_has_pedidos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de endereços: ");

            while (rs.next()) {

                PedidosModel pedidosMod = new PedidosModel(rs.getInt("Pedidos_idPedido"), rs.getString("Clientes_CPF"), rs.getString("Clientes_Endereços_Endereco"));

                System.out.println("Número do pedido = " + pedidosMod.getClientes_Enderecos_Endereco());
                System.out.println("--------------------------------");

                pedidosM.add(pedidosMod);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return pedidosM;
    }
}
