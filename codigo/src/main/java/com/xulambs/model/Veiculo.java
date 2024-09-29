package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private String placa;
    private List<UsoDeVaga> usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<>();
    }

    public String getPlaca() {
        return placa;
    }

    public int totalDeUsos() {
        return usos.size();
    }

    public void adicionarUsoDeVaga(UsoDeVaga uso) {
        usos.add(uso);
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (UsoDeVaga uso : usos) {
            valorTotal += uso.getValorPago();  // Delegar o cálculo para UsoDeVaga
        }
        return valorTotal;
    }
}
