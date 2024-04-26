package DAO;

import Model.DocesModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Doces extends Connection{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertDoces(DocesModel docesModel) {

        connectToDB();

        String sql = "INSERT INTO doces (Quantidade, NomeDoce, Receitas_NomeReceita) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,docesModel.getQuantidade());
            pst.setString(2, docesModel.getNomeDoce());
            pst.setString(3, docesModel.getReceitas_NomeReceita());
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
    public boolean updateDoces(String newNomeDoce, String newReceita, String nomeDoce) {
        connectToDB();
        String sql = "UPDATE doces SET NomeDoce=?, Receitas_NomeReceita = ? where NomeDoce = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, newNomeDoce);
            //pst.setString(2, nomeDoce);
            pst.setString(2, newReceita);
            pst.setString(3, nomeDoce);
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
    public boolean deleteDoces(String nomeDoce) {
        connectToDB();
        String sql = "DELETE FROM doces where NomeDoce=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeDoce);
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
    public ArrayList<DocesModel> selectDoce() {
        ArrayList<DocesModel> docesM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM doces";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de doces: ");

            while (rs.next()) {

                DocesModel docesMod = new DocesModel(rs.getInt("Quantidade"), rs.getString("NomeDoce"), rs.getString("Receitas_NomeReceita"));

                System.out.println("Quantidade = " + docesMod.getQuantidade());
                System.out.println("Nome = " + docesMod.getNomeDoce());
                System.out.println("Receita = " + docesMod.getReceitas_NomeReceita());
                System.out.println("--------------------------------");

                docesM.add(docesMod);
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
        return docesM;
    }
}
