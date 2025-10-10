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
}
