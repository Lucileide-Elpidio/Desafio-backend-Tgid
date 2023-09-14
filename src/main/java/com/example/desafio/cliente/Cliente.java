package com.example.desafio.cliente;

/**
 * A classe Cliente representa um cliente no sistema.
 */
public class Cliente {
    private String cpf;
    private double saldo;

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O CPF do cliente a ser definido.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o saldo do cliente.
     *
     * @return O saldo do cliente.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo do cliente.
     *
     * @param saldo O saldo do cliente a ser definido.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
