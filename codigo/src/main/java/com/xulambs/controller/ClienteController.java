package main.java.com.xulambs.controller;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente " + cliente.getNome() + " adicionado.");
    }

    public void removerCliente(String cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
        System.out.println("Cliente com CPF " + cpf + " removido.");
    }

    public void listarClientes() {
        System.out.println("Lista de clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}
