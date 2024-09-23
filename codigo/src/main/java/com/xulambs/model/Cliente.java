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
<<<<<<< HEAD
=======


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Veiculo> getVeiculos() {
        return this.veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
>>>>>>> 39fd5b78a225fe348ccf2eba9ed43982fbb60ee4
}
