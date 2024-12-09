package com.xulambs.view;

import com.xulambs.controllers.VeiculoController;
import com.xulambs.model.Vaga;
import com.xulambs.model.Veiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ListarVagaView extends JFrame {

    private VeiculoController controller; //Adaptado para VeiculoController
    private JButton buttonExcluir = new JButton("Excluir"); //Remover Veículo
    private JButton buttonEditar = new JButton("Editar");
    private JButton buttonVoltar = new JButton("Voltar");
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable tableVagas = new JTable();


    public ListarVagaView(VeiculoController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Xulambs Parking - Gerenciar Vagas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); //Aumentei o tamanho da janela
    }


    private void initComponents() {
         JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jScrollPane.setViewportView(tableVagas);
        painel.add(jScrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(buttonExcluir);
        painelBotoes.add(buttonEditar);
        painelBotoes.add(buttonVoltar);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        add(painel);

        buttonEditar.addActionListener(e -> {
            int selectedRow = tableVagas.getSelectedRow();
            if (selectedRow != -1) { // Verifica se uma linha foi selecionada
                int id = (int) tableVagas.getValueAt(selectedRow, 0); // Obtém o ID (ou outro valor, dependendo de como você armazena o ID)
                controller.editarVeiculo(id); // Chama o método editarVeiculo passando o id
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um veículo.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }); //Adaptado para Veículo
        buttonExcluir.addActionListener(e -> controller.excluirVeiculo(tableVagas.getSelectedRow())); //Adaptado para Veículo
        buttonVoltar.addActionListener(e -> dispose());


        carregarDadosNaTabela();

    }

    public void carregarDadosNaTabela() {
        //Adaptado para carregar os veículos e suas vagas.
        try {
            List<Veiculo> veiculos = controller.obterVeiculos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Placa");
            model.addColumn("Tipo");
            model.addColumn("Cliente");
            model.addColumn("Vaga");
            model.addColumn("Hora de Entrada");
            // ... outras colunas ...

            for (Veiculo veiculo : veiculos) {
                Vector<Object> row = new Vector<>();
                row.add(veiculo.getPlaca());
                row.add(veiculo.getTipo());
                row.add(veiculo.getCliente() != null ? veiculo.getCliente().getNome() : ""); //Nome do cliente
                Vaga vaga = veiculo.getUsos().isEmpty() ? null : veiculo.getUsos().get(0).getVaga(); //Pega a vaga do último uso, se existir. Adapte conforme necessário.

                row.add(vaga != null ? vaga.getIdentificacao() : "Não estacionado");
                row.add(vaga != null ? vaga.getHoraEntrada() : "");
                // Adicione outros dados conforme necessário


                model.addRow(row);
            }

            tableVagas.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar veículos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Log adequadamente em produção
        }


    }


    public void atualizarTabela() {
        carregarDadosNaTabela();
    }



}