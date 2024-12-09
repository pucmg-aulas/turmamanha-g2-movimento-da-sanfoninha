package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nome;
    private String cpf;
    private String categoria;
    private List<Veiculo> veiculos;
    private List<HistoricoUso> historicoUso;

    public Cliente(String nome, String cpf, String categoria) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.categoria = categoria;
        this.veiculos = new ArrayList<>();
        this.historicoUso = new ArrayList<>();

        // Validações (implemente as validações de CPF e categoria aqui ou no Controller)
        if (cpf == null || !cpf.matches("\\d{11}")) {  // Validação básica de CPF (11 dígitos)
            throw new IllegalArgumentException("CPF inválido.");
        }

    }


    // Getters e Setters (para todos os atributos)
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf;} //Validação no controller
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) {this.categoria = categoria; }//Validação no controller
    public List<Veiculo> getVeiculos() { return new ArrayList<>(veiculos); } //Retorna cópia
    public List<HistoricoUso> getHistoricoUso() { return new ArrayList<>(historicoUso); } //Retorna cópia



    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        this.veiculos.remove(veiculo);
    }


    public boolean isVip() {
        return this.categoria != null && this.categoria.equalsIgnoreCase("VIP");
    }

    public boolean isIdoso() {
        return this.categoria != null && this.categoria.equalsIgnoreCase("IDOSO");
    }

    public boolean isPcd() {
        return this.categoria != null && this.categoria.equalsIgnoreCase("PCD");
    }

    public void adicionarHistoricoUso(HistoricoUso historico) {
        this.historicoUso.add(historico);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
} 