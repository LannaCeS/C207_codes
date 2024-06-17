package DAO;

import java.sql.*;

public class Connection {
    java.sql.Connection con; //conexão
    PreparedStatement pst; // query preparada - código em sql
    Statement st; //query - código em sql
    ResultSet rs; //resposta do BD

    String database = "lojadedoces";//nome do BD
    String user = "root";
    String password = "****"; //inserir a senha ao utilizar
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";


    public void connectToDB() {
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Conexao deu certo!");
        } catch(SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
