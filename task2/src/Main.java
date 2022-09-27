import RPN.Evaluate;
import RPN.StkScanner;
import RPN.tokens.Token;

import java.util.*;

// StkScanner é responsável pelo scan & gerar tokens
// Evaluate é responsável por evaliar as expressões de tokens.
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
}


