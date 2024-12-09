package conexao;

import java.sql.Connection;    // Para a conexão com o banco de dados
import java.sql.DriverManager; // Para carregar o driver JDBC (se necessário)
import java.sql.ResultSet;     // Para armazenar o resultado da consulta
import java.sql.Statement;     // Para executar a consulta SQL
import java.sql.SQLException;  // Para capturar exceções relacionadas ao SQL
import exceptions.ConexaoBancoException;



public class Conexao {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static final String user = "root";
    private static final String password = "root";


    private static Connection conn;


    public static Connection getConexao() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Connection conectar() throws ConexaoBancoException {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "root");
        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao conectar com o banco de dados.", e);
        }
    }
}
