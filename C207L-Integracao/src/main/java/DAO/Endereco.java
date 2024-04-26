package DAO;

import Model.DocesModel;
import Model.EnderecosModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Endereco extends Connection{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertEnderecos(EnderecosModel enderecosModel) {

        connectToDB();

        String sql = "INSERT INTO endereços (Endereco) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,enderecosModel.getEndereco());
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
    public boolean updateEnderecos(String newEndereco, String oldEndereco) {
        connectToDB();
        String sql = "UPDATE endereços SET Endereco=? where Endereco=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, newEndereco);
            //pst.setString(2, nomeDoce);
            pst.setString(2, oldEndereco);
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
    public boolean deleteEnderecos(String endereco) {
        connectToDB();
        String sql = "DELETE FROM endereços where Endereco=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, endereco);
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
    public ArrayList<EnderecosModel> selectEndereco() {
        ArrayList<EnderecosModel> enderecosM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM endereços";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de endereços: ");

            while (rs.next()) {

                EnderecosModel enderecosMod = new EnderecosModel(rs.getString("Endereco"));

                System.out.println("Endereço = " + enderecosMod.getEndereco());
                System.out.println("--------------------------------");

                enderecosM.add(enderecosMod);
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
        return enderecosM;
    }
}
