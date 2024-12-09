package com.xulambs.controllers;

import com.xulambs.daos.EstacionamentoDAO;
import com.xulambs.daos.VagaDAO;
import com.xulambs.model.Cliente;
import com.xulambs.model.Estacionamento;
import com.xulambs.model.HistoricoUso;
import com.xulambs.model.UsoDeVaga;
import com.xulambs.model.Vaga;
import com.xulambs.model.Veiculo;
import com.xulambs.view.AddEstacionamentoView;
import com.xulambs.view.ListarEstacionamentoView;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class EstacionamentoController {
    private EstacionamentoDAO estacionamentoDAO;
    private VagaDAO vagaDAO;
    private AddEstacionamentoView addEstacionamentoView;
    private ListarEstacionamentoView listarEstacionamentoView;


    public void ocuparVaga(UUID estacionamentoId, UUID vagaId, Veiculo veiculo, Cliente cliente) {
        try {
             if(estacionamentoId == null){
                 throw new IllegalArgumentException("Estacionamento inválido.");
             }
              if(vagaId == null){
                 throw new IllegalArgumentException("Vaga inválida.");
             }
              if(veiculo == null){
                  throw new IllegalArgumentException("Veículo inválido");
              }

             if (cliente == null) {
                throw new IllegalArgumentException("Cliente inválido.");
            }



            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if (estacionamento == null) {
                throw new IllegalArgumentException("Estacionamento não encontrado.");
            }

            Vaga vaga = estacionamento.encontrarVagaPorId(vagaId);
             if(vaga == null){
                throw new Estacionamento.VagaInvalidaException("Vaga não encontrada no estacionamento.");
             }


            if (!vaga.isOcupada()) {
                //Associa o cliente ao veículo
                veiculo.setCliente(cliente);

                 vaga.estacionar(veiculo);
            } else {
                throw new Estacionamento.VagaOcupadaException("Vaga ocupada.");
            }

        } catch (Estacionamento.VagaOcupadaException | Estacionamento.VagaInvalidaException e) {
             JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // ou trate de outra forma
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao ocupar vaga: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

//Adicione o método para entrada no veículo
    public void entrarVeiculo(UUID estacionamentoId, UUID vagaId, String placa, String cpf) {
        try {
            if (estacionamentoId == null) {
                throw new IllegalArgumentException("Estacionamento inválido.");
            }
            if (vagaId == null) {
                throw new IllegalArgumentException("Vaga inválida.");
            }
            if (placa == null || placa.isEmpty()) {
                throw new IllegalArgumentException("Placa inválida.");
            }
            if (cpf == null || cpf.isEmpty()) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if (estacionamento == null) {
                throw new IllegalArgumentException("Estacionamento não encontrado.");
            }

            Veiculo veiculo = veiculoDAO.buscarPorPlaca(placa);
            if (veiculo == null) {
                throw new IllegalArgumentException("Veículo não encontrado.");
            }

            Cliente cliente = clienteDAO.buscarPorCpf(cpf);
            if (cliente == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }


            Vaga vaga = estacionamento.encontrarVagaPorId(vagaId);
            if (vaga == null) {
                throw new Estacionamento.VagaInvalidaException("Vaga não encontrada para o estacionamento.");
            }
            if (vaga.isOcupada()) {
                throw new Estacionamento.VagaOcupadaException("Vaga já ocupada.");
            }


             // Define o cliente do veículo se ainda não estiver definido
            if (veiculo.getCliente() == null) {
                veiculo.setCliente(cliente); // Associa o cliente ao veículo
                veiculoDAO.atualizar(veiculo); // Atualiza o veículo no DAO
            }

            estacionamento.ocuparVaga(vagaId, veiculo);

            // Criar um novo UsoDeVaga e adicioná-lo à lista de usos do veículo
            UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);
            veiculo.adicionarUsoDeVaga(usoDeVaga);

            // Salvar o uso da vaga e atualizar o veículo
            historicoUsoDAO.salvar(new HistoricoUso(cliente, veiculo, vaga, usoDeVaga, null)); // pagamento pode ser null inicialmente
            veiculoDAO.atualizar(veiculo);

            JOptionPane.showMessageDialog(null, "Entrada do veículo registrada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar a entrada do veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
}
}

    public EstacionamentoController(EstacionamentoDAO estacionamentoDAO, VagaDAO vagaDAO) {
        this.estacionamentoDAO = estacionamentoDAO;
        this.vagaDAO = vagaDAO;
        this.addEstacionamentoView = new AddEstacionamentoView(this);
        this.listarEstacionamentoView = new ListarEstacionamentoView(this);
    }

    public void abrirAdicionarEstacionamentoView() {
        addEstacionamentoView.setVisible(true);
    }


    public void abrirListarEstacionamentoView() {
        listarEstacionamentoView.carregarDadosNaTabela();
        listarEstacionamentoView.setVisible(true);
    }


    public void salvarEstacionamento(String nome, int quantidadeVagas) {
         try{
            //Validações
             if (nome == null || nome.isEmpty()) {
                 throw new IllegalArgumentException("Nome do estacionamento inválido.");
             }
             if (quantidadeVagas <= 0) {
                 throw new IllegalArgumentException("Quantidade de vagas inválida.");
             }

            Estacionamento estacionamento = new Estacionamento(nome, (int) Math.ceil((double)quantidadeVagas/20),20); //Calcula fileiras e colunas (max 20 vagas por fileira)
            estacionamentoDAO.salvar(estacionamento);

            //Salvar as vagas no DAO.
            for(Vaga vaga: estacionamento.getVagas().values()){
                 vagaDAO.salvar(vaga);
            }

            addEstacionamentoView.limparCampos();
            listarEstacionamentoView.atualizarTabela();

            JOptionPane.showMessageDialog(addEstacionamentoView, "Estacionamento salvo com sucesso!");

        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(addEstacionamentoView, "Erro ao salvar o estacionamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(addEstacionamentoView, "Erro ao salvar estacionamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Logue a exceção adequadamente
        }
    }



    public List<Estacionamento> obterEstacionamentos() {
        return estacionamentoDAO.listarTodos();
    }



    public void editarEstacionamento(int linhaSelecionada) {
        try {
            if (linhaSelecionada == -1) {
                throw new IllegalArgumentException("Nenhum estacionamento selecionado.");
            }

            List<Estacionamento> estacionamentos = estacionamentoDAO.listarTodos();
            Estacionamento estacionamentoSelecionado = estacionamentos.get(linhaSelecionada);


            addEstacionamentoView.preencherCampos(estacionamentoSelecionado);
            addEstacionamentoView.setVisible(true);

            listarEstacionamentoView.atualizarTabela();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(listarEstacionamentoView, "Erro ao editar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(listarEstacionamentoView, "Erro ao editar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Logue a exceção adequadamente
        }
    }


    public void excluirEstacionamento(int linhaSelecionada) {
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(listarEstacionamentoView, "Nenhum estacionamento selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(listarEstacionamentoView, "Tem certeza que deseja excluir este estacionamento?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try{
                List<Estacionamento> estacionamentos = estacionamentoDAO.listarTodos();
                Estacionamento estacionamentoSelecionado = estacionamentos.get(linhaSelecionada);

                // Exclua todas as vagas associadas ao estacionamento
                for (Vaga vaga : estacionamentoSelecionado.getVagas().values()) {
                    vagaDAO.excluir(vaga.getId());
                }

                estacionamentoDAO.excluir(estacionamentoSelecionado.getId());
                listarEstacionamentoView.atualizarTabela();
                 JOptionPane.showMessageDialog(listarEstacionamentoView, "Estacionamento excluído com sucesso!");
            }catch (Exception e){
                 JOptionPane.showMessageDialog(listarEstacionamentoView, "Erro ao excluir o estacionamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }


    public Estacionamento getEstacionamentoPorId(UUID id) {
        return estacionamentoDAO.buscarPorId(id);
    }
}