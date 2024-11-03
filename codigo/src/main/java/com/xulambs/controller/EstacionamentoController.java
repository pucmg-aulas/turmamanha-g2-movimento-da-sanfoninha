package main.java.com.xulambs.controller;

public class EstacionamentoController {
    private Estacionamento estacionamento;

    public EstacionamentoController(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public void ocuparVaga(Vaga vaga, Veiculo veiculo, Cliente cliente) {
        cliente.ocuparVaga(estacionamento, vaga, veiculo);
    }

    public void liberarVaga(Vaga vaga) {
        estacionamento.liberarVaga(vaga);
    }

    public double calcularTotalArrecadado() {
        return estacionamento.calcularTotalArrecadado();
    }

    public double calcularArrecadacaoPorMes(int mes, int ano) {
        return estacionamento.calcularTotalArrecadadoPorMes(mes, ano);
    }
}
