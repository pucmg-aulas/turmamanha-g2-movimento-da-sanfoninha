package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.xulambs.model.Cliente;

//classe abstrata
public class Vaga {
    private int fila;
    private int numero;
    private boolean disponivel;
    private Veiculo veiculo;
    private Cliente cliente;
    private double ValorDesconto;

    public Vaga(int fila, int numero) {
    this.fila = fila;
    this.numero = numero;
    this.disponivel = true;
    }

    public int getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
