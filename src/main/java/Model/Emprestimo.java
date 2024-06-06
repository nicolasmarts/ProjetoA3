package Model;

import DAO.EmprestimoDAO;
import java.util.Date;
import java.util.ArrayList;

public class Emprestimo {
    private int id;
    private Date dataPrevistaDevolucao;
    private Date dataRetirada;
    private int idAmigo;
    private int idFerramenta;
    private String nome; // Adicionado
    private String telefone; // Adicionado
    private String ferramenta; // Adicionado
    private EmprestimoDAO DAO;
    
    public Emprestimo() {
        this.DAO = new EmprestimoDAO();
    }
    
    public Emprestimo(int id, Date dataPrevistaDevolucao, Date dataRetirada, int idAmigo, int idFerramenta, String nome, String telefone, String ferramenta) {
        this.id = id;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
        this.idAmigo = idAmigo;
        this.idFerramenta = idFerramenta;
        this.nome = nome;
        this.telefone = telefone;
        this.ferramenta = ferramenta;
        this.DAO = new EmprestimoDAO();
    }
    
    public Emprestimo(Date dataPrevistaDevolucao, Date dataRetirada, int idAmigo, int idFerramenta, String nome, String telefone, String ferramenta) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
        this.idAmigo = idAmigo;
        this.idFerramenta = idFerramenta;
        this.nome = nome;
        this.telefone = telefone;
        this.ferramenta = ferramenta;
        this.DAO = new EmprestimoDAO();
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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    public ArrayList<Emprestimo> getMinhaLista() {
        return DAO.getMinhaLista();
    }

    public boolean insertEmprestimoBD(Date dataPrevistaDevolucao, Date dataRetirada, int idAmigo, int idFerramenta, String nome, String telefone, String ferramenta) {
        int id = this.maiorID() + 1;
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada, idAmigo, idFerramenta, nome, telefone, ferramenta);
        return DAO.insertEmprestimoBD(objeto);
    }

    public boolean deleteEmprestimoBD(int id) {
        return DAO.deleteEmprestimoBD(id);
    }

    public boolean updateEmprestimoBD(int id, Date dataPrevistaDevolucao, Date dataRetirada, int idAmigo, int idFerramenta, String nome, String telefone, String ferramenta) {
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada, idAmigo, idFerramenta, nome, telefone, ferramenta);
        return DAO.updateEmprestimoBD(objeto);
    }

    public Emprestimo carregaEmprestimo(int id) {
        return DAO.carregaEmprestimo(id);
    }

    public int maiorID(){
        return DAO.maiorID();
    }
}
