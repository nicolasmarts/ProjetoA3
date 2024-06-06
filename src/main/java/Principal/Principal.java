package Principal;

import View.FRMMenu;
import DAO.conexao;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
    try {
        // Testa a conexão com o banco de dados
        if (testarConexao()) {
            System.out.println("Conexão com o banco de dados bem-sucedida!");

            // Obtém a conexão do método getConnection da classe conexao
            Connection connection = conexao.getConnection();

            // Cria uma instância do FRMMenu passando a conexão como parâmetro
            FRMMenu menu = new FRMMenu();
            menu.setConnection(connection);

            // Exibe o FRMMenu
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
            // Tenta obter a conexão
            Connection connection = conexao.getConnection();
            
            // Verifica se a conexão não é nula e não está fechada
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
