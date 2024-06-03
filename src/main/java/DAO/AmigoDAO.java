package DAO;

import java.util.ArrayList;
import Model.Amigo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmigoDAO {
    
    public ArrayList<Amigo> minhaLista = new ArrayList<>();
    
    public ArrayList<Amigo> getMinhaLista() {
        minhaLista.clear();
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_alunos");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");
                String email = res.getString("email");

                Amigo objeto = new Amigo(id, nome, telefone, email);

                minhaLista.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }
    
    public void setMinhaLista(ArrayList<Amigo> minhaLista) {
        this.minhaLista = minhaLista;
    }
    
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_alunos");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }
    public Connection getConexao() throws SQLException{
        
        Connection connection = null;
        
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            String server = "localhost";
            String database = "emprestimo_ferramentas";
            String url = "jdbc:mysql://localhost:8111/emprestimo_ferramentas" + server + ":8111/"
                    + database + "?useTimezone=true&serverTimezone=UTC";
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
    
    public boolean insertAmigoBD(Amigo objeto) {
        String sql = "INSERT INTO tb_amigos(id,nome,telefone,email) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());
            stmt.setString(4, objeto.getEmail());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }
    
    public boolean deleteAmigoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_amigos WHERE id = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }
    
    public boolean updateAmigoBD(Amigo objeto) {

        String sql = "UPDATE tb_amigos set nome = ? ,telefone = ? ,email = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());
            stmt.setString(4, objeto.getEmail());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }
    
    public Amigo carregaAmigo(int id) {
        Amigo objeto = new Amigo();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigos WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setTelefone(res.getString("telefone"));
            objeto.setEmail(res.getString("email"));

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }
}