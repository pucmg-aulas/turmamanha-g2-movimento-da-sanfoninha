package com.xulambs.model;

import java.time.LocalDateTime;

public class UsoDeVaga {
    public static final double FRACAO_USO = 0.15;
    public static final double VALOR_FRACAO = 4.0;
    public static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.vaga.estacionar();
    }

    public double sair() {
        this.saida = LocalDateTime.now();
        vaga.sair();
        long minutos = java.time.Duration.between(entrada, saida).toMinutes();
        valorPago = Math.min(VALOR_MAXIMO, (minutos / 60.0) * VALOR_FRACAO);
        return valorPago;
    }
}
