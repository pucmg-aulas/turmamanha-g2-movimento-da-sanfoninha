package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Veiculo> veiculos;

    public Cliente(String nome) {
        this.nome = nome;
        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo possuiVeiculoEstacionado(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
}
