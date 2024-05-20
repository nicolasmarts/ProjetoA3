package Model;

import java.util.Date;

public class Historico {
    private int id;
    private Amigo amigo;
    private Ferramenta ferramenta;
    private Emprestimo emprestimo;
    private Date dataEfetivaDevolucao;
    
    public Historico() {
    }
    
    public Historico(int id, Amigo amigo, Ferramenta ferramenta, Emprestimo emprestimo, Date dataEfetivaDevolucao) {
        this.id = id;
        this.amigo = amigo;
        this.ferramenta = ferramenta;
        this.emprestimo = emprestimo;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
    
    public Historico(Amigo amigo, Ferramenta ferramenta, Emprestimo emprestimo, Date dataEfetivaDevolucao) {
        this.amigo = amigo;
        this.ferramenta = ferramenta;
        this.emprestimo = emprestimo;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Amigo getAmigo() {
        return amigo;
    }
    
    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }
    
    public Ferramenta getFerramenta() {
        return ferramenta;
    }
    
    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }
    
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }
    
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    public Date getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }
    
    public void setDataEfetivaDevolucao(Date dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
}
