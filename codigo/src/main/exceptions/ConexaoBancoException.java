package exceptions;

// Exceção para erros de conexão com o banco de dados
public class ConexaoBancoException extends Exception {
    public ConexaoBancoException(String mensagem) {
        super(mensagem);
    }

    public ConexaoBancoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
