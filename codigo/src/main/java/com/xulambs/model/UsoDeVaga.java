package com.xulambs.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class UsoDeVaga {
    private static final double FRACAO_USO = 0.15;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
    }

    public void registrarSaida() {
        this.saida = LocalDateTime.now();
        calcularValor();
    }

    private void calcularValor() {
        long minutos = java.time.Duration.between(entrada, saida).toMinutes();
        double fracoes = minutos / (60.0 * FRACAO_USO);
        this.valorPago = Math.min(fracoes * VALOR_FRACAO, VALOR_MAXIMO);
    }

    public double getValorPago() {
        return valorPago;
    }
}

