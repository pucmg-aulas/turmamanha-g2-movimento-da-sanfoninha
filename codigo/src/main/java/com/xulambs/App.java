package com.xulambs;

import com.xulambs.model.Cliente;
import com.xulambs.model.Estacionamento;
import com.xulambs.model.Veiculo;

public class App {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 3, 5); // 3 fileiras com 5 vagas cada

        Cliente cliente1 = new Cliente("Rayssa Pierre", "123");
        Cliente cliente2 = new Cliente("Giovanna Lima", "456");

        estacionamento.adicionarCliente(cliente1);
        estacionamento.adicionarCliente(cliente2);

        Veiculo veiculo1 = new Veiculo("ABC-1234");
        Veiculo veiculo2 = new Veiculo("XYZ-5678");

        estacionamento.adicionarVeiculo(veiculo1, "123");
        estacionamento.adicionarVeiculo(veiculo2, "456");

        System.out.println("Estacionando veículos:");
        estacionamento.estacionar("ABC-1234"); 
        estacionamento.estacionar("XYZ-5678"); 

        System.out.println("Saída de veículos: ");
        estacionamento.sair("ABC-1234");
        estacionamento.sair("XYZ-5678"); 

    }
}
