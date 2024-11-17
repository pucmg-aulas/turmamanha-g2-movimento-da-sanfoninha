package main.java.com.xulambs.controller;

import com.xulambs.model.Cliente;
import main.java.com.xulambs.DAO.ClienteDAO;
import view.AddClienteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteController {

    private AddClienteView addClienteView;
    private ClienteDAO clienteDAO;

    public ClienteController(AddClienteView addClienteView, ClienteDAO clienteDAO) {
        this.addClienteView = addClienteView;
        this.clienteDAO = clienteDAO;
        initController();
    }

    private void initController() {
        addClienteView.getButtonSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        addClienteView.getButtonCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClienteView.dispose();
            }
        });
    }

    private void salvarCliente() {
        String nome = addClienteView.getTextNomeCliente().getText();
        String cpf = addClienteView.getTextCpfCliente().getText();
        String categoria = addClienteView.getTextCategoriaCliente().getText();

        if (nome.isEmpty() || cpf.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(addClienteView, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se o cliente já existe no banco de dados
        Cliente clienteExistente = clienteDAO.encontrarPorCpf(cpf);
        if (clienteExistente != null) {
            JOptionPane.showMessageDialog(addClienteView, "Cliente com CPF " + cpf + " já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria o cliente e salva
        Cliente cliente = new Cliente(nome, cpf, categoria);
        clienteDAO.salvarCliente(cliente);
        JOptionPane.showMessageDialog(addClienteView, "Cliente salvo com sucesso!");

        // Limpa os campos
        addClienteView.getTextNomeCliente().setText("");
        addClienteView.getTextCpfCliente().setText("");
        addClienteView.getTextCategoriaCliente().setText("");
    }
}
