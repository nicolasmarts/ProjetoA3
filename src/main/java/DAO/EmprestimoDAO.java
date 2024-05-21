package DAO;

import Model.HistoricoPersonalizado;
import java.sql.Connection;
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

    public boolean adicionarEmprestimo(HistoricoPersonalizado emprestimo) {
        String query = "INSERT INTO tabela_emprestimo (nomeAmigo, nomeFerramenta, marcaFerramenta, idEmprestimo, dataEmprestimo, dataEntregaPrevista, dataEntregaEfetiva) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, emprestimo.getNomeAmigo());
            stmt.setString(2, emprestimo.getNomeFerramenta());
            stmt.setString(3, emprestimo.getMarcaFerramenta());
            stmt.setInt(4, emprestimo.getIdEmprestimo());
            stmt.setDate(5, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(6, new java.sql.Date(emprestimo.getDataEntregaPrevista().getTime()));
            stmt.setDate(7, new java.sql.Date(emprestimo.getDataEntregaEfetiva().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<HistoricoPersonalizado> listarEmprestimos() {
        List<HistoricoPersonalizado> emprestimos = new ArrayList<>();
        String query = "SELECT * FROM tabela_emprestimo";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HistoricoPersonalizado emprestimo = new HistoricoPersonalizado(
                        rs.getString("nomeAmigo"),
                        rs.getString("nomeFerramenta"),
                        rs.getString("marcaFerramenta"),
                        rs.getInt("idEmprestimo"),
                        rs.getDate("dataEmprestimo"),
                        rs.getDate("dataEntregaPrevista"),
                        rs.getDate("dataEntregaEfetiva")
                );
                emprestimos.add(emprestimo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return emprestimos;
    }
}