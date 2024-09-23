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

    public void adicionarUso(UsoDeVaga uso) {
        usos.add(uso);
    }
}
