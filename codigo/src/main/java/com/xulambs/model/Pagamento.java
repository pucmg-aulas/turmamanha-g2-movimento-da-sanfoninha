package com.xulambs.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pagamento {
    private UUID id;
    private double valor;
    private LocalDateTime dataPagamento;
    private String tipoPagamento; // Adicione o tipo de pagamento

    public Pagamento(double valor, String tipoPagamento) {
        this.id = UUID.randomUUID();
        this.valor = valor;
        this.dataPagamento = LocalDateTime.now();
        this.tipoPagamento = tipoPagamento;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public double getValor() { return valor; }
    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public String getTipoPagamento() { return tipoPagamento; }


}