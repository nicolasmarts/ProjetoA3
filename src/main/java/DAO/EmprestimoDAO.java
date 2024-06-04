package DAO;

import Model.Emprestimo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class EmprestimoDAO {
    
    public ArrayList<Emprestimo> minhaLista = new ArrayList<>();

    public ArrayList<Emprestimo> getMinhaLista() {
        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Emprestimo");
            while (res.next()) {

                int id = res.getInt("idEmprestimo");
                Date dataPrevistaDevolucao = res.getDate("dataPrevistaDevolucao");
                Date dataRetirada = res.getDate("dataRetirada");

                Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada);

                minhaLista.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }

    public void setMinhaLista(ArrayList<Emprestimo> minhaLista) {
        this.minhaLista = minhaLista;
    }

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(idEmprestimo) id FROM Emprestimo");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

    public Connection getConexao() throws SQLException {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "emprestimo_ferramentas";
            String url = "jdbc:mysql://localhost:8111/" + database + "?useTimezone=true&serverTimezone=UTC";
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

    public boolean insertEmprestimoBD(Emprestimo objeto) {
        String sql = "INSERT INTO Emprestimo(idEmprestimo,dataPrevistaDevolucao,dataRetirada) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setDate(2, (Date) objeto.getDataPrevistaDevolucao());
            stmt.setDate(3, (Date) objeto.getDataRetirada());

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
        }
        return true;
    }

    public boolean updateEmprestimoBD(Emprestimo objeto) {
        String sql = "UPDATE Emprestimo SET dataPrevistaDevolucao = ?, dataRetirada = ? WHERE idAmigo = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setDate(1, (Date) objeto.getDataPrevistaDevolucao());
            stmt.setDate(2, (Date) objeto.getDataRetirada());
            stmt.setInt(4, objeto.getId());

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
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM Emprestimo WHERE idEmprestimo = " + id);
            res.next();

            objeto.setDataPrevistaDevolucao(res.getDate("dataPrevistaDevolucao"));
            objeto.setDataRetirada(res.getDate("DataRetirada"));

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }
}
