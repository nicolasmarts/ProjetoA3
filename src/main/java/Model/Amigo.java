package Model;

import DAO.AmigoDAO;
import java.util.ArrayList;

public class Amigo {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private AmigoDAO DAO;
    
    public Amigo(){
    }
    
    public Amigo(String nome, String telefone, String email){
        this();
    }

    public Amigo(int id1, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.DAO = new AmigoDAO();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<Amigo> getMinhaLista() {
        return DAO.getMinhaLista();
    }
    public boolean insertAmigoBD(String nome, String telefone, String email){
        int id = this.maiorID() + 1;
        Amigo objeto = new Amigo(id, nome, telefone, email);
        DAO.insertAmigoBD(objeto);
        return true;
    }
    
    public boolean deleteAmigoBD(int id) {
        DAO.deleteAmigoBD(id);
        return true;
    }
    
    public boolean updateAmigoBD(int id, String nome, String telefone, String email) {
        Amigo objeto = new Amigo(id, nome, telefone, email);
        DAO.updateAmigoBD(objeto);
        return true;
    }
    
    public Amigo carregaAmigo(int id) {
        return DAO.carregaAmigo(id);
    }
    
    public int maiorID(){
        return DAO.maiorID();
    }
}
