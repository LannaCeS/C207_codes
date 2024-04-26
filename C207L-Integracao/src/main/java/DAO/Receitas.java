package DAO;

import Model.ClientesModel;
import Model.ReceitasModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Receitas extends Connection{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertReceita(ReceitasModel receitasModel) {

        connectToDB();

        String sql = "INSERT INTO receitas (NomeReceita, Ovo, Farinha, Fermento, Leite, Acucar, Cacau, Cenoura, Morango, Abacaxi, Chantilly) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,receitasModel.getNomeReceita());
            pst.setBoolean(2, receitasModel.getOvo());
            pst.setBoolean(3, receitasModel.getFarinha());
            pst.setBoolean(4, receitasModel.getFermento());
            pst.setBoolean(5, receitasModel.getLeite());
            pst.setBoolean(6, receitasModel.getAcucar());
            pst.setBoolean(7, receitasModel.getCacau());
            pst.setBoolean(8, receitasModel.getCenoura());
            pst.setBoolean(9, receitasModel.getMorango());
            pst.setBoolean(10, receitasModel.getAbacaxi());
            pst.setBoolean(11, receitasModel.getChantilly());
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
    public boolean updateReceitasMorango(String nomeReceita, boolean morango) {
        connectToDB();
        String sql = "UPDATE receitas SET Morango=? where NomeReceita=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setBoolean(1, morango);
            //pst.setString(2, nomeDoce);
            pst.setString(2, nomeReceita);
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
    public boolean deleteReceitas(String nomeReceita) {
        connectToDB();
        String sql = "DELETE FROM receitas where NomeReceita=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeReceita);
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
    public ArrayList<ReceitasModel> selectreceitas() {
        ArrayList<ReceitasModel> receitasM = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM receitas";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de receitas: ");

            while (rs.next()) {

                ReceitasModel receitasMod = new ReceitasModel(rs.getString("NomeReceita"), rs.getBoolean("Ovo"), rs.getBoolean("Farinha"), rs.getBoolean("Fermento"), rs.getBoolean("Leite"), rs.getBoolean("Acucar"), rs.getBoolean("Cacau"), rs.getBoolean("Cenoura"), rs.getBoolean("Morango"), rs.getBoolean("Abacaxi"), rs.getBoolean("Chantilly"));

                System.out.println("Nome = " + receitasMod.getNomeReceita());
                System.out.println("Tem ovo? = " + receitasMod.getOvo());
                System.out.println("Tem farinha? = " + receitasMod.getFarinha());
                System.out.println("Tem fermento? = " + receitasMod.getFermento());
                System.out.println("Tem leite? = " + receitasMod.getLeite());
                System.out.println("Tem açúcar? = " + receitasMod.getAcucar());
                System.out.println("Tem cacau? = " + receitasMod.getCacau());
                System.out.println("Tem cenoura? = " + receitasMod.getCenoura());
                System.out.println("Tem morango? = " + receitasMod.getMorango());
                System.out.println("Tem abacaxi? = " + receitasMod.getAbacaxi());
                System.out.println("Tem chantilly? = " + receitasMod.getChantilly());
                System.out.println("--------------------------------");

                receitasM.add(receitasMod);
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
        return receitasM;
    }
}
