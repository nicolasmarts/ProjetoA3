package Model;

import DAO.EmprestimoDAO;
import java.util.Date;
import java.util.ArrayList;

public class Emprestimo {
    private int id;
    private Date dataPrevistaDevolucao;
    private Date dataRetirada;
    private EmprestimoDAO DAO;
    
    public Emprestimo() {
        this.DAO = new EmprestimoDAO();
    }
    
    public Emprestimo(int id, Date dataPrevistaDevolucao, Date dataRetirada) {
        this();
        this.id = id;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
    }
    
    public Emprestimo(Date dataPrevistaDevolucao, Date dataRetirada) {
        this();
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
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
    
    public ArrayList<Emprestimo> getMinhaLista() {
        return DAO.getMinhaLista();
    }

    public boolean insertEmprestimoBD(Date dataPrevistaDevolucao, Date dataRetirada){
        int id = this.maiorID() + 1;
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada);
        return DAO.insertEmprestimoBD(objeto);
    }

    public boolean deleteEmprestimoBD(int id) {
        return DAO.deleteEmprestimoBD(id);
    }

    public boolean updateEmprestimoBD(int id, Date dataPrevistaDevolucao, Date dataRetirada) {
        Emprestimo objeto = new Emprestimo(id, dataPrevistaDevolucao, dataRetirada);
        return DAO.updateEmprestimoBD(objeto);
    }

    public Ferramenta carregaEmprestimo(int id) {
        return DAO.carregaEmprestimo(id);
    }

    public int maiorID(){
        return DAO.maiorID();
    }
}