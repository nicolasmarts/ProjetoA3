package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FerramentaDAO {

    private Connection connection;

    public FerramentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFerramenta(Ferramenta ferramenta) throws SQLException {
        String sql = "INSERT INTO Ferramenta (nome, marca, custo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ferramenta.getNome());
            stmt.setString(2, ferramenta.getMarca());
            stmt.setDouble(3, ferramenta.getCusto());
            stmt.executeUpdate();
        }
    }

    public List<Ferramenta> getAllFerramentas() throws SQLException {
        List<Ferramenta> ferramentas = new ArrayList<>();
        String sql = "SELECT * FROM Ferramenta";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setCusto(rs.getDouble("custo"));
                ferramentas.add(ferramenta);
            }
        }
        return ferramentas;
    }

    public Ferramenta getFerramentaById(int id) throws SQLException {
        String sql = "SELECT * FROM Ferramenta WHERE id = ?";
        Ferramenta ferramenta = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ferramenta = new Ferramenta();
                    ferramenta.setId(rs.getInt("id"));
                    ferramenta.setNome(rs.getString("nome"));
                    ferramenta.setMarca(rs.getString("marca"));
                    ferramenta.setCusto(rs.getDouble("custo"));
                }
            }
        }
        return ferramenta;
    }

    public void updateFerramenta(Ferramenta ferramenta) throws SQLException {
        String sql = "UPDATE Ferramenta SET nome = ?, marca = ?, custo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ferramenta.getNome());
            stmt.setString(2, ferramenta.getMarca());
            stmt.setDouble(3, ferramenta.getCusto());
            stmt.setInt(4, ferramenta.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteFerramenta(int id) throws SQLException {
        String sql = "DELETE FROM Ferramenta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
