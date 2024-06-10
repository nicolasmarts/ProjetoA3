package Principal;

import View.FRMMenu;
import DAO.conexao;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
    try {
        if (testarConexao()) {
            System.out.println("Conexão com o banco de dados bem-sucedida!");
            Connection connection = conexao.getConnection();
            FRMMenu menu = new FRMMenu();
            menu.setConnection(connection);
            menu.setVisible(true);
        } else {
            System.out.println("Falha na conexão com o banco de dados!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static boolean testarConexao() {
        try {
            Connection connection = conexao.getConnection();
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
