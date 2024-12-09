package com.xulambs.model;

import java.time.Duration;
import java.time.LocalDateTime;

import main.exceptions.UsoDeVagaException;

public class UsoDeVaga {
    private static final int MINUTOS_POR_FRACAO = 15;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public UsoDeVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("Vaga não pode ser nula.");
        }
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
    }

    public Vaga getVaga() { return vaga; }
    public LocalDateTime getEntrada() { return entrada; }
    public LocalDateTime getSaida() { return saida; }
    public void setSaida(LocalDateTime saida) { this.saida = saida; }
    public double getValorPago() { return valorPago; }


    public double calcularValorPago() {
        if (saida == null || entrada == null) {
            throw new IllegalStateException("Entrada ou saída não definida.");
        }
        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException("A saída não pode ser anterior à entrada.");
        }

        Duration duracao = Duration.between(entrada, saida);
        long minutos = duracao.toMinutes();
        long fracoes = minutos / MINUTOS_POR_FRACAO;

        valorPago = fracoes * VALOR_FRACAO;

        if (valorPago > VALOR_MAXIMO) {
            valorPago = VALOR_MAXIMO;
        }

        return valorPago;
    }

    @Override
    public String toString() {
        return "UsoDeVaga [vaga=" + vaga.getIdentificacao() + ", entrada=" + entrada + ", saída=" + saida + ", valorPago=" + valorPago + "]";
    }
public void reservarVaga(String placa, int numero, char fila) throws UsoDeVagaException {
        if (placa == null || placa.isEmpty()) {
            throw new UsoDeVagaException("A placa do veículo é obrigatória para reservar uma vaga.");
        }

        // Outras validações ou lógica
    }
}

}