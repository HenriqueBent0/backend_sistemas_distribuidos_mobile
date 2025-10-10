package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Movimentacao implements Serializable {

    public enum Tipo {
        ENTRADA, SAIDA
    }

    private int idProduto;
    private LocalDate data;
    private int quantidade;
    private Tipo tipo;

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
