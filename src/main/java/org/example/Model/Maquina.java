package org.example.Model;

public class Maquina {
    private int id;
    private String nome;
    private String setor;
    private String status;

    public Maquina(String nome, String setor, String status) {
        this.nome = nome;
        this.setor = setor;
        this.status = status;
    }

    public Maquina(int id, String nome, String setor, String status) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nMÁQUINA:" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nSetor: " + setor +
                "\nStatus: " + status;
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

    public String getSetor() {
        return setor;
    }

    public String getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
