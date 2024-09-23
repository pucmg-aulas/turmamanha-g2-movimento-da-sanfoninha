package com.xulambs.model;

public class Estacionamento {
    private String nome;
    private Vaga[][] vagas;
    private int quantidadeFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantidadeFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new Vaga[fileiras][vagasPorFila];
        gerarVagas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void gerarVagas() {
        for (int i = 0; i < quantidadeFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas[i][j] = new Vaga(i, j);
            }
        }
    }

    public void estacionar(Veiculo veiculo) {        for (int i = 0; i < quantidadeFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (vagas[i][j].isDisponivel()) {
                    vagas[i][j].estacionar(veiculo, null);
                    System.out.println("Veículo com placa " + veiculo.getPlaca() + " estacionado na vaga [" + i + "][" + j + "].");
                    return;
                }
            }
        }
        System.out.println("Estacionamento cheio. Não foi possível estacionar o veículo de placa " + veiculo.getPlaca() + ".");
    }

    public void sair(String placa) {
        for (int i = 0; i < quantidadeFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (!vagas[i][j].isDisponivel() && vagas[i][j].getVeiculo().getPlaca().equals(placa)) {
                    vagas[i][j].sair();
                    System.out.println("Veículo com placa " + placa + " saiu da vaga [" + i + "][" + j + "].");
                    return;
                }
            }
        }
        System.out.println("Veículo com placa " + placa + " não encontrado no estacionamento.");
    }

    public void valorPorUso(String placa, double valorHora, int horasUsadas) {
        double valorFinal = valorHora * horasUsadas;
        System.out.println("Veículo com placa " + placa + " deve pagar R$" + valorFinal + " por " + horasUsadas + " horas de uso.");
    }
}
