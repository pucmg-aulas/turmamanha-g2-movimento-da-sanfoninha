package com.xulambs.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.UUID;

public abstract class Vaga {
    private UUID id;
    private int fila;
    private int numero;
    private String tipoVaga;
    private double preco;
    private boolean ocupada;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Veiculo veiculo;


    public Vaga(int fila, int numero, String tipoVaga, double preco) {
        this.id = UUID.randomUUID();
        this.fila = fila;
        this.numero = numero;
        this.tipoVaga = tipoVaga;
        this.preco = preco;
        this.ocupada = false;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public String getTipoVaga() { return tipoVaga; }
    public double getPreco() { return preco; }
    public boolean isOcupada() { return ocupada; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSaida() { return horaSaida; }
    public Veiculo getVeiculo() { return veiculo;}
    public String getIdentificacao() { return "Fila " + fila + ", Vaga " + numero + " (" + tipoVaga + ")"; }

    public void estacionar(Veiculo veiculo) {
        if (ocupada) {
            throw new Estacionamento.VagaOcupadaException("Vaga já ocupada.");
        }
        this.veiculo = veiculo;
        this.ocupada = true;
        this.horaEntrada = LocalDateTime.now();
    }

    public double sair() {
        if (!ocupada) {
            throw new Estacionamento.VagaInvalidaException("Vaga já está livre.");
        }
        this.horaSaida = LocalDateTime.now();
        double valor = calcularValor();
        this.ocupada = false;
        this.veiculo = null;
        return valor;
    }

    public abstract double calcularValor(); // Método abstrato para cálculo do valor, considerando descontos/acréscimos

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
}