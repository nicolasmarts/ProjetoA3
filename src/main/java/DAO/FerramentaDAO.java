package DAO;

import Model.Ferramenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FerramentaDAO {
    private Connection connection;

    public FerramentaDAO() throws SQLException {
        this.connection = conexao.getConnection();
    }

    public void adicionarFerramenta(Ferramenta ferramenta) throws SQLException {
        String sql = "INSERT INTO Ferramenta (nome, marca) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ferramenta.getNome());
            statement.setString(2, ferramenta.getMarca());
            statement.executeUpdate();
        }
    }

    public List<Ferramenta> listarFerramentas() throws SQLException {
        List<Ferramenta> ferramentas = new ArrayList<>();
        String sql = "SELECT * FROM Ferramenta";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setId(resultSet.getInt("id"));
                ferramenta.setNome(resultSet.getString("nome"));
                ferramenta.setMarca(resultSet.getString("marca"));
                ferramentas.add(ferramenta);
            }
        }
        return ferramentas;
    }

    public void atualizarFerramenta(Ferramenta ferramenta) throws SQLException {
        String sql = "UPDATE Ferramenta SET nome=?, marca=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ferramenta.getNome());
            statement.setString(2, ferramenta.getMarca());
            statement.setInt(3, ferramenta.getId());
            statement.executeUpdate();
        }
    }

    public void removerFerramenta(int id) throws SQLException {
        String sql = "DELETE FROM Ferramenta WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
