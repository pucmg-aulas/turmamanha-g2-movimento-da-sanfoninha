package com.xulambs.controllers;

import com.xulambs.daos.EstacionamentoDAO;
import com.xulambs.daos.HistoricoUsoDAO;
import com.xulambs.daos.VeiculoDAO;
import com.xulambs.model.Cliente;
import com.xulambs.model.Estacionamento;
import com.xulambs.model.HistoricoUso;
//import com.xulambs.model.RegistroArrecadacao;
import com.xulambs.model.Veiculo;
import main.java.com.xulambs.view.HistoricoView;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class RelatorioController {

    private EstacionamentoDAO estacionamentoDAO;;
    private HistoricoUsoDAO historicoUsoDAO; //Adicione o DAO
    private HistoricoView historicoView;
    private VeiculoDAO veiculoDAO;


    public RelatorioController(EstacionamentoDAO estacionamentoDAO, VeiculoDAO veiculoDAO, HistoricoUsoDAO historicoUsoDAO, HistoricoView historicoView) {
        this.estacionamentoDAO = estacionamentoDAO;
        this.veiculoDAO = veiculoDAO;
        this.historicoUsoDAO = historicoUsoDAO;
        this.historicoView = historicoView;
    }


    public void abrirHistoricoView(){
        if (historicoView != null) { 
            historicoView.carregarDadosNaTabela();
            historicoView.setVisible(true);
        } else {
            System.err.println("Erro: historicoView não foi injetada.");
        }
}

    //Adicione este método:
    public List<HistoricoUso> obterHistorico() {
        return historicoUsoDAO.listarTodos();
    }

    public void setHistoricoView(HistoricoView historicoView) {
        this.historicoView = historicoView;
    }


    public void gerarRelatorioEstacionamento(UUID estacionamentoId, int mes, int ano) {
        double totalArrecadado = calcularTotalArrecadado(estacionamentoId);
        double arrecadacaoMensal = calcularArrecadacaoPorMes(estacionamentoId, mes, ano);
        double valorMedio = calcularValorMedioPorUtilizacao(estacionamentoId);
        Map<String, Double> rankingClientes = exibirRankingClientes(estacionamentoId, mes, ano);

        // Exibe os resultados no HistoricoView (adapte conforme a estrutura da sua view)
        historicoView.exibirRelatorio(totalArrecadado, arrecadacaoMensal, valorMedio, rankingClientes);
        historicoView.setVisible(true);

        try{
             if(estacionamentoId == null){
                 throw new IllegalArgumentException("Estacionamento inválido.");
            }

            //Validação de data (implemente validações mais robustas se necessário)
            if(mes < 1 || mes > 12 || ano <=0){
                throw new IllegalArgumentException("Data inválida.");
            }


            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if (estacionamento == null) {
                throw new IllegalArgumentException("Estacionamento não encontrado.");
            }

            //Calcula totais e valores para o relatório.
            calcularTotalArrecadado(estacionamentoId); //implementar
            calcularArrecadacaoPorMes(estacionamentoId, mes, ano); //implementar
            calcularValorMedioPorUtilizacao(estacionamentoId); //implementar
            exibirRankingClientes(estacionamentoId, mes, ano);//implementar


        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(historicoView, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(historicoView, "Erro ao gerar relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();//Log adequadamente
        }
    }



    public double calcularTotalArrecadado(UUID estacionamentoId) {
        Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
        if (estacionamento == null) {
            //Trate o erro adequadamente, lance uma exceção ou retorne um valor padrão
            return 0;
        }
        return estacionamento.getTotalArrecadado();
    }


    public double calcularArrecadacaoPorMes(UUID estacionamentoId, int mes, int ano) {
        try{

            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if (estacionamento == null) {
                //Trate o erro adequadamente
                return 0;
            }

            double totalMes = 0;
            for (RegistroArrecadacao registro : estacionamento.getRegistrosArrecadacao()) {

                if (registro.getData().getYear() == ano && registro.getData().getMonthValue() == mes) {
                    totalMes += registro.getValorPago();
                }
            }
            return totalMes;


        }catch (Exception e){
            //Trate a exceção e retorne 0 ou lance uma exceção.
            return 0;
        }
    }



    public double calcularValorMedioPorUtilizacao(UUID estacionamentoId) {

        try{
            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if(estacionamento == null){
                 return 0; //Ou lance uma exceção
            }
             List<RegistroArrecadacao> registros = estacionamento.getRegistrosArrecadacao();
            if (registros.isEmpty()) {
                 return 0;
            }

            double totalArrecadado = registros.stream().mapToDouble(RegistroArrecadacao::getValorPago).sum();

            return totalArrecadado / registros.size();

        }catch (Exception e){
            //Trate a exceção
            return 0;//Ou lance uma exceção
        }

    }


    public Map<String, Double> exibirRankingClientes(UUID estacionamentoId, int mes, int ano) {
        try{
            Estacionamento estacionamento = estacionamentoDAO.buscarPorId(estacionamentoId);
            if(estacionamento == null){
                //Trate o erro
                return new TreeMap<>(); //Ou lance uma exceção
            }

            Map<String, Double> rankingClientes = new TreeMap<>();
            for (RegistroArrecadacao registro : estacionamento.getRegistrosArrecadacao()) {
                if (registro.getData().getYear() == ano && registro.getData().getMonthValue() == mes) {
                    //Aqui você precisa associar a placa ao cliente.  Como seu modelo não tem essa informação diretamente,
                    //precisará buscar o cliente pelo veículo no VeiculoDAO
                    //e depois acessar o CPF do cliente.


                    //Exemplo (adapte):
                    Veiculo veiculo = new VeiculoDAO().buscarPorPlaca(registro.getPlaca());
                    if(veiculo != null){
                        Cliente cliente = veiculo.getCliente();
                        if(cliente != null){
                           rankingClientes.put(cliente.getCpf(), rankingClientes.getOrDefault(cliente.getCpf(), 0.0) + registro.getValorPago());
                        }
                    }
                }
            }


            //Ordena o ranking (opcional):
            return rankingClientes.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(java.util.stream.Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    java.util.LinkedHashMap::new
                ));




        }catch (Exception e){
            //Lidar com a exceção de forma mais apropriada
            e.printStackTrace();
            return new TreeMap<>();
        }

    }

    public List<Estacionamento> obterEstacionamentos() {
        return estacionamentoDAO.listarTodos();
    }
    
}