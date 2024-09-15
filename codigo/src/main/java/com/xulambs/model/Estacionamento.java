package com.xulambs.model;


import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantidadeFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantidadeFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.vagas = new ArrayList<>();
        gerarVagas();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarVeiculo(Veiculo veiculo, String idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            cliente.adicionarVeiculo(veiculo);
        }
    }

    public Cliente buscarClientePorId(String id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    private void gerarVagas() {
        for (int i = 1; i <= quantidadeFileiras; i++) {
            for (int j = 1; j <= vagasPorFileira; j++) {
                vagas.add(new Vaga(i, j));
            }
        }
    }

    public void estacionar(String placa) {
        Vaga vaga = vagas.stream().filter(Vaga::isDisponivel).findFirst().orElse(null);
        if (vaga != null) {
            System.out.println("Estacionando o veículo na vaga: " + vaga.getId());
        } else {
            System.out.println("Não há vagas disponíveis.");
        }
    }

    public void sair(String placa) {
        System.out.println("Veículo saiu da vaga.");
    }

    public void valorPorUso() {
        // Implementar lógica para calcular valor de estacionamento
    }
}
