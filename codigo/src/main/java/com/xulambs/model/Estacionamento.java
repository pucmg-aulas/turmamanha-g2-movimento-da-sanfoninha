package com.xulambs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Estacionamento {
    private UUID id;
    private String nome;
    private Map<UUID, Vaga> vagas;
    private int quantidadeFileiras;
    private int vagasPorFileira;
    private double totalArrecadado;
    private List<RegistroArrecadacao> registrosArrecadacao = new ArrayList<>();

    // Exceções personalizadas (mova para arquivos separados se preferir)
    public static class EstacionamentoCheioException extends RuntimeException {
        public EstacionamentoCheioException(String message) { super(message); }
    }
    public static class VeiculoNaoEncontradoException extends RuntimeException {
        public VeiculoNaoEncontradoException(String message) { super(message); }
    }
    public static class VagaOcupadaException extends RuntimeException {
        public VagaOcupadaException(String message) { super(message); }
    }
    public static class VagaInvalidaException extends RuntimeException {
        public VagaInvalidaException(String message) { super(message); }
    }

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.id = UUID.randomUUID(); // Sets the ID here
        this.nome = nome;
        this.quantidadeFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new HashMap<>();
        this.totalArrecadado = 0;
        gerarVagas();
    }
    

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Map<UUID, Vaga> getVagas() { return new HashMap<>(vagas);} //Retorna uma cópia
    public int getQuantidadeFileiras() { return quantidadeFileiras; }
    public int getVagasPorFileira() { return vagasPorFileira; }
    public int getQuantidadeTotalVagas() { return quantidadeFileiras * vagasPorFileira; }
    public double getTotalArrecadado() { return totalArrecadado; }
    public List<RegistroArrecadacao> getRegistrosArrecadacao() {return  new ArrayList<>(registrosArrecadacao); }//Retorna cópia


    private void gerarVagas() {
        for (int i = 0; i < quantidadeFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                UUID id = UUID.randomUUID();
                // Crie diferentes tipos de vaga aqui (Comum, Idoso, PCD, VIP)
                // e adicione-as ao Map com seus respectivos UUIDs.
                Vaga vaga = new VagaComum(i, j, "Média", 10.0, 2.0); // Exemplo, adapte
                vagas.put(id, vaga);
            }
        }
    }

    public Vaga encontrarVagaPorId(UUID vagaId) {
        return vagas.get(vagaId);
    }

    public boolean vagaExiste(Vaga vaga) {
        return vagas.containsKey(vaga.getId());
    }

    public void ocuparVaga(UUID vagaId, Veiculo veiculo) {
        Vaga vaga = encontrarVagaPorId(vagaId);
        if (vaga == null) {
            throw new VagaInvalidaException("Vaga com ID " + vagaId + " não encontrada.");
        }
        if (vaga.isOcupada()) {
            throw new VagaOcupadaException("Vaga " + vaga.getIdentificacao() + " já está ocupada.");
        }
        vaga.estacionar(veiculo);
    }


    public double liberarVaga(UUID vagaId) {
         Vaga vaga = encontrarVagaPorId(vagaId);
        if (vaga == null) {
           throw new VagaInvalidaException("Vaga não encontrada");
        }
        if (!vaga.isOcupada()) {
            throw new VagaInvalidaException("Vaga já está desocupada");
        }
        double valorPago = vaga.sair();
        totalArrecadado += valorPago;
        registrosArrecadacao.add(new RegistroArrecadacao(vaga.getVeiculo().getPlaca(), valorPago, LocalDate.now()));
        return valorPago;
    }



    // ... outros métodos para cálculos de relatórios (implemente-os) ...

    class RegistroArrecadacao {
        String placa;
        double valorPago;
        LocalDate data;

        public RegistroArrecadacao(String placa, double valorPago, LocalDate data) {
            this.placa = placa;
            this.valorPago = valorPago;
            this.data = data;
        }

        public String getPlaca(){ return placa; }
        public double getValorPago(){ return valorPago; }
        public LocalDate getData(){ return data; }

    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}