package DAO;

import Model.Amigo;
import Model.Emprestimo;
import Model.Ferramenta;
import Model.Historico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {

    private Connection connection;

    public HistoricoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addHistorico(Historico historico) throws SQLException {
        String sql = "INSERT INTO Historico (amigo_id, ferramenta_id, emprestimo_id, dataEfetivaDevolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, historico.getAmigo().getId());
            stmt.setInt(2, historico.getFerramenta().getId());
            stmt.setInt(3, historico.getEmprestimo().getId());
            stmt.setDate(4, new Date(historico.getDataEfetivaDevolucao().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Historico> getAllHistoricos() throws SQLException {
        List<Historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM Historico";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Historico historico = new Historico();
                historico.setId(rs.getInt("id"));
                
                // Aqui você precisaria recuperar e instanciar os objetos Amigo, Ferramenta e Emprestimo com base nos seus IDs
                Amigo amigo = new Amigo(); // Este seria recuperado do banco usando o AmigoDAO
                amigo.setId(rs.getInt("amigo_id"));
                historico.setAmigo(amigo);

                Ferramenta ferramenta = new Ferramenta(); // Este seria recuperado do banco usando o FerramentaDAO
                ferramenta.setId(rs.getInt("ferramenta_id"));
                historico.setFerramenta(ferramenta);

                Emprestimo emprestimo = new Emprestimo(); // Este seria recuperado do banco usando o EmprestimoDAO
                emprestimo.setId(rs.getInt("emprestimo_id"));
                historico.setEmprestimo(emprestimo);

                historico.setDataEfetivaDevolucao(rs.getDate("dataEfetivaDevolucao"));
                historicos.add(historico);
            }
        }
        return historicos;
    }

    public Historico getHistoricoById(int id) throws SQLException {
        String sql = "SELECT * FROM Historico WHERE id = ?";
        Historico historico = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    historico = new Historico();
                    historico.setId(rs.getInt("id"));

                    // Aqui você precisaria recuperar e instanciar os objetos Amigo, Ferramenta e Emprestimo com base nos seus IDs
                    Amigo amigo = new Amigo(); // Este seria recuperado do banco usando o AmigoDAO
                    amigo.setId(rs.getInt("amigo_id"));
                    historico.setAmigo(amigo);

                    Ferramenta ferramenta = new Ferramenta(); // Este seria recuperado do banco usando o FerramentaDAO
                    ferramenta.setId(rs.getInt("ferramenta_id"));
                    historico.setFerramenta(ferramenta);

                    Emprestimo emprestimo = new Emprestimo(); // Este seria recuperado do banco usando o EmprestimoDAO
                    emprestimo.setId(rs.getInt("emprestimo_id"));
                    historico.setEmprestimo(emprestimo);

                    historico.setDataEfetivaDevolucao(rs.getDate("dataEfetivaDevolucao"));
                }
            }
        }
        return historico;
    }

    public void updateHistorico(Historico historico) throws SQLException {
        String sql = "UPDATE Historico SET amigo_id = ?, ferramenta_id = ?, emprestimo_id = ?, dataEfetivaDevolucao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, historico.getAmigo().getId());
            stmt.setInt(2, historico.getFerramenta().getId());
            stmt.setInt(3, historico.getEmprestimo().getId());
            stmt.setDate(4, new Date(historico.getDataEfetivaDevolucao().getTime()));
            stmt.setInt(5, historico.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteHistorico(int id) throws SQLException {
        String sql = "DELETE FROM Historico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
