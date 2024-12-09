package com.xulambs.model;

public class VagaVIP extends Vaga {
    private double valorAcrescimo; // Acréscimo, não desconto
    private String tamanho;

    public VagaVIP(int fila, int numero, String tamanho, double precoBase, double valorAcrescimo) {
        super(fila, numero, "VIP", precoBase);
        this.tamanho = tamanho;
        this.valorAcrescimo = valorAcrescimo;
    }


    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public double getValorAcrescimo() { return valorAcrescimo; }
    public void setValorAcrescimo(double valorAcrescimo) { this.valorAcrescimo = valorAcrescimo; }

    @Override
    public double calcularValor() {
        double valor = super.calcularValor();
        return valor + valorAcrescimo;
    }
    
        @Override
    public String getIdentificacao(){
        return super.getIdentificacao() +  " - Tamanho: "+ tamanho;
    }
}