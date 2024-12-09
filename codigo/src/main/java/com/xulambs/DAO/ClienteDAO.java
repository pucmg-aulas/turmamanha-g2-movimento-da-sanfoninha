package com.xulambs.daos;

import com.xulambs.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteDAO implements Dao<Cliente> {
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(UUID.randomUUID());
        }
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes); // Retorna uma cópia para evitar modificações externas
    }

    @Override
    public Cliente buscarPorId(UUID id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }


    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }




    @Override
    public void atualizar(Cliente cliente) {
        Cliente clienteExistente = buscarPorId(cliente.getId());
        if (clienteExistente != null) {
            clientes.remove(clienteExistente);
            clientes.add(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado para atualização."); // Trate a exceção adequadamente
        }
    }


    @Override
    public void excluir(UUID id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado para exclusão."); // Trate a exceção adequadamente
        }

    }

}