package Suporte;

// Exception para um input que não representa um numero nem um operado
public class InvalidCharException extends Exception {
    public InvalidCharException(String errorMessage) {
        super(errorMessage);
    }
}
