package com.xulambs.controllers;

import com.xulambs.view.AdicionarClienteView;
import com.xulambs.view.AdicionarVeiculoView;
import com.xulambs.view.AdicionarEstacionamentoView;
import com.xulambs.model.Cliente;
import com.xulambs.model.Veiculo;
import com.xulambs.model.Estacionamento;
import java.util.UUID;

public class MainController {

    private ClienteController clienteController;
    private EstacionamentoController estacionamentoController;
    private VeiculoController veiculoController;
    private RelatorioController relatorioController;

    public MainController(ClienteController clienteController, EstacionamentoController estacionamentoController, VeiculoController veiculoController, RelatorioController relatorioController) {
        this.clienteController = clienteController;
        this.estacionamentoController = estacionamentoController;
        this.veiculoController = veiculoController;
        this.relatorioController = relatorioController;
    }

    // Método para abrir a view de adicionar cliente
    public void AddClienteView() {
        clienteController.abrirAdicionarClienteView();
    }

    // Método para abrir a view de adicionar veículo
    public void AddVeiculoView() {
        veiculoController.abrirAdicionarVeiculoView();
    }

    // Método para abrir a view de adicionar estacionamento
    public void AddEstacionamentoView() {
        estacionamentoController.abrirAdicionarEstacionamentoView();
    }

    // Métodos para listar clientes, veículos, estacionamentos e histórico
    public void listarClientes() {
        clienteController.abrirListarClientesView();
    }

    public void listarVeiculos() {
        veiculoController.abrirListarVagasView();
    }

    public void listarEstacionamentos() {
        estacionamentoController.abrirListarEstacionamentoView();
    }

    public void abrirHistoricoView() {
        relatorioController.abrirHistoricoView();
    }

    // Outros métodos para gerenciar entidades (clientes, veículos, estacionamentos)
    public void adicionarCliente(String nome, String cpf, String categoria) {
        clienteController.salvarCliente(nome, cpf, categoria);
    }

    public void adicionarVeiculo(String placa, UUID vagaId, String cpf) {
        veiculoController.salvarVeiculo(placa, vagaId, cpf);
    }

    public void adicionarEstacionamento(String nome, int quantidadeVagas) {
        estacionamentoController.salvarEstacionamento(nome, quantidadeVagas);
    }
}