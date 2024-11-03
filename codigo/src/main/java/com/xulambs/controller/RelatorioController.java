package main.java.com.xulambs.controller;

import java.util.Map;

public class RelatorioController {
    private EstacionamentoController estacionamentoController;

    public RelatorioController(EstacionamentoController estacionamentoController) {
        this.estacionamentoController = estacionamentoController;
    }

    public void exibirRankingClientes(int mes, int ano) {
        Map<String, Double> ranking = estacionamentoController.getEstacionamento().calcularArrecadacaoPorCliente(mes, ano);

        System.out.println("Ranking dos clientes com maior arrecadação em " + mes + "/" + ano + ":");
        int posicao = 1;
        for (Map.Entry<String, Double> entry : ranking.entrySet()) {
            String cpf = entry.getKey();
            double valor = entry.getValue();
            System.out.println(posicao + ". CPF: " + cpf + " - Total: R$" + valor);
            posicao++;
        }
    }

    public void exibirArrecadacaoMensal(int mes, int ano) {
        double totalMes = estacionamentoController.calcularArrecadacaoPorMes(mes, ano);
        System.out.println("Total arrecadado no mês " + mes + "/" + ano + ": R$" + totalMes);
    }
}
