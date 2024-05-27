package DAO;

import Model.Emprestimo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private Connection connection;

    public EmprestimoDAO() throws SQLException {
        this.connection = conexao.getConnection();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO Emprestimo (dataPrevistaDevolucao, dataRetirada, amigoId, ferramentaId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(emprestimo.getDataPrevistaDevolucao().getTime()));
            statement.setDate(2, new Date(emprestimo.getDataRetirada().getTime()));
            statement.executeUpdate();
        }
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimo";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(resultSet.getInt("id"));
                emprestimo.setDataPrevistaDevolucao(resultSet.getDate("dataPrevistaDevolucao"));
                emprestimo.setDataRetirada(resultSet.getDate("dataRetirada"));
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE Emprestimo SET dataPrevistaDevolucao=?, dataRetirada=?, amigoId=?, ferramentaId=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(emprestimo.getDataPrevistaDevolucao().getTime()));
            statement.setDate(2, new Date(emprestimo.getDataRetirada().getTime()));
            statement.setInt(5, emprestimo.getId());
            statement.executeUpdate();
        }
    }

    public void removerEmprestimo(int id) throws SQLException {
        String sql = "DELETE FROM Emprestimo WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
