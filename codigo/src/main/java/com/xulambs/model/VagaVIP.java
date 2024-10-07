package com.xulambs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VagaVIP extends Vaga {
    private double valorDesconto; // Valor do desconto específico para a vaga VIP

    public VagaVIP(int fila, int numero, String tipoDesconto, boolean necessitaAcompanhante, double valorDesconto) {
            super(fila, numero, tipoDesconto); // Chama o construtor da classe Vaga
            this.valorDesconto = valorDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }
    

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    // Método para estacionar um veículo, verificando se a vaga é destinada para VIP
    @Override
    public boolean estacionar(Veiculo veiculo) {
        if (isDisponivel() && veiculo.isVip()) { // Verifica se o veículo pertence a um cliente VIP
            super.estacionar(veiculo, Cliente); // Chama o método da classe pai para estacionar
            System.out.println("Estacionamento reservado para VIP. Aumento aplicado: R$ " + valorDesconto);
            return true;
        }
        System.out.println("A vaga é destinada para VIP.");
        return false;
    }

    // Método para pegar as informações completas da vaga VIP
    public String getInformacoesVagaVIP() {
        String info = "Vaga VIP - Fila: " + getFila() + ", Número: " + getNumero() + 
                      ", Disponível: " + isDisponivel() + 
                      ", Tamanho: " + tamanho + 
                      ", Valor do Desconto: R$ " + valorDesconto; 
        
        if (!isDisponivel()) {
            info += ", Veículo: " + getVeiculo().getModelo() + ", Cliente: " + getCliente().getNome();
        }
        
        return info;
    }
}

