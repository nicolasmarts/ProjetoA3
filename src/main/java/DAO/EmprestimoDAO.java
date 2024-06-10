package DAO;

import Model.Emprestimo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class EmprestimoDAO {

    public ArrayList<Emprestimo> minhaLista = new ArrayList<>();

    public ArrayList<Emprestimo> getListaEmprestimos() {
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try (Connection conn = getConexao();
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("SELECT e.idEmprestimo, e.dataPrevistaDevolucao, e.dataRetirada, " + 
                        "a.nome AS nomeAmigo, a.telefone, f.nome AS nomeFerramenta, e.idAmigo, e.idFerramenta " + 
                        "FROM Emprestimo e " + 
                        "JOIN Amigo a ON e.idAmigo = a.idAmigo " + 
                        "JOIN Ferramenta f ON e.idFerramenta = f.idFerramenta")) {
            while (res.next()) {
                int id = res.getInt("idEmprestimo");
                Date dataPrevistaDevolucao = res.getDate("dataPrevistaDevolucao");
                Date dataRetirada = res.getDate("dataRetirada");
                String nomeAmigo = res.getString("nomeAmigo");
                String telefone = res.getString("telefone");
                String nomeFerramenta = res.getString("nomeFerramenta");
                int idAmigo = res.getInt("idAmigo");
                int idFerramenta = res.getInt("idFerramenta");
                Emprestimo emprestimo = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada, nomeAmigo, telefone, nomeFerramenta);
                emprestimo.setIdAmigo(idAmigo);
                emprestimo.setIdFerramenta(idFerramenta);
                emprestimos.add(emprestimo);
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return emprestimos;
    }


    public void setMinhaLista(ArrayList<Emprestimo> minhaLista) {
        this.minhaLista = minhaLista;
    }

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(idEmprestimo) id FROM Emprestimo");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

    public boolean insertEmprestimoBD(Emprestimo objeto, String nomeAmigo, String nomeFerramenta) {
        String sql = "INSERT INTO Emprestimo(dataPrevistaDevolucao, dataRetirada, idAmigo, idFerramenta) VALUES(?, ?, ?, ?)";
        try {
            int idAmigo = getIdAmigoPorNome(nomeAmigo);
            int idFerramenta = getIdFerramentaPorNome(nomeFerramenta);
            PreparedStatement stmt = getConexao().prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(objeto.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(2, new java.sql.Date(objeto.getDataRetirada().getTime()));
            stmt.setInt(3, idAmigo);
            stmt.setInt(4, idFerramenta);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    public int getIdAmigoPorNome(String nomeAmigo) {
        String sql = "SELECT idAmigo FROM Amigo WHERE nome = ?";
        try {
            PreparedStatement stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, nomeAmigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idAmigo");
            } else {
                throw new RuntimeException("Amigo não encontrado com o nome fornecido: " + nomeAmigo);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar ID do amigo: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public int getIdFerramentaPorNome(String nomeFerramenta) {
        String sql = "SELECT idFerramenta FROM Ferramenta WHERE nome = ?";
        try {
            PreparedStatement stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, nomeFerramenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idFerramenta");
            } else {
                throw new RuntimeException("Ferramenta não encontrada com o nome fornecido: " + nomeFerramenta);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar ID da ferramenta: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public boolean deleteEmprestimoBD(int id) {
        try {
            Statement stmt = getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM Emprestimo WHERE idEmprestimo = " + id);
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    public boolean updateEmprestimoBD(Emprestimo objeto) {
        String sql = "UPDATE Emprestimo SET dataPrevistaDevolucao = ?, dataRetirada = ?, nomeAmigo = ?, telefone = ?, nomeFerramenta = ? WHERE idEmprestimo = ?";
        try {
            PreparedStatement stmt = getConexao().prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(objeto.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(2, new java.sql.Date(objeto.getDataRetirada().getTime()));
            stmt.setString(3, objeto.getNomeAmigo());
            stmt.setString(4, objeto.getTelefone());
            stmt.setString(5, objeto.getNomeFerramenta());
            stmt.setInt(6, objeto.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            return false;
        }
    }

    public Emprestimo carregaEmprestimo(int id) {
        Emprestimo objeto = new Emprestimo();
        objeto.setId(id);
        try {
            Statement stmt = getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Emprestimo WHERE idEmprestimo = " + id);
            if (res.next()) {
                objeto.setDataPrevistaDevolucao(res.getDate("dataPrevistaDevolucao"));
                objeto.setDataRetirada(res.getDate("dataRetirada"));
                objeto.setIdAmigo(res.getInt("idAmigo"));
                objeto.setIdFerramenta(res.getInt("idFerramenta"));
            }
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }

    public ArrayList<Emprestimo> getMinhaLista() {
        return minhaLista;
    }

    public static Connection getConexao() throws SQLException {
        Connection connection = null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String server = "localhost";
            String database = "emprestimo_ferramentas";
            String url = "jdbc:mysql://" + server + ":8111/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public int obterIdPorNome(String nomeFerramenta) {
        int idFerramenta = -1;
        try {
            Connection conn = getConexao();
            String sql = "SELECT idFerramenta FROM Ferramenta WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeFerramenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idFerramenta = rs.getInt("idFerramenta");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao obter o ID da ferramenta: " + ex.getMessage());
        }
        return idFerramenta;
    }
    
    public String getAmigoComMaisEmprestimos() {
        String amigoComMaisEmprestimos = null;
        try (Connection conn = getConexao();
                Statement stmt = conn.createStatement()) {
            String query = "SELECT a.nome AS amigo, COUNT(e.idEmprestimo) as emprestimos " + 
                    "FROM Emprestimo e " + 
                    "JOIN Amigo a ON e.idAmigo = a.idAmigo " + 
                    "GROUP BY a.nome " + 
                    "ORDER BY emprestimos DESC " + 
                    "LIMIT 1";
            System.out.println("Executando query: " + query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                amigoComMaisEmprestimos = rs.getString("amigo");
                System.out.println("Amigo encontrado: " + amigoComMaisEmprestimos);
            } else {
                System.out.println("Nenhum amigo encontrado na consulta");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter amigo com mais empréstimos: " + e.getMessage());
        }
        return amigoComMaisEmprestimos;
    }
}
