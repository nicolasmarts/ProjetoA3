package Model;

import DAO.FerramentaDAO;
import java.util.ArrayList;

public class Ferramenta {
    private int id;
    private String nome;
    private String marca;
    private double custo;
    private FerramentaDAO DAO;
    
    public Ferramenta(){
        this.DAO = new FerramentaDAO();
    }
    
    public Ferramenta(int id, String nome, String marca, double custo) {
        this();
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.custo = custo;
    }
    
    public Ferramenta(String nome, String marca, double custo) {
        this();
        this.nome = nome;
        this.marca = marca;
        this.custo = custo;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public double getCusto() {
        return custo;
    }
    
    public void setCusto(double custo) {
        this.custo = custo;
    }
    
    public ArrayList<Ferramenta> getMinhaLista() {
        return DAO.getMinhaLista();
    }

    public boolean insertFerramentaBD(String nome, String marca, Double custo){
        int id = this.maiorID() + 1;
        Ferramenta objeto = new Ferramenta(id, nome, marca, custo);
        return DAO.insertFerramentaBD(objeto);
    }

    public boolean deleteFerramentaBD(int id) {
        return DAO.deleteFerramentaBD(id);
    }

    public boolean updateFerramentaBD(int id, String nome, String marca, Double custo) {
        Ferramenta objeto = new Ferramenta(id, nome, marca, custo);
        return DAO.updateFerramentaBD(objeto);
    }

    public Ferramenta carregaFerramenta(int id) {
        return DAO.carregaFerramenta(id);
    }

    public int maiorID(){
        return DAO.maiorID();
    }
}
