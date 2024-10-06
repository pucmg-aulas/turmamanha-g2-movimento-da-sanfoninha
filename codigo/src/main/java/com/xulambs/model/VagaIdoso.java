package com.xulambs.model;

public class VagaIdoso extends Vaga {
    private String tamanho; // Ex: Pequena, Média, Grande
    private double valorDesconto; // Valor do desconto específico para a vaga Idoso

    public VagaIdoso(int fila, int numero, String tipoDesconto, String tamanho, double valorDesconto) {
        super(fila, numero, tipoDesconto, tamanho, valorDesconto); // Chama o construtor da classe Vaga
        this.tamanho = tamanho;
        this.valorDesconto = valorDesconto;
    }

    // Método para estacionar um veículo, verificando se a vaga é destinada para idosos
    @Override
    public boolean estacionar(Veiculo veiculo) {
        if (isDisponivel() && veiculo.isIdoso()) { // Verifica se o veículo pertence a um cliente idoso
            super.estacionar(veiculo, cliente); // Chama o método da classe pai para estacionar
            System.out.println("Estacionamento reservado para Idoso. Desconto aplicado: R$ " + valorDesconto);
            return true;
        }
        System.out.println("A vaga é destinada para Idosos.");
        return false;
    }

    // Método para pegar as informações completas da vaga Idoso
    public String getInformacoesVagaIdoso() {
        String info = "Vaga Idoso - Fila: " + getFila() + ", Número: " + getNumero() + 
                      ", Disponível: " + isDisponivel() + 
                      ", Tamanho: " + tamanho + 
                      ", Valor do Desconto: R$ " + valorDesconto;
        
        if (!isDisponivel()) {
            info += ", Veículo: " + getVeiculo().getModelo() + ", Cliente: " + getCliente().getNome();
        }
        
        return info;
    }
}
