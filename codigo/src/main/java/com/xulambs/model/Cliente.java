package com.xulambs.model;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;  // Alterado de "identificador" para "cpf"
    private ArrayList<Veiculo> veiculos;
    private boolean anonimo;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.veiculos = new ArrayList<>();
        this.anonimo = false;
    }

    public Cliente() {
        this.nome = "Anônimo";
        this.cpf = "00000000000";  // Exemplo de CPF fictício para cliente anônimo
        this.veiculos = new ArrayList<>();
        this.anonimo = true;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        System.out.println("Veículo " + veiculo.getPlaca() + " adicionado ao cliente " + nome);
    }

    public void removerVeiculo(Veiculo veiculo) {
        if (veiculos.contains(veiculo)) {
            veiculos.remove(veiculo);
            System.out.println("Veículo " + veiculo.getPlaca() + " removido do cliente " + nome);
        } else {
            System.out.println("O cliente não possui esse veículo.");
        }
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void ocuparVaga(Estacionamento estacionamento, Vaga vaga, Veiculo veiculo) {
        if (veiculos.contains(veiculo)) {
            if (estacionamento.ocuparVaga(vaga, veiculo)) {
                System.out.println("Veículo " + veiculo.getPlaca() + " ocupou a vaga " + vaga.getIdentificador());
            } else {
                System.out.println("A vaga " + vaga.getIdentificador() + " já está ocupada.");
            }
        } else {
            System.out.println("O veículo " + veiculo.getPlaca() + " não está registrado para o cliente " + nome);
        }
    }

    public void liberarVaga(Estacionamento estacionamento, Vaga vaga) {
        if (vaga.isOcupada()) {
            estacionamento.liberarVaga(vaga);
            System.out.println("Vaga " + vaga.getIdentificador() + " foi liberada.");
        } else {
            System.out.println("A vaga " + vaga.getIdentificador() + " já está desocupada.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }
}
