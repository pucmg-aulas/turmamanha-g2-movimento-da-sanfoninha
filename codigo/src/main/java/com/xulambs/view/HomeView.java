package com.xulambs.view;

import com.xulambs.controllers.MainController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.xulambs.view.*;

public class HomeView extends JFrame {

    private MainController controller;


    private JButton btnAdicionarCliente;
    private JButton btnGerenciarVagas; //Gerenciar veículos
    private JButton btnGerenciarEstacionamento;
    private JButton btnListarClientes;
    private JButton btnListarVagas;  //Listar veículos
    private JButton btnListarEstacionamentos;
    private JButton btnHistorico;
    // ... outros componentes ...


    public HomeView(MainController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Sistema de Estacionamento - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza na tela


    }


    private void initComponents() {


        JPanel painelBotoes = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margens


         btnAdicionarCliente = new JButton("Adicionar Cliente");
         btnGerenciarVagas = new JButton("Adicionar Veículo");
         btnGerenciarEstacionamento = new JButton("Gerenciar Estacionamento");
         btnListarClientes = new JButton("Listar Clientes");
         btnListarVagas = new JButton("Listar Veículos");
         btnListarEstacionamentos = new JButton("Listar Estacionamentos");
         btnHistorico = new JButton("Histórico");

        // Adiciona os botões ao painel
        painelBotoes.add(btnAdicionarCliente);
        painelBotoes.add(btnListarClientes);

        painelBotoes.add(btnGerenciarEstacionamento);
        painelBotoes.add(btnListarEstacionamentos);

        painelBotoes.add(btnGerenciarVagas);
        painelBotoes.add(btnListarVagas);

        painelBotoes.add(btnHistorico);



        // Adiciona o painel à janela
        add(painelBotoes);



        btnAdicionarCliente.addActionListener(e -> controller.AddClienteView());
        btnGerenciarVagas.addActionListener(e -> controller.AddVeiculoView());
        btnGerenciarEstacionamento.addActionListener(e -> controller.AddEstacionamentoView());
        btnListarClientes.addActionListener(e -> controller.listarClientes());
        btnListarVagas.addActionListener(e -> controller.listarVeiculos());
        btnListarEstacionamentos.addActionListener(e -> controller.listarEstacionamentos());
        btnHistorico.addActionListener(e -> controller.abrirHistoricoView());


    }




}