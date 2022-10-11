package RPN.opMap;

import RPN.tokens.TokenType;

import java.util.HashMap;
import java.util.Map;

// Class to handle mapping String to the RPN.tokens.TokenType and its corresponding expression
// (Soma, Subtração, Multiplicação, Divisão, Potência)
public class OpMap {
    private final Map<TokenType, Operator> opMap = new HashMap<>();

    public OpMap() {
        opMap.put(TokenType.PLUS, ((a, b) -> a + b));
        opMap.put(TokenType.MINUS, (a, b) -> a - b);
        opMap.put(TokenType.STAR, (a, b) -> a * b);
        opMap.put(TokenType.SLASH, (a, b) -> a / b);
        opMap.put(TokenType.POW, Math::pow);
    }

    // Faz o calculo do da expressão
    public Double calc(TokenType o, double a, double b) {
        return opMap.get(o).calc(a, b);
    }

    // Interface para representar as operações aritiméticas (+, - , *, /, ^)
    interface Operator {
        Double calc(Double a, Double b);
    }
}
