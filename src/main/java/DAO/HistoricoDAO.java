package DAO;

import Model.Historico;
import Model.Amigo;
import Model.Ferramenta;
import Model.Emprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoricoDAO {
    private Connection connection;

    public HistoricoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean adicionarHistorico(Historico historico) {
        String query = "INSERT INTO tabela_historico (id_amigo, id_ferramenta, id_emprestimo, data_efetiva_devolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(2, historico.getFerramenta().getId());
            stmt.setInt(3, historico.getEmprestimo().getId());
            stmt.setDate(4, new java.sql.Date(historico.getDataEfetivaDevolucao().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Historico> listarHistorico() {
        List<Historico> historicos = new ArrayList<>();
        String query = "SELECT * FROM tabela_historico";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Amigo amigo = buscarAmigoPorId(rs.getInt("id_amigo"));
                Ferramenta ferramenta = buscarFerramentaPorId(rs.getInt("id_ferramenta"));
                Emprestimo emprestimo = buscarEmprestimoPorId(rs.getInt("id_emprestimo"));
                Date dataEfetivaDevolucao = rs.getDate("data_efetiva_devolucao");
                Historico historico = new Historico(id, amigo, ferramenta, emprestimo, dataEfetivaDevolucao);
                historicos.add(historico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return historicos;
    }

    private Amigo buscarAmigoPorId(int id) {
        // Implemente a lógica para buscar o amigo pelo ID no banco de dados
        // e retornar o objeto Amigo correspondente
        return null;
    }

    private Ferramenta buscarFerramentaPorId(int id) {
        // Implemente a lógica para buscar a ferramenta pelo ID no banco de dados
        // e retornar o objeto Ferramenta correspondente
        return null;
    }

    private Emprestimo buscarEmprestimoPorId(int id) {
        // Implemente a lógica para buscar o empréstimo pelo ID no banco de dados
        // e retornar o objeto Emprestimo correspondente
        return null;
    }
}

