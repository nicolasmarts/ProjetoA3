package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    
    private Connection connection;

    public EmprestimoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO Emprestimo (dataPrevistaDevolucao, dataRetirada) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new Date(emprestimo.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(2, new Date(emprestimo.getDataRetirada().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Emprestimo> getAllEmprestimos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimo";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataPrevistaDevolucao(rs.getDate("dataPrevistaDevolucao"));
                emprestimo.setDataRetirada(rs.getDate("dataRetirada"));
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo getEmprestimoById(int id) throws SQLException {
        String sql = "SELECT * FROM Emprestimo WHERE id = ?";
        Emprestimo emprestimo = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    emprestimo = new Emprestimo();
                    emprestimo.setId(rs.getInt("id"));
                    emprestimo.setDataPrevistaDevolucao(rs.getDate("dataPrevistaDevolucao"));
                    emprestimo.setDataRetirada(rs.getDate("dataRetirada"));
                }
            }
        }
        return emprestimo;
    }

    public void updateEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE Emprestimo SET dataPrevistaDevolucao = ?, dataRetirada = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new Date(emprestimo.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(2, new Date(emprestimo.getDataRetirada().getTime()));
            stmt.setInt(3, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmprestimo(int id) throws SQLException {
        String sql = "DELETE FROM Emprestimo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

