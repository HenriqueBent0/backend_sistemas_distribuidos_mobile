package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa uma movimentação de entrada ou saída de um produto no estoque.
 * Armazena o ID do produto, a data, a quantidade movimentada e o tipo (ENTRADA
 * ou SAIDA).
 */
public class Movimentacao implements Serializable {

    /**
     * Tipos possíveis de movimentação. ENTRADA: aumenta o estoque. SAIDA: reduz
     * o estoque.
     */
    public enum Tipo {
        ENTRADA, SAIDA
    }

    private int idProduto;   // ID do produto movimentado
    private LocalDate data;  // Data da movimentação
    private int quantidade;  // Quantidade movimentada
    private Tipo tipo;       // Tipo da movimentação

    /**
     * Construtor da movimentação.
     */
    public Movimentacao(int idProduto, LocalDate data, int quantidade, Tipo tipo) {
        this.idProduto = idProduto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
