package com.xulambs.model;

public class VagaPCD extends Vaga {
    private double valorDesconto; // Valor do desconto específico para a vaga PCD

    public VagaPCD(int fila, int numero, String tipoDesconto, double valorDesconto) {
        super(fila, numero, tipoDesconto); // Chama o construtor da classe Vaga
        this.valorDesconto = valorDesconto;
    }


    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    // Método para estacionar um veículo, verificando se a vaga é destinada para PCD
    @Override
    public boolean estacionar(Veiculo veiculo) {
        if (isDisponivel() && veiculo.isPcd()) { // Verifica se o veículo pertence a um cliente PCD
            super.estacionar(veiculo, getCliente()); // Chama o método da classe pai para estacionar
            System.out.println("Estacionamento reservado para PCD. Desconto aplicado: R$ " + valorDesconto);
            return true;
        }
        System.out.println("A vaga é destinada para PCD.");
        return false;
    }

    // Método para pegar as informações completas da vaga PCD
    public String getInformacoesVagaPCD() {
        String info = "Vaga PCD - Fila: " + getFila() + ", Número: " + getNumero() + 
                      ", Disponível: " + isDisponivel() + 
                      ", Tamanho: " + tamanho + 
                      ", Valor do Desconto: R$ " + valorDesconto;
        
        if (!isDisponivel()) {
            info += ", Veículo: " + getVeiculo().getModelo() + ", Cliente: " + getCliente().getNome();
        }
        
        return info;
    }
}
