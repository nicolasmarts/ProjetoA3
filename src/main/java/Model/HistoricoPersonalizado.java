package Model;

import java.util.Date;

public class HistoricoPersonalizado {
    private int idHistorico;
    private String nomeAmigo;
    private String nomeFerramenta;
    private String marcaFerramenta;
    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataEntregaPrevista;
    private Date dataEntregaEfetiva;
    
    public HistoricoPersonalizado() {
    }
    
    public HistoricoPersonalizado(int idHistorico, String nomeAmigo, String nomeFerramenta, String marcaFerramenta, int idEmprestimo, Date dataEmprestimo, Date dataEntregaPrevista, Date dataEntregaEfetiva) {
        this.idHistorico = idHistorico;
        this.nomeAmigo = nomeAmigo;
        this.nomeFerramenta = nomeFerramenta;
        this.marcaFerramenta = marcaFerramenta;
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataEntregaPrevista = dataEntregaPrevista;
        this.dataEntregaEfetiva = dataEntregaEfetiva;
    }
    
    public HistoricoPersonalizado(String nomeAmigo, String nomeFerramenta, String marcaFerramenta, int idEmprestimo, Date dataEmprestimo, Date dataEntregaPrevista, Date dataEntregaEfetiva) {
        this.nomeAmigo = nomeAmigo;
        this.nomeFerramenta = nomeFerramenta;
        this.marcaFerramenta = marcaFerramenta;
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataEntregaPrevista = dataEntregaPrevista;
        this.dataEntregaEfetiva = dataEntregaEfetiva;
    }
    
    public int getIdHistorico() {
        return idHistorico;
    }
    
    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }
    
    public String getNomeAmigo() {
        return nomeAmigo;
    }
    
    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }
    
    public String getNomeFerramenta() {
        return nomeFerramenta;
    }
    
    public void setNomeFerramenta(String nomeFerramenta) {
        this.nomeFerramenta = nomeFerramenta;
    }
    
    public String getMarcaFerramenta() {
        return marcaFerramenta;
    }
    
    public void setMarcaFerramenta(String marcaFerramenta) {
        this.marcaFerramenta = marcaFerramenta;
    }
    
    public int getIdEmprestimo() {
        return idEmprestimo;
    }
    
    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
    
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Date getDataEntregaPrevista() {
        return dataEntregaPrevista;
    }
    
    public void setDataEntregaPrevista(Date dataEntregaPrevista) {
        this.dataEntregaPrevista = dataEntregaPrevista;
    }
    
    public Date getDataEntregaEfetiva() {
        return dataEntregaEfetiva;
    }
    
    public void setDataEntregaEfetiva(Date dataEntregaEfetiva) {
        this.dataEntregaEfetiva = dataEntregaEfetiva;
    }
}
