package com.example.desafio.empresa;

import java.util.List;

/**
 * A classe Empresa representa uma empresa no sistema.
 */
public class Empresa {
    private String cnpj;
    private double saldo;
    private List<TaxaSistema> taxas;

    /**
     * Obtém o CNPJ da empresa.
     *
     * @return O CNPJ da empresa.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ da empresa.
     *
     * @param cnpj O CNPJ da empresa a ser definido.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Obtém o saldo da empresa.
     *
     * @return O saldo da empresa.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo da empresa.
     *
     * @param saldo O saldo da empresa a ser definido.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtém a lista de taxas associadas à empresa.
     *
     * @return A lista de taxas associadas à empresa.
     */
    public List<TaxaSistema> getTaxas() {
        return taxas;
    }

    /**
     * Define a lista de taxas associadas à empresa.
     *
     * @param taxas A lista de taxas a ser definida.
     */
    public void setTaxas(List<TaxaSistema> taxas) {
        this.taxas = taxas;
    }
}
