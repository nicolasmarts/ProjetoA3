package Model;

import java.util.Date;

public class Emprestimo {
    private int id;
    private Date dataPrevistaDevolucao;
    private Date dataRetirada;
    
    public Emprestimo() {
    }
    
    public Emprestimo(int id, Date dataPrevistaDevolucao, Date dataRetirada) {
        this.id = id;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRetirada = dataRetirada;
    }
    
    public Emprestimo(Date dataPrevistaDevolucao, Date dataRetirada) {
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
}