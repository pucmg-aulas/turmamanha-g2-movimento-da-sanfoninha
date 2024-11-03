import com.xulambs.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 3, 5);
        List<Cliente> clientes = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            HomeView homeView = new HomeView();
            homeView.setVisible(true);
        });
        


        while (true) {
            System.out.println("\n--- Menu do Estacionamento ---");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Veículo ao Cliente");
            System.out.println("3. Estacionar Veículo");
            System.out.println("4. Sair do Estacionamento");
            System.out.println("5. Mostrar Vagas Disponíveis");
            System.out.println("6. Calcular Valor de Uso");
            System.out.println("0. Sair do Programa");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    // Adicionar Cliente
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    // Escolher categoria
                    System.out.println("Escolha a categoria do cliente:");
                    System.out.println("1. VIP");
                    System.out.println("2. Idoso");
                    System.out.println("3. PCD");
                    System.out.println("4. Comum");
                    System.out.print("Escolha uma opção: ");
                    int categoriaOpcao = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    String categoria;

                    switch (categoriaOpcao) {
                        case 1:
                            categoria = "VIP";
                            break;
                        case 2:
                            categoria = "Idoso";
                            break;
                        case 3:
                            categoria = "PCD";
                            break;
                        default:
                            categoria = "Comum"; // Categoria padrão
                            break;
                    }

                    Cliente cliente = new Cliente(nome, cpf, categoria);
                    clientes.add(cliente);
                    System.out.println("Cliente " + nome + " adicionado com sucesso!");
                    break;

                case 2:
                    // Adicionar Veículo ao Cliente
                    System.out.println("Clientes disponíveis:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + 1 + ". " + clientes.get(i).getNome());
                    }
                    System.out.print("Escolha o cliente (número): ");
                    int clienteIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consumir a quebra de linha

                    if (clienteIndex >= 0 && clienteIndex < clientes.size()) {
                        System.out.print("Digite a placa do veículo: ");
                        String placa = scanner.nextLine();
                        clientes.get(clienteIndex).adicionarVeiculo(new Veiculo(placa));
                    } else {
                        System.out.println("Cliente inválido.");
                    }
                    break;

                case 3:
                    // Estacionar Veículo
                    System.out.print("Digite a placa do veículo que deseja estacionar: ");
                    String placaEstacionar = scanner.nextLine();
                    Veiculo veiculoEstacionar = new Veiculo(placaEstacionar);
                    estacionamento.estacionar(veiculoEstacionar);
                    break;

                case 4:
                    // Sair do Estacionamento
                    System.out.print("Digite a placa do veículo que deseja retirar: ");
                    String placaSair = scanner.nextLine();
                    estacionamento.sair(placaSair);
                    break;

                case 5:
                    // Mostrar Vagas Disponíveis
                    System.out.println("Vagas disponíveis:");
                    for (int i = 0; i < estacionamento.quantidadeFileiras; i++) {
                        for (int j = 0; j < estacionamento.vagasPorFileira; j++) {
                            if (estacionamento.vagas[i][j].isDisponivel()) {
                                System.out.println("Vaga [" + i + "][" + j + "] - Disponível");
                            }
                        }
                    }
                    break;

                case 6:
                    // Calcular Valor de Uso
                    System.out.print("Digite a placa do veículo para calcular o valor: ");
                    String placaCalcular = scanner.nextLine();
                    double valorPorHora = 4.0; // exemplo de valor por hora
                    System.out.print("Digite a quantidade de horas usadas: ");
                    int horasUsadas = scanner.nextInt();
                    double valorTotal = estacionamento.valorPorUso(placaCalcular, valorPorHora, horasUsadas);
                    System.out.println("Valor total a ser pago: R$ " + valorTotal);
                    break;

                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
