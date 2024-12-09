package exceptions;

// Exceção para situações inesperadas de uso do estacionamento
public class UsoDeVagaException extends Exception {
    public UsoDeVagaException(String mensagem) {
        super(mensagem);
    }

    public UsoDeVagaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}