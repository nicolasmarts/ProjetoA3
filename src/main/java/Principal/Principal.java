package Principal;

import DAO.conexao;
import View.FRMMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        if (testarConexao()) {
            System.out.println("Conexão com o banco de dados bem-sucedida!");
            FRMMenu objetotela = new FRMMenu();
            objetotela.setVisible(true);

            // Teste de inserção com dados fictícios
            inserirAmigo(1, "João", "123456789", "joao@example.com");
            inserirAmigo(2, "Maria", "987654321", "maria@example.com");
            inserirAmigo(3, "Pedro", "555555555", "pedro@example.com");
        } else {
            System.out.println("Falha na conexão com o banco de dados!");
        }
    }

    public static boolean testarConexao() {
        try (Connection connection = conexao.getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void inserirAmigo(int idAmigo, String nome, String telefone, String email) {
        try (Connection connection = conexao.getConnection()) {
            String sql = "INSERT INTO Amigo(idAmigo, nome, telefone, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idAmigo);
            statement.setString(2, nome);
            statement.setString(3, telefone);
            statement.setString(4, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Amigo " + nome + " inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir amigo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
