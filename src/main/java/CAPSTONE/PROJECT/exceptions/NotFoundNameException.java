package CAPSTONE.PROJECT.exceptions;

public class NotFoundNameException extends RuntimeException {
    public NotFoundNameException(String nome) {
        super("Elemento con nome " + nome + " non trovato!");
    }
}
