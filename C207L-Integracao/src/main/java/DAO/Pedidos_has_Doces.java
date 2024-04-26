package DAO;

import Model.ClientesModel;
import Model.PedidosDocesModel;
import Model.ReceitasModel;

import java.sql.SQLException;
import java.util.ArrayList;
public class Pedidos_has_Doces extends Connection {
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPedidosDoces(PedidosDocesModel pedidosDocesModel) {

        connectToDB();

        String sql = "INSERT INTO pedidos_has_doces (Pedidos_idPedido, Doces_nomeDoce, Doces_Receitas_NomeReceita) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,pedidosDocesModel.getPedidos_idPedidos());
            pst.setString(2,pedidosDocesModel.getDoces_nomeDoce());
            pst.setString(3,pedidosDocesModel.getDoces_Receitas_NomeReceita());

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
    public boolean updatePedidosDoces(String newNomeDoce, String newNomeReceita, int id_Pedido) {
        connectToDB();
        String sql = "UPDATE pedidos_has_doces SET Doces_NomeDoces, Doces_Receitas_NomeReceita=(?,?) where Pedidos_idPedido=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, newNomeDoce);
            pst.setString(2, newNomeReceita);
            pst.setInt(3, id_Pedido);
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
    public boolean deletePedidosDoces(int id_pedidos) {
        connectToDB();
        String sql = "DELETE FROM pedidos_has_doces where Pedidos_idPedido=?";
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
    public ArrayList<PedidosDocesModel> selectPedidosDoces() {
        ArrayList<PedidosDocesModel> pedidosDocesM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM pedidos_has_doces";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de pedidos e seus doces: ");

            while (rs.next()) {

                PedidosDocesModel pedidosDocesMod = new PedidosDocesModel(rs.getInt("Pedidos_idPedido"), rs.getString("Doces_nomeDoce"), rs.getString("Doces_Receitas_NomeReceita"));
                //Pedidos_idPedido, Doces_nomeDoce, Doces_Receitas_NomeReceita
                System.out.println("Número do pedido = " + pedidosDocesMod.getPedidos_idPedidos());
                System.out.println("Nome do doce = " + pedidosDocesMod.getDoces_nomeDoce());
                System.out.println("Número da receita = " + pedidosDocesMod.getDoces_Receitas_NomeReceita());
                System.out.println("--------------------------------");

                pedidosDocesM.add(pedidosDocesMod);
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
        return pedidosDocesM;
    }
}
