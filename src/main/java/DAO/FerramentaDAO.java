package DAO;

import Model.Ferramenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FerramentaDAO {
    
    public int obterIdFerramenta(String nomeFerramenta) {
    // Lógica para consultar o banco de dados e retornar o ID correspondente
    int idFerramenta = -1; // Valor padrão caso a ferramenta não seja encontrada
    // Implemente a lógica para consultar o banco de dados e obter o ID da ferramenta com o nome especificado
    // Suponha que você já tenha obtido o ID da ferramenta de alguma forma
    // Substitua a atribuição abaixo pelo código real para obter o ID da ferramenta
    // Exemplo fictício: idFerramenta = ferramentaDAO.obterIdFerramenta(nomeFerramenta);
    return idFerramenta;
}
    
    public ArrayList<Ferramenta> minhaLista = new ArrayList<>();
    
    public ArrayList<Ferramenta> getMinhaLista() {
        minhaLista.clear();

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Ferramenta");
            while (res.next()) {

                int id = res.getInt("idFerramenta");
                String nome = res.getString("nome");
                String marca = res.getString("marca");
                double custo = res.getDouble("custo");

                Ferramenta objeto = new Ferramenta(id, nome, marca, custo);

                minhaLista.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }

    public void setMinhaLista(ArrayList<Ferramenta> minhaLista) {
        this.minhaLista = minhaLista;
    }

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(idFerramenta) id FROM Ferramenta");
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

    public boolean insertFerramentaBD(Ferramenta objeto) {
        String sql = "INSERT INTO Ferramenta(idFerramenta,nome,marca,custo) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getMarca());
            stmt.setDouble(4, objeto.getCusto());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    public boolean deleteFerramentaBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM Ferramenta WHERE idFerramenta = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    public boolean updateFerramentaBD(Ferramenta objeto) {
        String sql = "UPDATE Ferramenta SET nome = ?, marca = ?, custo = ? WHERE idFerramenta = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getMarca());
            stmt.setDouble(3, objeto.getCusto());
            stmt.setInt(4, objeto.getId());

            stmt.executeUpdate();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            return false;
        }
    }

    public Ferramenta carregaFerramenta(int id) {
        Ferramenta objeto = new Ferramenta();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM Ferramenta WHERE idFerramenta = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setMarca(res.getString("marca"));
            objeto.setCusto(res.getDouble("custo"));

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }

    public int obterIdPorNome(String nomeFerramenta) {
        // SQL para selecionar o ID da ferramenta com base no nome
        String sql = "SELECT idFerramenta FROM Ferramenta WHERE nome = ?";
        
        try (
            // Obtendo a conexão com o banco de dados
            Connection conexao = getConexao();
            // Preparando a declaração SQL
            PreparedStatement stmt = conexao.prepareStatement(sql);
        ) {
            // Definindo o parâmetro nome da ferramenta na declaração preparada
            stmt.setString(1, nomeFerramenta);
            
            // Executando a consulta e armazenando o resultado
            ResultSet rs = stmt.executeQuery();
            
            // Verificando se a consulta retornou algum resultado
            if (rs.next()) {
                // Se sim, retorna o ID da ferramenta encontrado
                return rs.getInt("idFerramenta");
            } else {
                // Se não, lança uma exceção ou retorna um valor padrão, dependendo da sua lógica
                throw new RuntimeException("Ferramenta não encontrada com o nome fornecido: " + nomeFerramenta);
            }
        } catch (SQLException erro) {
            // Em caso de erro, imprime o erro e lança uma exceção
            System.out.println("Erro ao buscar ID da ferramenta: " + erro);
            throw new RuntimeException(erro);
        }
    }
}
