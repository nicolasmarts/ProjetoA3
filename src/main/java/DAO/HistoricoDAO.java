package DAO;

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

    public HistoricoDAO() throws SQLException {
        this.connection = conexao.getConnection();
    }

    public void adicionarHistorico(Historico historico) throws SQLException {
        String sql = "INSERT INTO Historico (emprestimoId, dataDevolucao) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, historico.getEmprestimoId());
            if (historico.getDataDevolucao() != null) {
                statement.setDate(2, new Date(historico.getDataDevolucao().getTime()));
            } else {
                statement.setNull(2, java.sql.Types.DATE);
            }
            statement.executeUpdate();
        }
    }

    public List<Historico> listarHistoricos() throws SQLException {
        List<Historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM Historico";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Historico historico = new Historico();
                historico.setId(resultSet.getInt("id"));
                historico.setEmprestimoId(resultSet.getInt("emprestimoId"));
                historico.setDataDevolucao(resultSet.getDate("dataDevolucao"));
                historicos.add(historico);
            }
        }
        return historicos;
    }

    public void atualizarHistorico(Historico historico) throws SQLException {
        String sql = "UPDATE Historico SET emprestimoId=?, dataDevolucao=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, historico.getEmprestimoId());
            if (historico.getDataDevolucao() != null) {
                statement.setDate(2, new Date(historico.getDataDevolucao().getTime()));
            } else {
                statement.setNull(2, java.sql.Types.DATE);
            }
            statement.setInt(3, historico.getId());
            statement.executeUpdate();
        }
    }

    public void removerHistorico(int id) throws SQLException {
        String sql = "DELETE FROM Historico WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}
