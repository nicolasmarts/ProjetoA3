package Model;

import java.util.Date;

public class Historico {
    private int id;
    private int emprestimoId;
    private Date dataDevolucao;

    public Historico() {}

    public Historico(int id, int emprestimoId, Date dataDevolucao) {
        this.id = id;
        this.emprestimoId = emprestimoId;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(int emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
