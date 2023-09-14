package com.example.desafio.empresa;

/**
 * A classe TaxaSistema representa uma taxa associada a uma empresa no sistema.
 */
public class TaxaSistema {
    private String nome;
    private double valor;

    /**
     * Obtém o nome da taxa.
     *
     * @return O nome da taxa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da taxa.
     *
     * @param nome O nome da taxa a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o valor da taxa.
     *
     * @return O valor da taxa.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da taxa.
     *
     * @param valor O valor da taxa a ser definido.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
