package RPN;
import RPN.tokens.Token;
import RPN.tokens.TokenType;
import RPN.opMap.OpMap;

import java.util.*;

/*
* Evaluate: Classe responsável por calcular o valor de uma dada expressão de Tokens.
*           levantando erro caso seja uma expressão inválida.
* */
public class Evaluate {
    public Stack<Token> stk = new Stack<>();
    private final OpMap op = new OpMap();
    public Evaluate(){}

    // Evalia uma lista de Tokens.
    // Retorna o resultado da expressão
    // Identifica expressões invalidas.
    public Token eval(ArrayList<Token> tokens){
        for(Token token : tokens ){
            if(token.type == TokenType.NUM){
                stk.push(token);
            }
            else{
                try {
                    double b = Double.parseDouble(stk.pop().lexeme), a = Double.parseDouble(stk.pop().lexeme);
                    stk.push(new Token(TokenType.NUM, Double.toString(op.calc(token.lexeme, a, b))));
                } catch ( NumberFormatException e){
                    System.out.println("Error: Expressão Inválida: Esperava um NUM, recebeu OPERATOR");
                    throw e;
                } catch ( EmptyStackException o){
                    System.out.println("Error: Expressão Inválida: Esperava um número, recebeu Vazio");
                    throw o;
                }
            }
        }
        if(isOver()){
            return this.resultado();
        } else{
            System.out.println("Error: Expressão inválida: Existe mais de um Número na Stack após a sua evaliação");
            throw new IllegalArgumentException();
        }
    }

    // Verifica se a stack apenas contem o resultado
    public Boolean isOver() {
        return (stk.size() == 1);
    }
    // faz o pop do resultado
    public Token resultado() {
        return stk.pop();
    }
}