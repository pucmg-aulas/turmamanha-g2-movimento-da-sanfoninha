package com.xulambs.view;

import com.xulambs.controllers.EstacionamentoController;
import com.xulambs.model.Estacionamento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ListarEstacionamentoView extends JFrame {

    private EstacionamentoController controller;
    private JButton buttonExcluir = new JButton("Excluir");
    private JButton buttonEditar = new JButton("Editar");
    private JButton buttonVoltar = new JButton("Voltar");
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable tableEstacionamentos = new JTable();


    public ListarEstacionamentoView(EstacionamentoController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Xulambs Parking - Gerenciar Estacionamentos");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
    }


    private void initComponents() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens

        jScrollPane.setViewportView(tableEstacionamentos);
        painel.add(jScrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(buttonExcluir);
        painelBotoes.add(buttonEditar);
        painelBotoes.add(buttonVoltar);
        painel.add(painelBotoes, BorderLayout.SOUTH);


        add(painel);



        buttonEditar.addActionListener(e -> controller.editarEstacionamento(tableEstacionamentos.getSelectedRow()));
        buttonExcluir.addActionListener(e -> controller.excluirEstacionamento(tableEstacionamentos.getSelectedRow()));
        buttonVoltar.addActionListener(e -> dispose());
        carregarDadosNaTabela();


    }

    public void carregarDadosNaTabela() {

         try {
            List<Estacionamento> estacionamentos = controller.obterEstacionamentos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nome");
            model.addColumn("Quantidade de Vagas");
            // ... outras colunas ...

            for (Estacionamento estacionamento : estacionamentos) {
                Vector<Object> row = new Vector<>();
                row.add(estacionamento.getNome());
                row.add(estacionamento.getQuantidadeTotalVagas());
                // ... outros dados ...
                model.addRow(row);
            }

            tableEstacionamentos.setModel(model);

        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Erro ao carregar estacionamentos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Logue isso adequadamente
        }
    }



    public void atualizarTabela() {
        carregarDadosNaTabela();
    }
}