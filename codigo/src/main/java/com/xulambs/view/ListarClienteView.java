package com.xulambs.view;

import com.xulambs.controllers.ClienteController;
import com.xulambs.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ListarClienteView extends JFrame {

    private ClienteController controller;
    private JButton buttonExcluir = new JButton("Excluir");
    private JButton buttonEditar = new JButton("Editar");
    private JButton buttonVoltar = new JButton("Voltar");
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable tableClientes = new JTable();

    public ListarClienteView(ClienteController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Xulambs Parking - Gerenciar clientes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);


    }

    private void initComponents() {

        // ... (código de layout -  GridBagLayout é recomendado para mais flexibilidade)
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        jScrollPane.setViewportView(tableClientes);
        painel.add(jScrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(buttonExcluir);
        painelBotoes.add(buttonEditar);
        painelBotoes.add(buttonVoltar);
        painel.add(painelBotoes, BorderLayout.SOUTH);


        add(painel);


        buttonEditar.addActionListener(e -> controller.editarCliente(tableClientes.getSelectedRow()));
        buttonExcluir.addActionListener(e -> controller.excluirCliente(tableClientes.getSelectedRow()));
        buttonVoltar.addActionListener(e -> dispose());


        carregarDadosNaTabela();
    }


     public void carregarDadosNaTabela() {
        try {

            List<Cliente> clientes = controller.obterClientes();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nome");
            model.addColumn("CPF");
            model.addColumn("Categoria");


            for (Cliente cliente : clientes) {
                Vector<String> row = new Vector<>();
                row.add(cliente.getNome());
                row.add(cliente.getCpf());
                row.add(cliente.getCategoria());
                model.addRow(row);
            }


            tableClientes.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    public void atualizarTabela() {
       carregarDadosNaTabela();
    }

}