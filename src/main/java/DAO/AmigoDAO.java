package DAO;

import Model.Amigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AmigoDAO {
    
    public int obterIdAmigo(String nomeAmigo) {
    // Lógica para consultar o banco de dados e retornar o ID correspondente
    int idAmigo = -1; // Valor padrão caso o amigo não seja encontrado
    // Implemente a lógica para consultar o banco de dados e obter o ID do amigo com o nome especificado
    // Suponha que você já tenha obtido o ID do amigo de alguma forma
    // Substitua a atribuição abaixo pelo código real para obter o ID do amigo
    // Exemplo fictício: idAmigo = amigoDAO.obterIdAmigo(nomeAmigo);
    return idAmigo;
}

    public ArrayList<Amigo> minhaLista = new ArrayList<>();

    public ArrayList<Amigo> getMinhaLista() {
        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Amigo");
            while (res.next()) {

                int id = res.getInt("idAmigo");
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
            ResultSet res = stmt.executeQuery("SELECT MAX(idAmigo) id FROM Amigo");
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

    public boolean insertAmigoBD(Amigo objeto) {
        String sql = "INSERT INTO Amigo(idAmigo,nome,telefone,email) VALUES(?,?,?,?)";
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
            stmt.executeUpdate("DELETE FROM Amigo WHERE idAmigo = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    public boolean updateAmigoBD(Amigo objeto) {
        String sql = "UPDATE Amigo SET nome = ?, telefone = ?, email = ? WHERE idAmigo = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getEmail());
            stmt.setInt(4, objeto.getId());

            stmt.executeUpdate();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            return false;
        }
    }

    public Amigo carregaAmigo(int id) {
        Amigo objeto = new Amigo();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM Amigo WHERE idAmigo = " + id);
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

    public int obterIdPorNome(String nome) {
    String sql = "SELECT idAmigo FROM Amigo WHERE nome = ?";
    int id = -1; // Valor padrão se o nome não for encontrado

    try {
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            id = rs.getInt("idAmigo");
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println("Erro ao obter o ID do amigo: " + e.getMessage());
    }

    return id;
}
}
