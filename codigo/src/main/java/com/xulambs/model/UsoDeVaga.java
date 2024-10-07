// package com.xulambs.model;

// import java.time.LocalDateTime;
// import java.time.Duration;


// public class UsoDeVaga {
//     private static final double FRACAO_USO = 0.15;
//     private static final double VALOR_FRACAO = 4.0;
//     private static final double VALOR_MAXIMO = 50.0;
    
//     private Vaga vagas;
//     private LocalDateTime entrada;
//     private LocalDateTime saida;
//     private double valorPago;
    
//     public UsoDeVaga(Vaga vaga) {
//         this.vaga = vaga;
//         this.entrada = LocalDateTime.now();
//     }

//     public void calcularValorPago() {
//         this.saida = LocalDateTime.now();
//         Duration duracao = Duration.between(entrada, saida);
//         long minutos = duracao.toMinutes();
//         long fracoes = minutos / 15;
        
//         valorPago = fracoes * VALOR_FRACAO;
        
//         if (valorPago > VALOR_MAXIMO) {
//             valorPago = VALOR_MAXIMO;
//         }

//         switch (vaga.getTipoVaga()) {
//             case "IDOSO":
//                 valorPago -= valorPago * 0.15; 
//                 break;
//             case "PCD":
//                 valorPago -= valorPago * 0.13;  
//                 break;
//             case "VIP":
//                 valorPago += valorPago * 0.20;  
//                 break;
//             default:
//                 break;
//         }
//     }

//     public LocalDateTime getEntrada() {
//         return entrada;
//     }

//     public LocalDateTime getSaida() {
//         return saida;
//     }

//     public double getValorPago() {
//         return valorPago;
//     }

//     public Vaga getVaga() {
//         return vaga;
//     }
    
//     public void setSaida(LocalDateTime saida) {
//         this.saida = saida;
//     }

//     @Override
//     public String toString() {
//         return "UsoDeVaga [vaga=" + vaga.getIdentificacao() + ", entrada=" + entrada + ", saida=" + saida + ", valorPago=" + valorPago + "]";
//     }
// }
