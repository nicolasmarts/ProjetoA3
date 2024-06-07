package Model;

import DAO.EmprestimoDAO;
import java.util.ArrayList;
import java.util.Date;

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

    // Getters and setters
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
}
