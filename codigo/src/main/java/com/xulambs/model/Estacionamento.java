package com.xulambs.model;

import com.xulambs.model.Vaga;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estacionamento {
    private String nome;
    public Vaga[][] vagas;
    public int quantidadeFileiras;
    public int vagasPorFileira;
    private double totalArrecadado;
    private List<String> registrosArrecadacao = new ArrayList<>();

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantidadeFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new Vaga[fileiras][vagasPorFila];
        this.totalArrecadado = 0;
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

    public void estacionar(Veiculo veiculo) {
        for (int i = 0; i < quantidadeFileiras; i++) {
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

    public void sair(String placa, double valorHora, int horasUsadas) {
        for (int i = 0; i < quantidadeFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                if (!vagas[i][j].isDisponivel() && vagas[i][j].getVeiculo().getPlaca().equals(placa)) {
                    double valorPago = valorPorUso(placa, valorHora, horasUsadas);
                    totalArrecadado += valorPago;

                    // Registro no formato: "placa;valorPago;data"
                    registrosArrecadacao.add(placa + ";" + valorPago + ";" + LocalDate.now());

                    vagas[i][j].sair();
                    System.out.println("Veículo com placa " + placa + " saiu da vaga [" + i + "][" + j + "] e pagou R$" + valorPago);
                    return;
                }
            }
        }
        System.out.println("Veículo com placa " + placa + " não encontrado no estacionamento.");
    }

    public double valorPorUso(String placa, double valorHora, int horasUsadas) {
        double valorFinal = valorHora * horasUsadas;
        System.out.println("Veículo com placa " + placa + " deve pagar R$" + valorFinal + " por " + horasUsadas + " horas de uso.");
        return valorFinal;
    }

    public double calcularTotalArrecadado() {
        System.out.println("Total arrecadado pelo estacionamento: R$" + totalArrecadado);
        return totalArrecadado;
    }

    public double calcularTotalArrecadadoPorMes(int mes, int ano) {
        double totalMes = 0;
        for (String registro : registrosArrecadacao) {
            String[] partes = registro.split(";");
            String[] dataPartes = partes[2].split("-"); // formato AAAA-MM-DD
            int anoRegistro = Integer.parseInt(dataPartes[0]);
            int mesRegistro = Integer.parseInt(dataPartes[1]);

            if (anoRegistro == ano && mesRegistro == mes) {
                totalMes += Double.parseDouble(partes[1]);
            }
        }
        System.out.println("Total arrecadado no mês " + mes + "/" + ano + ": R$" + totalMes);
        return totalMes;
    }

    public double calcularValorMedioPorUtilizacao() {
        if (registrosArrecadacao.isEmpty()) {
            System.out.println("Nenhuma utilização registrada.");
            return 0.0;
        }
        double total = 0.0;
        for (String registro : registrosArrecadacao) {
            String[] partes = registro.split(";");
            total += Double.parseDouble(partes[1]);
        }
        double valorMedio = total / registrosArrecadacao.size();
        System.out.println("Valor médio por utilização: R$" + valorMedio);
        return valorMedio;
    }


    public Map<String, Double> calcularArrecadacaoPorCliente(int mes, int ano) {
        Map<String, Double> arrecadacaoPorCliente = new HashMap<>();

        for (String registro : registrosArrecadacao) {
            String[] partes = registro.split(";");
            String cpfCliente = partes[0];
            double valorPago = Double.parseDouble(partes[1]);
            String[] dataPartes = partes[2].split("-");
            int anoRegistro = Integer.parseInt(dataPartes[0]);
            int mesRegistro = Integer.parseInt(dataPartes[1]);

        if (anoRegistro == ano && mesRegistro == mes) {
            arrecadacaoPorCliente.put(
                cpfCliente, 
                arrecadacaoPorCliente.getOrDefault(cpfCliente, 0.0) + valorPago
            );
        }
    }

    return arrecadacaoPorCliente.entrySet()
        .stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new
        ));
}
}
