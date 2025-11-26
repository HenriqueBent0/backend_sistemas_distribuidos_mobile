package model;

import java.io.Serializable;

/**
 * Representa um produto armazenado no estoque. Contém informações como nome,
 * preço, unidade, quantidade atual, limites mínimo/máximo e categoria.
 */
public class Produto implements Serializable {

    private int id;                 // Identificador do produto
    private String nome;            // Nome do produto
    private double precoUnitario;   // Preço por unidade
    private String unidade;         // Tipo de unidade (ex: kg, litro, un)
    private int quantidade;         // Quantidade atual em estoque
    private int quantidadeMinima;   // Estoque mínimo recomendado
    private int quantidadeMaxima;   // Estoque máximo permitido
    private String categoria;       // Categoria do produto

    /**
     * Construtor completo do produto.
     */
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

    /**
     * Atualiza a quantidade atual do produto no estoque.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
