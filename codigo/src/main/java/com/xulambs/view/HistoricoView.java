package main.java.com.xulambs.view;

import com.xulambs.controllers.RelatorioController;
import com.xulambs.model.HistoricoUso;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoricoView extends JFrame {
    private RelatorioController controller;
    private JTable tableHistorico;

    public HistoricoView(RelatorioController controller) {
        this.controller = controller;
        initComponents();
        setTitle("Histórico de Uso");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        JPanel painel = new JPanel(new BorderLayout());
        tableHistorico = new JTable();

        JScrollPane jScrollPane = new JScrollPane(tableHistorico);
        painel.add(jScrollPane, BorderLayout.CENTER);

        // Adicionar botão de atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarTabela());
        painel.add(btnAtualizar, BorderLayout.SOUTH);

        add(painel);

        // Carregar os dados iniciais
        carregarDadosNaTabela();
    }

    public void carregarDadosNaTabela() {
        try {
            List<HistoricoUso> historico = controller.obterHistorico(); // Método no controller
            tableHistorico.setModel(new HistoricoTableModel(historico));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar histórico: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void atualizarTabela() {
        carregarDadosNaTabela();
    }

    // Classe interna para modelagem da tabela
    private static class HistoricoTableModel extends AbstractTableModel {
        private final List<HistoricoUso> historico;
        private final String[] colunas = {
            "Cliente", "Placa do Veículo", "Vaga", "Entrada", "Saída",
            "Valor Pago", "Data Pagamento", "Tipo Pagamento"
        };

        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        public HistoricoTableModel(List<HistoricoUso> historico) {
            this.historico = historico;
        }

        @Override
        public int getRowCount() {
            return historico.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            HistoricoUso uso = historico.get(rowIndex);
            switch (columnIndex) {
                case 0: return uso.getCliente().getNome();
                case 1: return uso.getVeiculo().getPlaca();
                case 2: return uso.getVaga().getIdentificacao();
                case 3: return uso.getUsoDeVaga().getEntrada().format(formatter);
                case 4: return uso.getUsoDeVaga().getSaida() != null ? uso.getUsoDeVaga().getSaida().format(formatter) : "";
                case 5: return uso.getPagamento() != null ? uso.getPagamento().getValor() : "";
                case 6: return uso.getPagamento() != null && uso.getPagamento().getDataPagamento() != null
                        ? uso.getPagamento().getDataPagamento().format(formatter) : "";
                case 7: return uso.getPagamento() != null ? uso.getPagamento().getTipoPagamento() : "";
                default: return "";
            }
        }
    }

    public void setController(){
        
    }
}
