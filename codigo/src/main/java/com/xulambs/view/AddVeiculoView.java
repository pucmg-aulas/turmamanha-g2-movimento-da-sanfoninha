package com.xulambs.view;

import com.xulambs.controllers.VeiculoController;
import com.xulambs.model.Cliente;
import com.xulambs.model.Vaga;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.util.List;
import java.util.UUID;

public class AddVeiculoView extends JFrame {

    private VeiculoController controller;

    private JLabel carroPlacaRotulo = new JLabel("Placa");
    private JLabel numeroVagaRotulo = new JLabel("Vaga");
    private JLabel clienteRotulo = new JLabel("Cliente");
    private JComboBox<String> clienteComboBox = new JComboBox<>();
    private JFormattedTextField textPlacaVeiculo;
    private JComboBox<String> vagaComboBox = new JComboBox<>(); //ComboBox para as vagas
    private JToggleButton buttonCancelar = new JToggleButton("Cancelar");
    private JToggleButton buttonSalvar = new JToggleButton("Salvar");



    public AddVeiculoView(VeiculoController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Xulambs Parking - Adicionar Veículo");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);


    }

    private void initComponents() {
       JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        // Placa
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(carroPlacaRotulo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        try {
            MaskFormatter mask = new MaskFormatter("UUU-####"); // Adapte a máscara
            textPlacaVeiculo = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            textPlacaVeiculo = new JFormattedTextField();
        }
         jPanel.add(textPlacaVeiculo, gbc);


        //Vaga
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        jPanel.add(numeroVagaRotulo, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Preencher o JComboBox com as vagas disponíveis
        try {
            List<Vaga> vagas = controller.obterVagasDisponiveis();
            vagaComboBox.removeAllItems(); // Limpa o comboBox antes de adicionar novos itens
            for (Vaga vaga : vagas) {
                vagaComboBox.addItem(vaga.getIdentificacao());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar vagas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Logue a exceção adequadamente
        }


        //Cliente
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        jPanel.add(clienteRotulo, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        List<Cliente> clientes = controller.obterClientes();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente.getNome() + " - " + cliente.getCpf());
        }
        jPanel.add(clienteComboBox, gbc);



        JPanel painelBotoes = new JPanel();
        painelBotoes.add(buttonCancelar);
        painelBotoes.add(buttonSalvar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(painelBotoes, gbc);

        add(jPanel);

        buttonSalvar.addActionListener(e -> salvarVeiculo());
        buttonCancelar.addActionListener(e -> dispose());
    }



    private void salvarVeiculo() {

         try {
             String placa = textPlacaVeiculo.getText();
             Vaga vagaSelecionada =  controller.getVagaPorIdentificacao((String) vagaComboBox.getSelectedItem());
             String clienteSelecionado = (String) clienteComboBox.getSelectedItem();

             if(placa.isEmpty() || placa.equals("   -    ")){
                 throw new IllegalArgumentException("Placa inválida.");
             }
              if(vagaSelecionada == null){
                 throw new IllegalArgumentException("Vaga inválida.");
             }
             if (clienteSelecionado == null) {
                throw new IllegalArgumentException("Selecione um cliente.");
             }

            String cpf = clienteSelecionado.substring(clienteSelecionado.lastIndexOf("-") + 2).trim();


            controller.salvarVeiculo(placa, vagaSelecionada.getId(), cpf);
            limparCampos();

         }catch (IllegalArgumentException e){
             JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
         }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public void limparCampos() {
        textPlacaVeiculo.setText("");
        vagaComboBox.setSelectedIndex(0);
        clienteComboBox.setSelectedIndex(0);
    }


}