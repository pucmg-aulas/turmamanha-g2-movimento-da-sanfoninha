package com.xulambs;

import javax.swing.*;
import com.xulambs.controllers.*;
import com.xulambs.daos.*;
import com.xulambs.view.HomeView;
import main.java.com.xulambs.view.HistoricoView;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Criar DAOs simulados
            ClienteDAO clienteDAO = new ClienteDAO();
            EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();
            VagaDAO vagaDAO = new VagaDAO();
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            HistoricoUsoDAO historicoUsoDAO = new HistoricoUsoDAO();
            PagamentoDAO pagamentoDAO = new PagamentoDAO();

            // 2. Criar Controllers (injetando os DAOs)
            ClienteController clienteController = new ClienteController(clienteDAO);
            EstacionamentoController estacionamentoController = new EstacionamentoController(estacionamentoDAO, vagaDAO);
            VeiculoController veiculoController = new VeiculoController(veiculoDAO, clienteDAO, vagaDAO);

            // 3. Criar RelatorioController (temporariamente sem HistoricoView)
            RelatorioController relatorioController = new RelatorioController(
                estacionamentoDAO, veiculoDAO, historicoUsoDAO, null
            );

            // 4. Criar a View para Histórico (injetando RelatorioController)
            HistoricoView historicoView = new HistoricoView(relatorioController);

            // 5. Atualizar RelatorioController para incluir a HistoricoView
            relatorioController.setHistoricoView(historicoView);

            // 6. Criar o MainController (com dependências)
            MainController mainController = new MainController(
                clienteController,
                estacionamentoController,
                veiculoController,
                relatorioController
            );

            // 7. Criar a HomeView (injetando o MainController)
            SwingUtilities.invokeLater(() -> {
                HomeView homeView = new HomeView(mainController);
                homeView.setVisible(true);
            });

        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação: " + e.getMessage());
            JOptionPane.showMessageDialog(
                null,
                "Erro ao iniciar a aplicação: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}

