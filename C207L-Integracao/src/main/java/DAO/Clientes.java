package DAO;

import Model.ClientesModel;
import Model.DocesModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Clientes extends Connection{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertCliente(ClientesModel clientesModel) {

        connectToDB();

        String sql = "INSERT INTO clientes (CPF, NomeCliente, Endereços_Endereco) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,clientesModel.getCPF());
            pst.setString(2, clientesModel.getNomeCliente());
            pst.setString(3, clientesModel.getEndereco());
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
    public boolean updateClientes(String CPF, String Enderecos_Endereco) {
        connectToDB();
        String sql = "UPDATE clientes SET Endereços_Endereco=? where CPF=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, CPF);
            //pst.setString(2, nomeDoce);
            pst.setString(2, Enderecos_Endereco);
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
    public boolean deleteClientes(String CPF) {
        connectToDB();
        String sql = "DELETE FROM clientes where CPF=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, CPF);
            //pst.setInt(1, quantidade);
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
    public ArrayList<ClientesModel> selectclientes() {
        ArrayList<ClientesModel> clientesM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM clientes";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de clientes: ");

            while (rs.next()) {

                ClientesModel clientesMod = new ClientesModel(rs.getString("CPF"), rs.getString("NomeCliente"), rs.getString("Endereços_Endereco"));

                System.out.println("CPF = " + clientesMod.getCPF());
                System.out.println("Nome = " + clientesMod.getNomeCliente());
                System.out.println("Endereço = " + clientesMod.getEndereco());
                System.out.println("--------------------------------");

                clientesM.add(clientesMod);
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
        return clientesM;
    }
}
