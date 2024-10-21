import com.xulambs.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Estacionamento estacionamento;
    private static List<Cliente> clientes;
    private static JLabel statusBar;

    public static void main(String[] args) {
        estacionamento = new Estacionamento("Estacionamento Central", 3, 5);
        clientes = new ArrayList<>();

        // Criação da interface gráfica
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Estacionamento");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new GridBagLayout());
            frame.getContentPane().setBackground(new Color(215, 230, 219));

            // Título
            JLabel titleLabel = new JLabel("Xulambs", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            titleLabel.setForeground(new Color(96, 96, 96)); 
            GridBagConstraints gbcTitle = new GridBagConstraints();
            gbcTitle.gridwidth = 2; // Ocupa duas colunas
            gbcTitle.insets = new Insets(10, 10, 10, 10);
            frame.add(titleLabel, gbcTitle);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbcButton = new GridBagConstraints();
            gbcButton.fill = GridBagConstraints.HORIZONTAL;
            gbcButton.insets = new Insets(5, 5, 5, 5);

            // Adicionando botões com ícones
            gbcButton.gridx = 0;
            gbcButton.gridy = 0;
            buttonPanel.add(criarBotao("Adicionar Cliente", "src/icons/add_client.png", e -> adicionarCliente()), gbcButton);

            gbcButton.gridx = 1;
            buttonPanel.add(criarBotao("Adicionar Veículo", "src/icons/add_vehicle.png", e -> adicionarVeiculo()), gbcButton);

            gbcButton.gridx = 0;
            gbcButton.gridy = 1;
            buttonPanel.add(criarBotao("Estacionar Veículo", "src/icons/park.png", e -> estacionarVeiculo()), gbcButton);

            gbcButton.gridx = 1;
            buttonPanel.add(criarBotao("Retirar Veículo", "src/icons/remove_vehicle.png", e -> sairVeiculo()), gbcButton);

            gbcButton.gridx = 0;
            gbcButton.gridy = 2;
            buttonPanel.add(criarBotao("Mostrar Vagas", "src/icons/show_slots.png", e -> mostrarVagas()), gbcButton);

            gbcButton.gridx = 1;
            buttonPanel.add(criarBotao("Calcular Valor", "src/icons/calculate.png", e -> calcularValorUso()), gbcButton);

            gbcButton.gridx = 0;
            gbcButton.gridy = 3;
            gbcButton.gridwidth = 2; // Ocupa duas colunas
            JButton btnSair = criarBotao("Sair do Programa", "src/icons/exit.png", e -> System.exit(0));
            buttonPanel.add(btnSair, gbcButton);

            // Adicionando o painel de botões ao frame
            gbcButton.gridx = 0;
            gbcButton.gridy = 1;
            gbcButton.gridwidth = 2; // Ocupa duas colunas
            frame.add(buttonPanel, gbcButton);

            // Barra de status
            statusBar = new JLabel("Pronto");
            statusBar.setForeground(new Color(0, 51, 102)); // Texto escuro
            statusBar.setBackground(new Color(235, 242, 237)); // Fundo claro
            statusBar.setOpaque(false); // Para mostrar a cor de fundo
            gbcButton.gridy = 2;
            frame.add(statusBar, gbcButton);

            frame.setVisible(true);
        });
    }

    private static JButton criarBotao(String texto, String caminhoIcone, ActionListener acao) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        button.setBackground(new Color(174, 204, 183));
        button.setForeground(Color.WHITE); // Cor do texto em branco
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(29, 78, 49), 2)); 

        // Adicionando o ícone
        ImageIcon icon = new ImageIcon(caminhoIcone);
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto à direita do ícone
        button.addActionListener(acao);
        return button;
    }

    private static void adicionarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
        String[] categorias = {"VIP", "Idoso", "PCD", "Comum"};
        String categoria = (String) JOptionPane.showInputDialog(null, "Escolha a categoria do cliente:", "Categoria",
                JOptionPane.QUESTION_MESSAGE, null, categorias, categorias[3]);

        Cliente cliente = new Cliente(nome, cpf, categoria);
        clientes.add(cliente);
        statusBar.setText("Cliente " + nome + " adicionado com sucesso!");
    }

    private static void adicionarVeiculo() {
        StringBuilder clientesStr = new StringBuilder("Clientes disponíveis:\n");
        for (int i = 0; i < clientes.size(); i++) {
            clientesStr.append(i + 1).append(". ").append(clientes.get(i).getNome()).append("\n");
        }

        String escolha = JOptionPane.showInputDialog(clientesStr.toString() + "Escolha o cliente (número):");
        int clienteIndex = Integer.parseInt(escolha) - 1;

        if (clienteIndex >= 0 && clienteIndex < clientes.size()) {
            String placa = JOptionPane.showInputDialog("Digite a placa do veículo:");
            clientes.get(clienteIndex).adicionarVeiculo(new Veiculo(placa));
            statusBar.setText("Veículo adicionado ao cliente " + clientes.get(clienteIndex).getNome());
        } else {
            statusBar.setText("Cliente inválido.");
        }
    }

    private static void estacionarVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo que deseja estacionar:");
        Veiculo veiculo = new Veiculo(placa);
        estacionamento.estacionar(veiculo);
        statusBar.setText("Veículo " + placa + " estacionado com sucesso!");
    }

    private static void sairVeiculo() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo que deseja retirar:");
        estacionamento.sair(placa);
        statusBar.setText("Veículo " + placa + " retirado do estacionamento.");
    }

    private static void mostrarVagas() {
        StringBuilder vagasStr = new StringBuilder("Vagas disponíveis:\n");
        for (int i = 0; i < estacionamento.quantidadeFileiras; i++) {
            for (int j = 0; j < estacionamento.vagasPorFileira; j++) {
                if (estacionamento.vagas[i][j].isDisponivel()) {
                    vagasStr.append("Vaga [").append(i).append("][").append(j).append("] - Disponível\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, vagasStr.toString(), "Vagas Disponíveis", JOptionPane.INFORMATION_MESSAGE);
        statusBar.setText("Vagas mostradas com sucesso.");
    }

    private static void calcularValorUso() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo para calcular o valor:");
        double valorPorHora = 4.0; // exemplo de valor por hora
        String horasUsadasStr = JOptionPane.showInputDialog("Digite a quantidade de horas usadas:");
        int horasUsadas = Integer.parseInt(horasUsadasStr);
        double valorTotal = estacionamento.valorPorUso(placa, valorPorHora, horasUsadas);
        JOptionPane.showMessageDialog(null, "Valor total a ser pago: R$ " + valorTotal, "Valor de Uso", JOptionPane.INFORMATION_MESSAGE);
        statusBar.setText("Valor calculado com sucesso.");
    }
}
