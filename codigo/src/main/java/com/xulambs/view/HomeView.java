package view;
import java.awt.*;
import javax.swing.*;

public class HomeView extends JFrame {
    private JButton btnAdicionarCliente;
    private JButton btnGerenciarVagas;
    private JButton btnGerenciarEstacionamento;
    private JButton btnListarClientes;
    private JButton btnListarVagas;
    private JButton btnListarEstacionamentos;
    private JButton btnHistorico;

    public HomeView() {
        setTitle("Sistema de Estacionamento - Home");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Inicializa os botões
        btnAdicionarCliente = new JButton("Adicionar Cliente");
        btnGerenciarVagas = new JButton("Gerenciar Vagas");
        btnGerenciarEstacionamento = new JButton("Gerenciar Estacionamento");
        btnListarClientes = new JButton("Listar Clientes");
        btnListarVagas = new JButton("Listar Vagas");
        btnListarEstacionamentos = new JButton("Listar Estacionamentos");
        btnHistorico = new JButton("Histórico");

        // Adiciona ActionListeners para abrir cada view correspondente
        btnAdicionarCliente.addActionListener(e -> abrirAdicionarClienteView());
        btnGerenciarVagas.addActionListener(e -> abrirAddVeiculoView());
        btnGerenciarEstacionamento.addActionListener(e -> abrirGerenciarEstacionamentoView());
        btnListarClientes.addActionListener(e -> abrirListarClientesView());
        btnListarVagas.addActionListener(e -> abrirListarVagasView());
        btnListarEstacionamentos.addActionListener(e -> abrirListarEstacionamentosView());
        btnHistorico.addActionListener(e -> abrirHistoricoView());

        // Adiciona os botões à janela
        add(btnAdicionarCliente);
        add(btnGerenciarVagas);
        add(btnGerenciarEstacionamento);
        add(btnListarClientes);
        add(btnListarVagas);
        add(btnListarEstacionamentos);
        add(btnHistorico);
    }

    // Métodos para abrir as diferentes views
   
    private void abrirHistoricoView() {
        Historico historico = new Historico();
        historico.setVisible(true);
    }

    private void abrirAdicionarClienteView() {
        AddClienteView adicionarClienteView = new AddClienteView();
        adicionarClienteView.setVisible(true);
    }

    private void abrirAddVeiculoView() {
        AddVeiculoView AddVeiculoView = new AddVeiculoView();
        AddVeiculoView.setVisible(true);
    }

    private void abrirGerenciarEstacionamentoView() {
        AddEstacionamentoView gerenciarEstacionamentoView = new AddEstacionamentoView();
        gerenciarEstacionamentoView.setVisible(true);
    }

    private void abrirListarClientesView() {
        ListarClientesView listarClientesView = new ListarClientesView();
        listarClientesView.setVisible(true);
    }

    private void abrirListarVagasView() {
        ListarVagaView listarVagasView = new ListarVagaView();
        listarVagasView.setVisible(true);
    }

    private void abrirListarEstacionamentosView() {
        ListarEstacionamentosView listarEstacionamentosView = new ListarEstacionamentosView();
        listarEstacionamentosView.setVisible(true);
    }
}
