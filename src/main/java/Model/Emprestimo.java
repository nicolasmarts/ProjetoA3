package Model;

import DAO.EmprestimoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Emprestimo {
    private int id;
    private Date dataPrevistaDevolucao;
    private Date dataRetirada;
    private String nomeAmigo;
    private String telefone;
    private String nomeFerramenta;
    private EmprestimoDAO DAO;
    private int idAmigo;
    private int idFerramenta;

    public Emprestimo(){
        this.DAO = new EmprestimoDAO(); 
    }

    public Emprestimo(int id, Date dataPrevistaDevolucao, Date dataRetirada, String nomeAmigo, String telefone, String nomeFerramenta) {
        this();
        this.id = id;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
        this.nomeAmigo = nomeAmigo;
        this.telefone = telefone;
        this.nomeFerramenta = nomeFerramenta;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getNomeAmigo() {
        return nomeAmigo;
    }

    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeFerramenta() {
        return nomeFerramenta;
    }

    public void setNomeFerramenta(String nomeFerramenta) {
        this.nomeFerramenta = nomeFerramenta;
    }

    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    public int getIdFerramenta() {
        return idFerramenta;
    }

    public void setIdFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

    public ArrayList<Emprestimo> getMinhaLista() {
        return DAO.getListaEmprestimos();
    }

    public boolean insertEmprestimoBD(String nomeAmigo, String telefone, String nomeFerramenta, Date dataRetirada, Date dataPrevistaDevolucao){
        int id = this.maiorID() + 1;
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada, nomeAmigo, telefone, nomeFerramenta);
        return DAO.insertEmprestimoBD(objeto, nomeAmigo, nomeFerramenta); // Chama a versão correta do método
    }

    public boolean deleteEmprestimoBD(int id) {
        return DAO.deleteEmprestimoBD(id);
    }

    public boolean updateEmprestimoBD(int id, String nomeAmigo, String telefone, String nomeFerramenta, Date dataRetirada, Date dataPrevistaDevolucao) {
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada, nomeAmigo, telefone, nomeFerramenta);
        return DAO.updateEmprestimoBD(objeto);
    }

    public Emprestimo carregaEmprestimo(int id) {
        return DAO.carregaEmprestimo(id);
    }

    public int maiorID(){
        return DAO.maiorID();
    }
    
    public Connection getConexao() {
    Connection connection = null;
    try {
        String url = "jdbc:mysql://localhost:8111/emprestimo_ferramentas";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão com o banco de dados bem-sucedida!");
    } catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    }
    return connection;
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
