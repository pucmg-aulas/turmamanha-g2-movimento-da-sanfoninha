package com.xulambs.controllers;

import com.xulambs.daos.ClienteDAO;
import com.xulambs.model.Cliente;
import com.xulambs.view.AddClienteView;
import com.xulambs.view.ListarClienteView;

import javax.swing.*;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;
    private AddClienteView addClienteView;
    private ListarClienteView listarClienteView;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        this.addClienteView = new AddClienteView(this);
        this.listarClienteView = new ListarClienteView(this);
    }

    public void abrirAdicionarClienteView() {
        addClienteView.setVisible(true);
    }

    public void abrirListarClientesView() {
        listarClienteView.carregarDadosNaTabela();
        listarClienteView.setVisible(true);
    }



    public void salvarCliente(String nome, String cpf, String categoria) {

        try {
    
            // Validações
            if(nome == null || nome.isEmpty()){
                throw new IllegalArgumentException("Nome inválido");
            }
            
            // Remover todos os caracteres não numéricos do CPF
            cpf = cpf.replaceAll("[^0-9]", "");
            
            if (cpf == null || cpf.length() != 11) {
                throw new IllegalArgumentException("CPF Inválido. Formato: 11 dígitos numéricos.");
            }
            
            if(categoria == null || categoria.isEmpty()){
                throw new IllegalArgumentException("Categoria inválida");
            }
    
            // Verifica se o CPF já existe
            if (clienteDAO.buscarPorCpf(cpf) != null) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");
            }
    
            Cliente cliente = new Cliente(nome, cpf, categoria);
            clienteDAO.salvar(cliente);
            addClienteView.limparCampos();
            listarClienteView.atualizarTabela();
            JOptionPane.showMessageDialog(addClienteView, "Cliente salvo com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(addClienteView, "Erro ao salvar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(addClienteView, "Erro ao salvar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Em produção, logue a exceção adequadamente.
        }
    }
    


    public List<Cliente> obterClientes() {
        return clienteDAO.listarTodos();
    }




    public void editarCliente(int linhaSelecionada) {
        try{
            if (linhaSelecionada == -1) {
                 throw new IllegalArgumentException("Nenhum cliente selecionado.");
            }
             List<Cliente> clientes = clienteDAO.listarTodos();
            Cliente clienteSelecionado = clientes.get(linhaSelecionada);

            // Abra a tela de edição (AddClienteView) com os dados do cliente preenchidos
            addClienteView.preencherCampos(clienteSelecionado);
            addClienteView.setVisible(true);

            // Após a edição (que deve ser tratada no método salvarCliente), atualize a tabela
            listarClienteView.atualizarTabela();
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(listarClienteView, "Erro ao editar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(listarClienteView, "Erro ao editar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();//Em produção, logue a exceção adequadamente.
        }
    }


    public void excluirCliente(int linhaSelecionada) {
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(listarClienteView, "Nenhum cliente selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(listarClienteView, "Tem certeza que deseja excluir este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try{
                List<Cliente> clientes = clienteDAO.listarTodos();
                Cliente clienteSelecionado = clientes.get(linhaSelecionada);
                clienteDAO.excluir(clienteSelecionado.getId());
                listarClienteView.atualizarTabela();
                JOptionPane.showMessageDialog(listarClienteView, "Cliente excluído com sucesso!");
            }catch (Exception e){
                JOptionPane.showMessageDialog(listarClienteView, "Erro ao excluir o cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();//Em produção, logue a exceção adequadamente.
            }
        }
    }
}