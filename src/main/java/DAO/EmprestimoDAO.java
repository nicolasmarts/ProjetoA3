package DAO;

import Model.Emprestimo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmprestimoDAO {

    public ArrayList<Emprestimo> minhaLista = new ArrayList<>();
    
    public ArrayList<Emprestimo> getMinhaLista() {
        minhaLista.clear();
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Emprestimo");
            while (res.next()) {
                int id = res.getInt("idEmprestimo");
                java.sql.Date dataPrevistaDevolucao = res.getDate("dataPrevistaDevolucao");
                java.sql.Date dataRetirada = res.getDate("dataRetirada");
                int idAmigo = res.getInt("idAmigo");
                int idFerramenta = res.getInt("idFerramenta");
                String nome = res.getString("nome"); // Adicionado
                String telefone = res.getString("telefone"); // Adicionado
                String ferramenta = res.getString("ferramenta"); // Adicionado

                Emprestimo objeto = new Emprestimo(id, new java.util.Date(dataPrevistaDevolucao.getTime()), new java.util.Date(dataRetirada.getTime()), idAmigo, idFerramenta, nome, telefone, ferramenta);
                minhaLista.add(objeto);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }
    
    public Connection getConexao() throws SQLException {
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
            System.out.println("O driver não foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar...");
            return null;
        }
    }
    
    public boolean insertEmprestimoBD(Emprestimo objeto) {
        String sql = "INSERT INTO Emprestimo(idEmprestimo, dataPrevistaDevolucao, dataRetirada, idAmigo, idFerramenta, nome, telefone, ferramenta) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setDate(2, new java.sql.Date(objeto.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(3, new java.sql.Date(objeto.getDataRetirada().getTime()));
            stmt.setInt(4, objeto.getIdAmigo());
            stmt.setInt(5, objeto.getIdFerramenta());
            stmt.setString(6, objeto.getNome()); // Adicionado
            stmt.setString(7, objeto.getTelefone()); // Adicionado
            stmt.setString(8, objeto.getFerramenta()); // Adicionado

            stmt.execute();
            stmt.close();
            
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }
    
    public boolean deleteEmprestimoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM Emprestimo WHERE idEmprestimo = " + id);
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            return false;
        }
        return true;
    }
    
    public boolean updateEmprestimoBD(Emprestimo objeto) {
        String sql = "UPDATE Emprestimo SET dataPrevistaDevolucao = ?, dataRetirada = ?, idAmigo = ?, idFerramenta = ?, nome = ?, telefone = ?, ferramenta = ? WHERE idEmprestimo = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setDate(1, new java.sql.Date(objeto.getDataPrevistaDevolucao().getTime()));
            stmt.setDate(2, new java.sql.Date(objeto.getDataRetirada().getTime()));
            stmt.setInt(3, objeto.getIdAmigo());
            stmt.setInt(4, objeto.getIdFerramenta());
            stmt.setString(5, objeto.getNome());
            stmt.setString(6, objeto.getTelefone());
            stmt.setString(7, objeto.getFerramenta());
            stmt.setInt(8, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }
    
    public Emprestimo carregaEmprestimo(int id) {
        Emprestimo objeto = new Emprestimo();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Emprestimo WHERE idEmprestimo = " + id);
            res.next();

            objeto.setDataPrevistaDevolucao(res.getDate("dataPrevistaDevolucao"));
            objeto.setDataRetirada(res.getDate("dataRetirada"));
            objeto.setIdAmigo(res.getInt("idAmigo"));
            objeto.setIdFerramenta(res.getInt("idFerramenta"));
            objeto.setNome(res.getString("nome")); // Adicionado
            objeto.setTelefone(res.getString("telefone")); // Adicionado
            objeto.setFerramenta(res.getString("ferramenta")); // Adicionado

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(idEmprestimo) AS id FROM Emprestimo");
            if (res.next()) {
                maiorID = res.getInt("id");
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }
}
