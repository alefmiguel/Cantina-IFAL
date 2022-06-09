package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            //MySQL connection
            return DriverManager.getConnection("jdbc:mysql://localhost/cantina_banco", "root", "");
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
           
    }

    public static void closeConnection(Connection c){
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args)  {
        Connection conexao = ConnectionFactory.getConnection();
        System.out.println("Conexao aberta!");
        ConnectionFactory.closeConnection(conexao);
    }
}