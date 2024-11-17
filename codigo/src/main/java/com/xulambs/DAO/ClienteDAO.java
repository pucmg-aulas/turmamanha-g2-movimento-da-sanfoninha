package main.java.com.xulambs.DAO;

import com.xulambs.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static List<Cliente> clientes = new ArrayList<>();

    public void salvarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente salvo com sucesso: " + cliente.getNome());
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente encontrarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(String cpf) {
        Cliente cliente = encontrarPorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso: " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
