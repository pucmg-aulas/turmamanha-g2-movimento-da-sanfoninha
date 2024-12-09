package com.xulambs.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistoricoUso {
    private UUID id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Vaga vaga;
    private UsoDeVaga usoDeVaga;
    private Pagamento pagamento;
    private LocalDateTime dataRegistro;

    public HistoricoUso(Cliente cliente, Veiculo veiculo, Vaga vaga, UsoDeVaga usoDeVaga, Pagamento pagamento) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.usoDeVaga = usoDeVaga;
        this.pagamento = pagamento;
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public Vaga getVaga() { return vaga; }
    public UsoDeVaga getUsoDeVaga() { return usoDeVaga; }
    public Pagamento getPagamento() { return pagamento; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }

}