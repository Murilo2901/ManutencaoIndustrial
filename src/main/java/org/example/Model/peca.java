package org.example.Model;

public class peca {
    private int id;
    private String  nome;
    private double estoque;

    public peca(int id, String nome, double estoque) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
    }

    public peca(String nome, double estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }

    @Override
    public String toString(){
        return "\n PEÇA " +
                "\n ID" + id +
                "\n NOME " + nome +
                "\n ESTOQUE " + estoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }
}
