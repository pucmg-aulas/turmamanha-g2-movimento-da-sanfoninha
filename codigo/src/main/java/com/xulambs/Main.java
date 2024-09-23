import com.xulambs.model.*;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 3, 5);

        Cliente cliente1 = new Cliente("Giovanna");
        Cliente cliente2 = new Cliente("Debora");
        Cliente cliente3 = new Cliente("Maria Clara");

        Veiculo veiculo1 = new Veiculo("ABC-1234");
        Veiculo veiculo2 = new Veiculo("XYZ-5678");
        Veiculo veiculo3 = new Veiculo("DEF-1478");

        cliente1.adicionarVeiculo(veiculo1);
        cliente2.adicionarVeiculo(veiculo2);
        cliente3.adicionarVeiculo(veiculo3);

        System.out.println("Estacionando o veículo ABC-1234");
        estacionamento.estacionar(veiculo1);
        System.out.println();

        System.out.println("Estacionando o veículo XYZ-5678");
        estacionamento.estacionar(veiculo2);
        System.out.println();

        System.out.println("Estacionando o veículo DEF-1478");
        estacionamento.estacionar(veiculo3);
        System.out.println();

        System.out.println("Saída do veículo ABC-1234");
        estacionamento.sair("ABC-1234");
        System.out.println();

        System.out.println("Calculando valor do uso de vaga para o veículo ABC-1234");
        estacionamento.valorPorUso("ABC-1234", 4.0, 3);
        System.out.println();

        System.out.println("Calculando valor do uso de vaga para o veículo XYZ-5678");
        estacionamento.valorPorUso("XYZ-5678", 4.0, 4);
        System.out.println();

        System.out.println("Calculando valor do uso de vaga para o veículo  DEF-1478");
        estacionamento.valorPorUso(" DEF-1478", 4.0, 2);
        System.out.println();

    }
}
