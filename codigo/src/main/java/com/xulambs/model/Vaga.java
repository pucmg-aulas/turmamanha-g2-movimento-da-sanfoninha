package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vaga {
    private boolean disponivel;
    private Veiculo veiculo;
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    private Cliente cliente;
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private int fila;
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Vaga(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.disponivel = true;
    }

    public boolean estacionar(Veiculo veiculo, Cliente cliente) {
        if (disponivel) {
            this.veiculo = veiculo;
            this.cliente = cliente;
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean sair() {
        if (!disponivel) {
            this.veiculo = null;
            this.cliente = null;
            disponivel = true;
            return true;
        }
        return false;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}