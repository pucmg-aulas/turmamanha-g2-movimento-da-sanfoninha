package exceptions;

// Exceção para consultas inválidas
public class ConsultaInvalidaException extends Exception {
    public ConsultaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public ConsultaInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}