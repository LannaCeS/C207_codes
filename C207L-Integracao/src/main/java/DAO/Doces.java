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
            //System.out.println("Erro: " + exc.getMessage());
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
    public boolean updateDoces(String NomeDoce, int Quantidade, int quantidadeAnterior) {
        connectToDB();
        String sql = "UPDATE doces SET Quantidade = ? - ? where NomeDoce = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, quantidadeAnterior);
            pst.setInt(2, Quantidade);
            pst.setString(3, NomeDoce);
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
    public boolean deleteDoces(String nomeDoce, String receita) {
        connectToDB();
        String sql = "DELETE FROM doces where NomeDoce=? AND Receitas_NomeReceita = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeDoce);
            pst.setString(2, receita);
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

    public boolean verificarQuantidade(int quant, String sweet) {
        connectToDB();
        String sql = "SELECT quantidade FROM doces WHERE NomeDoce = ?";
        boolean autorizado = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, sweet);
            rs = pst.executeQuery();

            while (rs.next()) {
                if(quant <= rs.getInt("Quantidade")){
                    autorizado = true;
                }else{
                    autorizado = false;
                }
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return autorizado;
    }

    public int selectQuantidade(String sweet) {
        connectToDB();
        String sql = "SELECT quantidade FROM doces WHERE NomeDoce = ?";
        int quantity = 0;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, sweet);
            rs = pst.executeQuery();

            while (rs.next()) {
                quantity = rs.getInt("Quantidade");
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return quantity;
    }

    public void disableForeignKey() {
        connectToDB();
        String dropForeignKeySQL = "ALTER TABLE pedidos_has_doces DROP FOREIGN KEY pedidos_has_doces_ibfk_2 ";

        try {
            st = con.createStatement();
            st.executeUpdate(dropForeignKeySQL);
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }


    public void truncateDoces() {
        connectToDB();
        String truncateTableSQL = "TRUNCATE TABLE doces";

        try {
            st = con.createStatement();
            st.executeUpdate(truncateTableSQL);
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public void enableForeignKey() {
        connectToDB();
        String addForeignKeySQL = "ALTER TABLE pedidos_has_doces " +
                "ADD CONSTRAINT fk_Pedidos_has_Doces_Doces1 " +
                "FOREIGN KEY (Doces_NomeDoce, Doces_Receitas_NomeReceita) " +
                "REFERENCES doces (NomeDoce, Receitas_NomeReceita)";

        try {
            st = con.createStatement();
            st.executeUpdate(addForeignKeySQL);
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }



}
