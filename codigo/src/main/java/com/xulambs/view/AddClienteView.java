package com.xulambs.view;

import com.xulambs.controllers.ClienteController;
import com.xulambs.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;


public class AddClienteView extends JFrame {

    private ClienteController controller;

    private JPanel jPanel = new JPanel();
    private JToggleButton buttonCancelar = new JToggleButton("Cancelar");
    private JToggleButton buttonSalvar = new JToggleButton("Salvar");
    private JLabel nomeClienteRotulo = new JLabel("Nome Cliente");
    private JLabel cpfClienteRotulo = new JLabel("CPF Cliente");
    private JLabel categoriaClienteRotulo = new JLabel("Categoria Cliente");
    private JTextField textNomeCliente = new JTextField();
    private JFormattedTextField textCpfCliente;
    private JComboBox<String> comboBoxCategoria;


    public AddClienteView(ClienteController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Xulambs Parking - Adicionar Cliente");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);


    }


    private void initComponents() {

        //Painel
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        //Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(nomeClienteRotulo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(textNomeCliente, gbc);


        //CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        jPanel.add(cpfClienteRotulo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            textCpfCliente = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            textCpfCliente = new JFormattedTextField();
        }
        jPanel.add(textCpfCliente, gbc);



        //Categoria
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        jPanel.add(categoriaClienteRotulo, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] categorias = {"Comum", "VIP", "Idoso", "PCD"};
        comboBoxCategoria = new JComboBox<>(categorias);
        jPanel.add(comboBoxCategoria, gbc);


        //Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(buttonCancelar);
        painelBotoes.add(buttonSalvar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; //Dois botões na mesma linha
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(painelBotoes, gbc);




        add(jPanel);


        buttonSalvar.addActionListener(e -> {
            salvarCliente();
        });

        buttonCancelar.addActionListener(e -> {
            dispose();
        });

    }




    private void salvarCliente(){
            controller.salvarCliente(textNomeCliente.getText(), textCpfCliente.getText(), (String) comboBoxCategoria.getSelectedItem());
    }


    public void limparCampos() {
        textNomeCliente.setText("");
        textCpfCliente.setText("");
        comboBoxCategoria.setSelectedIndex(0); // Seleciona o primeiro item por padrão
    }



    public void preencherCampos(Cliente cliente) {
        textNomeCliente.setText(cliente.getNome());
        textCpfCliente.setText(cliente.getCpf());
        comboBoxCategoria.setSelectedItem(cliente.getCategoria());
    }

}