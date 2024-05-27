package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Amigo;

public class AmigoDAO {
    private Connection connection;

    public AmigoDAO() throws SQLException {
        this.connection = conexao.getConnection();
    }

    public void adicionarAmigo(Amigo amigo) throws SQLException {
        String sql = "INSERT INTO amigos (nome, telefone, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, amigo.getNome());
            statement.setString(2, amigo.getTelefone());
            statement.setString(3, amigo.getEmail());
            statement.executeUpdate();
        }
    }

    public List<Amigo> listarAmigos() throws SQLException {
        List<Amigo> amigos = new ArrayList<>();
        String sql = "SELECT * FROM amigos";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                Amigo amigo = new Amigo(nome, telefone, email);
                amigos.add(amigo);
            }
        }
        return amigos;
    }

    public void atualizarAmigo(Amigo amigo) throws SQLException {
        String sql = "UPDATE amigos SET telefone=?, email=? WHERE nome=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, amigo.getTelefone());
            statement.setString(2, amigo.getEmail());
            statement.setString(3, amigo.getNome());
            statement.executeUpdate();
        }
    }

    public void removerAmigo(String nome) throws SQLException {
        String sql = "DELETE FROM amigos WHERE nome=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.executeUpdate();
        }
    }
}

