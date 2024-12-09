package com.xulambs.controllers;

import com.xulambs.daos.ClienteDAO;
import com.xulambs.daos.EstacionamentoDAO;
import com.xulambs.daos.VagaDAO;
import com.xulambs.daos.VeiculoDAO;
import com.xulambs.model.Cliente;
import com.xulambs.model.Estacionamento;
import com.xulambs.model.Vaga;
import com.xulambs.model.Veiculo;
import com.xulambs.view.AddVeiculoView;
import com.xulambs.view.ListarVagaView;
import javax.swing.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VeiculoController {

    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;
    private VagaDAO vagaDAO;
    private AddVeiculoView addVeiculoView;
    private ListarVagaView listarVagaView; //View de listagem de vagas


    public VeiculoController(VeiculoDAO veiculoDAO, ClienteDAO clienteDAO, VagaDAO vagaDAO) {
        this.veiculoDAO = veiculoDAO;
        this.clienteDAO = clienteDAO;
        this.vagaDAO = vagaDAO;
        this.addVeiculoView = new AddVeiculoView(this);
        this.listarVagaView = new ListarVagaView(this); //Inicializando a view
    }


    public void abrirAdicionarVeiculoView(){
        addVeiculoView.setVisible(true);
    }

    public void abrirListarVagasView(){
        listarVagaView.carregarDadosNaTabela();
        listarVagaView.setVisible(true);
    }

    public void salvarVeiculo(String placa, UUID vagaId, String cpf) {

        try {
            // Validações
            if (placa == null || !placa.matches("[A-Z]{3}[0-9]{4}")) { //ajuste o regex
                throw new IllegalArgumentException("Placa inválida. Formato: AAA-1234");
            }

            if (vaga == null) {
                throw new IllegalArgumentException("Vaga inválida.");
            }


            if (cpf == null || !cpf.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            // Buscar o cliente pelo CPF
            Cliente cliente = clienteDAO.buscarPorCpf(cpf);
            if (cliente == null) {
                throw new IllegalArgumentException("Cliente não encontrado para o CPF informado.");
            }

            // Verificar se o veículo já existe
            if (veiculoDAO.buscarPorPlaca(placa) != null) {
                throw new IllegalArgumentException("Veículo com esta placa já cadastrado.");
            }



            Veiculo veiculo = new Veiculo(placa, "Carro", cliente); //Adapte o tipo conforme necessário
            veiculoDAO.salvar(veiculo);
            addVeiculoView.limparCampos();

            //Associa o veículo à vaga
            EstacionamentoController estacionamentoController = new EstacionamentoController(new EstacionamentoDAO(), new VagaDAO()); //Crie uma instância ou injete.
            Estacionamento estacionamento = estacionamentoController.getEstacionamentoPorId(UUID.randomUUID()); //Adapte para obter o estacionamento correto.


            if (estacionamento != null) {
               estacionamento.ocuparVaga(vagaId, veiculo);
            }


            JOptionPane.showMessageDialog(addVeiculoView, "Veículo salvo com sucesso!");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(addVeiculoView, "Erro ao salvar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(addVeiculoView, "Erro ao salvar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public void editarVeiculo(int id) {
        try {
            // Coletar os dados necessários para edição (como nova placa, tipo e CPF)
            String novaPlaca = JOptionPane.showInputDialog("Digite a nova placa:");
            String novoTipo = JOptionPane.showInputDialog("Digite o novo tipo de veículo:");
            String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do cliente:");
    
            // Validar os dados
            if (novaPlaca == null || !novaPlaca.matches("[A-Z]{3}[0-9]{4}")) {
                throw new IllegalArgumentException("Placa inválida. Formato: AAA-1234");
            }
    
            if (cpfCliente == null || !cpfCliente.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF inválido.");
            }
    
            // Buscar o veículo pelo ID
            Veiculo veiculo = veiculoDAO.buscarPorId(id);
            if (veiculo == null) {
                throw new IllegalArgumentException("Veículo não encontrado.");
            }
    
            // Buscar o cliente pelo CPF
            Cliente cliente = clienteDAO.buscarPorCpf(cpfCliente);
            if (cliente == null) {
                throw new IllegalArgumentException("Cliente não encontrado para o CPF informado.");
            }
    
            // Atualizar os dados do veículo
            veiculo.setPlaca(novaPlaca);
            veiculo.setTipo(novoTipo);
            veiculo.setCliente(cliente);
    
            // Atualizar o veículo no banco de dados
            veiculoDAO.atualizar(veiculo);
    
            JOptionPane.showMessageDialog(null, "Veículo atualizado com sucesso!");
    
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    

    // Método para excluir um veículo
    public void excluirVeiculo(int id) {
        try {
            // Buscar o veículo pelo ID
            Veiculo veiculo = veiculoDAO.buscarPorId(id);
            if (veiculo == null) {
                throw new IllegalArgumentException("Veículo não encontrado.");
            }

            // Verificar se o veículo está associado a uma vaga e liberá-la
            EstacionamentoController estacionamentoController = new EstacionamentoController(new EstacionamentoDAO(), new VagaDAO());
            Estacionamento estacionamento = estacionamentoController.getEstacionamentoPorId(UUID.randomUUID()); // Altere conforme necessário
            if (estacionamento != null && veiculo.getVaga() != null) {
                estacionamento.liberarVaga(veiculo.getVaga().getId());
            }

            // Excluir o veículo
            veiculoDAO.excluir(veiculo);

            JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public List<Veiculo> obterVeiculos() {
        return veiculoDAO.listarTodos();
    }


    public List<Cliente> obterClientes() {
        return clienteDAO.listarTodos();
    }

    public List<Vaga> obterVagasDisponiveis(){
        return vagaDAO.listarTodos().stream().filter(v -> !v.isOcupada()).collect(Collectors.toList());
    }

    public Vaga getVagaPorIdentificacao(String identificacao) {
        return vagaDAO.listarTodos().stream()
            .filter(v -> v.getIdentificacao().equalsIgnoreCase(identificacao))
            .findFirst()
            .orElse(null);
    }
    




}
