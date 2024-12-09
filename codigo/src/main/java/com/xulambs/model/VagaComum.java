package com.xulambs.model;

public class VagaComum extends Vaga {
    private double valorDesconto;
    private String tamanho;

    public VagaComum(int fila, int numero, String tamanho, double precoBase, double valorDesconto) {
        super(fila, numero, "COMUM", precoBase);
        this.tamanho = tamanho;
        this.valorDesconto = valorDesconto;
    }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public double getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(double valorDesconto) { this.valorDesconto = valorDesconto; }


    @Override
    public double calcularValor() {
        double valor = super.calcularValor();
        return Math.max(0, valor - valorDesconto); // Garante que o valor não seja negativo
    }

    @Override
    public String getIdentificacao(){
        return super.getIdentificacao() +  " - Tamanho: "+ tamanho;
    }

}