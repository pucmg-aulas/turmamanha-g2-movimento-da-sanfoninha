package com.xulambs.view;

import com.xulambs.controllers.EstacionamentoController;
import com.xulambs.model.Estacionamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEstacionamentoView extends JFrame {

    private EstacionamentoController controller;


    private JLabel labelNome;
    private JTextField textFieldNome;

    private JLabel labelQuantidadeVagas;
    private JSpinner spinnerQuantidadeVagas;

    private JButton buttonSalvar;
    private JButton buttonCancelar;



    public AddEstacionamentoView(EstacionamentoController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Adicionar Estacionamento");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
    }



    private void initComponents() {

        //Painel
         JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);


         labelNome = new JLabel("Nome do Estacionamento:");
         textFieldNome = new JTextField(20);


         labelQuantidadeVagas = new JLabel("Quantidade de Vagas:");
         spinnerQuantidadeVagas = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1)); // máximo 500 vagas


         buttonSalvar = new JButton("Salvar");
         buttonCancelar = new JButton("Cancelar");


        //Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(labelNome, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(textFieldNome, gbc);

        //Vagas
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        jPanel.add(labelQuantidadeVagas, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(spinnerQuantidadeVagas, gbc);




        JPanel painelBotoes = new JPanel();
        painelBotoes.add(buttonCancelar);
        painelBotoes.add(buttonSalvar);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(painelBotoes, gbc);



        add(jPanel);


        buttonSalvar.addActionListener(e -> salvarEstacionamento());
        buttonCancelar.addActionListener(e -> dispose());
    }


    private void salvarEstacionamento() {
        String nome = textFieldNome.getText();
        int quantidadeVagas = (int) spinnerQuantidadeVagas.getValue();
        controller.salvarEstacionamento(nome, quantidadeVagas);
    }


    public void limparCampos() {
        textFieldNome.setText("");
        spinnerQuantidadeVagas.setValue(1); // Valor padrão
    }

    public void preencherCampos(Estacionamento estacionamento) {
        textFieldNome.setText(estacionamento.getNome());
        spinnerQuantidadeVagas.setValue(estacionamento.getQuantidadeTotalVagas());

    }


}