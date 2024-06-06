package DAO;

import Model.HistoricoPersonalizado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoPersonalizadoDAO {
    private Connection connection;

    public HistoricoPersonalizadoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarHistoricoPersonalizado(HistoricoPersonalizado historico) throws SQLException {
        String sql = "INSERT INTO HistoricoPersonalizado (nomeAmigo, nomeFerramenta, marcaFerramenta, idEmprestimo, dataEmprestimo, dataEntregaPrevista, dataEntregaEfetiva) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, historico.getNomeAmigo());
            statement.setString(2, historico.getNomeFerramenta());
            statement.setString(3, historico.getMarcaFerramenta());
            statement.setInt(4, historico.getIdEmprestimo());
            statement.setDate(5, new java.sql.Date(historico.getDataEmprestimo().getTime()));
            statement.setDate(6, new java.sql.Date(historico.getDataEntregaPrevista().getTime()));
            statement.setDate(7, new java.sql.Date(historico.getDataEntregaEfetiva().getTime()));
            statement.executeUpdate();
        }
    }

    public List<HistoricoPersonalizado> listarHistoricoPersonalizado() throws SQLException {
        List<HistoricoPersonalizado> historicos = new ArrayList<>();
        String sql = "SELECT * FROM HistoricoPersonalizado";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                HistoricoPersonalizado historico = new HistoricoPersonalizado();
                historico.setIdHistorico(resultSet.getInt("idHistorico"));
                historico.setNomeAmigo(resultSet.getString("nomeAmigo"));
                historico.setNomeFerramenta(resultSet.getString("nomeFerramenta"));
                historico.setMarcaFerramenta(resultSet.getString("marcaFerramenta"));
                historico.setIdEmprestimo(resultSet.getInt("idEmprestimo"));
                historico.setDataEmprestimo(resultSet.getDate("dataEmprestimo"));
                historico.setDataEntregaPrevista(resultSet.getDate("dataEntregaPrevista"));
                historico.setDataEntregaEfetiva(resultSet.getDate("dataEntregaEfetiva"));
                historicos.add(historico);
            }
        }
        return historicos;
    }

    public void atualizarHistoricoPersonalizado(HistoricoPersonalizado historico) throws SQLException {
        String sql = "UPDATE HistoricoPersonalizado SET nomeAmigo=?, nomeFerramenta=?, marcaFerramenta=?, idEmprestimo=?, dataEmprestimo=?, dataEntregaPrevista=?, dataEntregaEfetiva=? WHERE idHistorico=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, historico.getNomeAmigo());
            statement.setString(2, historico.getNomeFerramenta());
            statement.setString(3, historico.getMarcaFerramenta());
            statement.setInt(4, historico.getIdEmprestimo());
            statement.setDate(5, new java.sql.Date(historico.getDataEmprestimo().getTime()));
            statement.setDate(6, new java.sql.Date(historico.getDataEntregaPrevista().getTime()));
            statement.setDate(7, new java.sql.Date(historico.getDataEntregaEfetiva().getTime()));
            statement.setInt(8, historico.getIdHistorico());
            statement.executeUpdate();
        }
    }

    public void removerHistoricoPersonalizado(int idHistorico) throws SQLException {
        String sql = "DELETE FROM HistoricoPersonalizado WHERE idHistorico=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idHistorico);
            statement.executeUpdate();
        }
    }
}

