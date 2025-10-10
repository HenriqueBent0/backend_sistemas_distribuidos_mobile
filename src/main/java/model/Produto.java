package model;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private double precoUnitario;
    private String unidade;
    private int quantidade;
    private int quantidadeMinima;
    private int quantidadeMaxima;
    private String categoria;

    public Produto(int id, String nome, double precoUnitario, String unidade,
            int quantidade, int quantidadeMinima, int quantidadeMaxima,
            String categoria) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
        this.categoria = categoria;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
