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
        this.DAO = new AmigoDAO(); // Inicializando o DAO no construtor padrão
    }
    
    public Amigo(String nome, String telefone, String email){
        this(); // Chamando o construtor padrão para inicializar o DAO
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Amigo(int id, String nome, String telefone, String email) {
        this(); // Chamando o construtor padrão para inicializar o DAO
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Métodos getters e setters

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
    
    // Métodos relacionados ao banco de dados

    public ArrayList<Amigo> getMinhaLista() {
        return DAO.getMinhaLista();
    }

    public boolean insertAmigoBD(String nome, String telefone, String email){
        int id = this.maiorID() + 1;
        Amigo objeto = new Amigo(id, nome, telefone, email);
        return DAO.insertAmigoBD(objeto);
    }

    public boolean deleteAmigoBD(int id) {
        return DAO.deleteAmigoBD(id);
    }

    public boolean updateAmigoBD(int id, String nome, String telefone, String email) {
        Amigo objeto = new Amigo(id, nome, telefone, email);
        return DAO.updateAmigoBD(objeto);
    }

    public Amigo carregaAmigo(int id) {
        return DAO.carregaAmigo(id);
    }

    public int maiorID(){
        return DAO.maiorID();
    }
}
