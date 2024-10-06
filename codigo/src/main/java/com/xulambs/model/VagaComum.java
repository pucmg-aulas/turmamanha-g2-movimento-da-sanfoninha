package com.xulambs.model;

public class VagaComum extends Vaga {
    private String tamanho; // Ex: Pequena, Média, Grande
    private double valorDesconto; // Valor do desconto específico para a vaga comum

    public VagaComum(int fila, int numero, String tipoDesconto, String tamanho, double valorDesconto) {
        super(fila, numero, tipoDesconto, tamanho, valorDesconto); // Chama o construtor da classe Vaga
    }

    @Override
    public boolean estacionar(Veiculo veiculo) {
        if (isDisponivel() && veiculo.isComum()) { // Verifica se a vaga está disponível e se o veículo é de um cliente comum
            Cliente cliente = veiculo.getCliente(); // Supondo que o veículo tenha um método para obter o cliente
            super.estacionar(veiculo, cliente); // Chama o método da classe pai passando o veículo e o cliente
            System.out.println("Estacionamento reservado para comum. Desconto aplicado: R$ " + valorDesconto);
            return true;
        }
        System.out.println("A vaga é destinada apenas para veículos comuns.");
        return false;
    }
    
    

    // Método para pegar as informações completas da vaga comum
    public String getInformacoesVagaComum() {
        String info = "Vaga comum - Fila: " + getFila() + ", Número: " + getNumero() + 
                      ", Disponível: " + isDisponivel() + 
                      ", Tamanho: " + tamanho + 
                      ", Valor do Desconto: R$ " + valorDesconto;
        
        if (!isDisponivel()) {
            info += ", Veículo: " + getVeiculo().getModelo() + ", Cliente: " + getCliente().getNome();
        }
        
        return info;
    }
}
