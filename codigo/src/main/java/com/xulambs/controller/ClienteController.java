// package main.java.com.xulambs.controller;

// import com.xulambs.model.Cliente;
// import com.xulambs.model.Veiculo;

// import java.util.ArrayList;
// import java.util.List;

// public class ClienteController {
//     private List<Cliente> clientes;

//     public ClienteController() {
//         this.clientes = new ArrayList<>();
//     }


//     public void adicionarCliente(Cliente cliente) {
//         clientes.add(cliente);
//         System.out.println("Cliente " + cliente.getNome() + " adicionado.");
//     }


//     public Cliente obterClientePorNome(String nome) {
//         for (Cliente cliente : clientes) {
//             if (cliente.getNome().equalsIgnoreCase(nome)) {
//                 return cliente;
//             }
//         }
//         System.out.println("Cliente " + nome + " não encontrado.");
//         return null;
//     }


//     public void adicionarVeiculoAoCliente(String nomeCliente, Veiculo veiculo) {
//         Cliente cliente = obterClientePorNome(nomeCliente);
//         if (cliente != null) {
//             cliente.adicionarVeiculo(veiculo);
//             System.out.println("Veículo " + veiculo.getPlaca() + " adicionado ao cliente " + nomeCliente + ".");
//         }
//     }

//     public List<Cliente> listarClientes() {
//         return clientes;
//     }


//     public void removerCliente(String nomeCliente) {
//         Cliente cliente = obterClientePorNome(nomeCliente);
//         if (cliente != null) {
//             clientes.remove(cliente);
//             System.out.println("Cliente " + nomeCliente + " removido.");
//         }
//     }
// }
