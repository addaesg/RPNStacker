import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Toda a manipulação da Stack e Evalições de expressões ocorrem na classe: evaluate
public class Main{
    public static void main(String[] args)
    {
        try{
            // Cria o scanner e recebe o path do arquivo
            StkScanner scanner =  new StkScanner();
            scanner.getstkScanner();

            // Scanneia o arquivo e mapeia para uma lista de tokens
            ArrayList<Token> tokens = scanner.scan();

            // Evalia as expressões da lista de tokens
            Evaluate eva = new Evaluate();
            Token resultado = eva.eval(tokens);

            // imprime o resultado
            System.out.println("O Resultado é: " + resultado.lexeme);
        } catch (Exception e){
            //  Aconteceu um erro
            System.out.println("Exiting...");
        }
    }
    public static class InvalidCharException extends Exception {
        public InvalidCharException(String errorMessage) {
            super(errorMessage);
        }
    }
}


