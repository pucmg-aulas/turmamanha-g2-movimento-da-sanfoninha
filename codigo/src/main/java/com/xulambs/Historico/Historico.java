import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Historico extends JFrame {
    private JTextArea clientesArea; // Área de texto para exibir a lista de clientes
    private JButton btnCarregarClientes; // Botão para carregar e exibir clientes

    public Historico () {
        setTitle("Lista de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializa a área de texto e desabilita edição
        clientesArea = new JTextArea();
        clientesArea.setEditable(false);
        
        // Adiciona uma barra de rolagem para a área de texto
        JScrollPane scrollPane = new JScrollPane(clientesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Inicializa o botão de carregar clientes
        btnCarregarClientes = new JButton("Carregar Clientes");
        
        // Adiciona um ActionListener ao botão para carregar a lista de clientes
        btnCarregarClientes.addActionListener(e -> carregarClientes());

        // Adiciona os componentes à janela
        add(scrollPane, BorderLayout.CENTER);
        add(btnCarregarClientes, BorderLayout.SOUTH);
    }

    // Método para carregar e exibir os clientes
    private void carregarClientes() {
        List<Cliente> clientes = ClienteRepository.getClientes(); // Puxa a lista de clientes

        // Limpa a área de texto antes de adicionar a lista atualizada
        clientesArea.setText("");

        // Itera pela lista e adiciona cada cliente à área de texto
        for (Cliente cliente : clientes) {
            clientesArea.append(cliente.toString() + "\n");
        }
    }

    // Método principal para executar o aplicativo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Historico  frame = new Historico ();
            frame.setVisible(true);
        });
    }
}
