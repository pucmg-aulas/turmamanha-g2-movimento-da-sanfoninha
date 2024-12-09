import java.sql.Connection;    // Para a conexão com o banco de dados
import java.sql.DriverManager; // Para carregar o driver JDBC (se necessário)
import java.sql.ResultSet;     // Para armazenar o resultado da consulta
import java.sql.Statement;     // Para executar a consulta SQL
import java.sql.SQLException;  // Para capturar exceções relacionadas ao SQL
import exceptions.ConsultaInvalidaException;


public class Consulta {
    
    // Método para recuperar o total pago por cliente
    public void totalPagoPorCliente() {
        String query = "SELECT C.NOME, C.CPF, SUM(U.ValorPago) AS TotalPago " +
                       "FROM CLIENTE C " +
                       "JOIN VEICULO V ON C.CPF = V.CPF_CLIENTE " +
                       "JOIN USODEVAGA U ON V.PLACA = U.Placa " +
                       "WHERE U.Entrada >= '2024-12-01 00:00:00' AND U.Saida <= '2024-12-31 23:59:59' " +
                       "GROUP BY C.CPF, C.NOME " +
                       "ORDER BY TotalPago DESC";
        
        // Usando a classe ConexaoBanco para conectar
        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Processa os resultados
            while (rs.next()) {
                String nome = rs.getString("NOME");
                String cpf = rs.getString("CPF");
                double totalPago = rs.getDouble("TotalPago");
                
                // Exibe os resultados ou retorna para a interface gráfica
                System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Total Pago: R$" + totalPago);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vagasOcupadasPorTipoEFila() {
        // Definindo a consulta SQL
        String query = "SELECT V.TIPO, V.FILA, COUNT(U.Numero) AS VagasOcupadas " +
                       "FROM VAGA V " +
                       "LEFT JOIN USODEVAGA U ON V.NUMERO = U.Numero AND V.FILA = U.Fila " +
                       "GROUP BY V.TIPO, V.FILA " +
                       "ORDER BY V.TIPO, V.FILA";
        
        // Usando a classe ConexaoBanco para obter a conexão com o banco
        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Iterando sobre os resultados da consulta
            while (rs.next()) {
                // Obtendo os valores de cada linha retornada pela consulta
                String tipo = rs.getString("TIPO");
                String fila = rs.getString("FILA");
                int vagasOcupadas = rs.getInt("VagasOcupadas");
                
                // Exibindo os resultados no console (ou você pode exibir na interface gráfica)
                System.out.println("Tipo: " + tipo + " | Fila: " + fila + " | Vagas Ocupadas: " + vagasOcupadas);
            }
            
        } catch (SQLException e) {
            // Tratamento de exceção caso ocorra um erro na consulta ou na conexão com o banco
            e.printStackTrace();
        }
    }
    
    public void realizarConsulta(String query) throws ConsultaInvalidaException {
        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.next()) {
                throw new ConsultaInvalidaException("Nenhum resultado encontrado para a consulta.");
            }

            // Processar resultados
        } catch (SQLException e) {
            throw new ConsultaInvalidaException("Erro ao executar a consulta SQL.", e);
        }
    }

}

