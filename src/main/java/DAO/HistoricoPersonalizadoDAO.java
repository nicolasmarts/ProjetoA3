package DAO;

import Model.HistoricoPersonalizado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoricoPersonalizadoDAO {
    private Connection connection;

    public HistoricoPersonalizadoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean adicionarHistoricoPersonalizado(HistoricoPersonalizado historico) {
        String query = "INSERT INTO tabela_historico_personalizado (nome_amigo, nome_ferramenta, marca_ferramenta, id_emprestimo, data_emprestimo, data_entrega_prevista, data_entrega_efetiva) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, historico.getNomeAmigo());
            stmt.setString(2, historico.getNomeFerramenta());
            stmt.setString(3, historico.getMarcaFerramenta());
            stmt.setInt(4, historico.getIdEmprestimo());
            stmt.setDate(5, new java.sql.Date(historico.getDataEmprestimo().getTime()));
            stmt.setDate(6, new java.sql.Date(historico.getDataEntregaPrevista().getTime()));
            stmt.setDate(7, new java.sql.Date(historico.getDataEntregaEfetiva().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<HistoricoPersonalizado> listarHistoricoPersonalizado() {
        List<HistoricoPersonalizado> historicos = new ArrayList<>();
        String query = "SELECT * FROM tabela_historico_personalizado";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idHistorico = rs.getInt("id_historico");
                String nomeAmigo = rs.getString("nome_amigo");
                String nomeFerramenta = rs.getString("nome_ferramenta");
                String marcaFerramenta = rs.getString("marca_ferramenta");
                int idEmprestimo = rs.getInt("id_emprestimo");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataEntregaPrevista = rs.getDate("data_entrega_prevista");
                Date dataEntregaEfetiva = rs.getDate("data_entrega_efetiva");
                HistoricoPersonalizado historico = new HistoricoPersonalizado(idHistorico, nomeAmigo, nomeFerramenta, marcaFerramenta, idEmprestimo, dataEmprestimo, dataEntregaPrevista, dataEntregaEfetiva);
                historicos.add(historico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return historicos;
    }
}

