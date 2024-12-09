package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
    private String placa;
    private String tipo;
    private Cliente cliente;
    private List<UsoDeVaga> usos;

    public Veiculo(String placa, String tipo, Cliente cliente) {
        if (!validarPlaca(placa)) {
            throw new IllegalArgumentException("Placa inválida.");
        }
        this.placa = placa;
        this.tipo = tipo;
        this.cliente = cliente;
        this.usos = new ArrayList<>();
    }

    private boolean validarPlaca(String placa) {
        // Implemente a lógica de validação de placa aqui (regex ou biblioteca externa)
        // Exemplo com regex (adapte ao formato da sua placa):
        String regex = "[A-Z]{3}[0-9]{4}"; // Exemplo, adapte conforme necessário
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }


    // Getters e Setters
    public String getPlaca() { return placa; }
    public String getTipo() { return tipo; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente;}
    public List<UsoDeVaga> getUsos() { return new ArrayList<>(usos); } //Retorna cópia


    public int totalDeUsos() {
        return usos.size();
    }

    public void adicionarUsoDeVaga(UsoDeVaga uso) {
        this.usos.add(uso);
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (UsoDeVaga uso : usos) {
            valorTotal += uso.getValorPago();
        }
        return valorTotal;
    }

    public boolean isComum() {
        return cliente != null && cliente.getCategoria().equalsIgnoreCase("COMUM");
    }

    public boolean isIdoso() {
        return cliente != null && cliente.isIdoso();
    }

    public boolean isPcd() {
        return cliente != null && cliente.isPcd();
    }

    public boolean isVip() {
        return cliente != null && cliente.isVip();
    }


    // sobrescreva equals e hashcode aqui, usando placa como identificador único.

}