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

    public FerramentaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean adicionarFerramenta(Ferramenta ferramenta) {
        String query = "INSERT INTO tabela_ferramenta (nome, marca, custo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ferramenta.getNome());
            stmt.setString(2, ferramenta.getMarca());
            stmt.setDouble(3, ferramenta.getCusto());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Ferramenta> listarFerramentas() {
        List<Ferramenta> ferramentas = new ArrayList<>();
        String query = "SELECT * FROM tabela_ferramenta";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ferramenta ferramenta = new Ferramenta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("marca"),
                        rs.getDouble("custo")
                );
                ferramentas.add(ferramenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ferramentas;
    }
}
