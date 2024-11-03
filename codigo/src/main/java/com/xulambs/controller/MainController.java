package main.java.com.xulambs.controller;

public class MainController {
    private ClienteController clienteController;
    private EstacionamentoController estacionamentoController;
    private RelatorioController relatorioController;

    public MainController(Estacionamento estacionamento) {
        this.clienteController = new ClienteController();
        this.estacionamentoController = new EstacionamentoController(estacionamento);
        this.relatorioController = new RelatorioController(estacionamentoController);
    }

    public void adicionarCliente(Cliente cliente) {
        clienteController.adicionarCliente(cliente);
    }

    public void removerCliente(String cpf) {
        clienteController.removerCliente(cpf);
    }

    public void listarClientes() {
        clienteController.listarClientes();
    }

    public void ocuparVaga(Vaga vaga, Veiculo veiculo, Cliente cliente) {
        estacionamentoController.ocuparVaga(vaga, veiculo, cliente);
    }

    public void liberarVaga(Vaga vaga) {
        estacionamentoController.liberarVaga(vaga);
    }

    public void calcularTotalArrecadado() {
        double total = estacionamentoController.calcularTotalArrecadado();
        System.out.println("Total arrecadado no estacionamento: R$" + total);
    }

    public void calcularArrecadacaoPorMes(int mes, int ano) {
        relatorioController.exibirArrecadacaoMensal(mes, ano);
    }

    public void exibirRankingClientes(int mes, int ano) {
        relatorioController.exibirRankingClientes(mes, ano);
    }
}
