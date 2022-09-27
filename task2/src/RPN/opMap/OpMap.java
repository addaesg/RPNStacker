package RPN.opMap;

import RPN.tokens.TokenType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Class to handle mapping String to the RPN.tokens.TokenType and its corresponding expression
// (Soma, Subtração, Multiplicação, Divisão, Potência)
public class OpMap {
    private final Map<String, OpPair> opMap = new HashMap<>();

    public OpMap() {
        opMap.put("+", new OpPair(TokenType.PLUS, ((a, b) -> a + b)));
        opMap.put("-", new OpPair(TokenType.MINUS, (a, b) -> a - b));
        opMap.put("*", new OpPair(TokenType.STAR, (a, b) -> a * b));
        opMap.put("/", new OpPair(TokenType.SLASH, (a, b) -> a / b));
        opMap.put("^", new OpPair(TokenType.POW, Math::pow));
    }

    // Faz o calculo do da expressão
    public Double calc(String o, double a, double b) {
        return opMap.get(o).opExp.calc(a, b);
    }

    // Verifica se uma String representa um operando
    public Boolean ehOperator(String o) {
        return !Objects.isNull(opMap.get(o));
    }

    public TokenType getType(String o) {
        if (ehOperator(o)) {
            return opMap.get(o).opType;
        }
        return TokenType.NOP;
    }

    // Interface para representar as operações aritiméticas (+, - , *, /, ^)
    interface Operator {
        Double calc(Double a, Double b);
    }

    // Java não tem pair... Tive que fazer um improviso.
    public static class OpPair {
        public TokenType opType;
        public Operator opExp;

        public OpPair(TokenType tType, Operator operator) {
            opType = tType;
            opExp = operator;
        }
    }
}
