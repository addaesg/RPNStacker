package Suporte;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// OpMap é responsável por armazenar as possíveis operações aritméticas e aplica-las
// Usa-se um hashMap para acessar a função arimética, onde a chave é a sua versão em String
public class OpMap {
    private Map<String, Operator> opMap = new HashMap<String, Operator>();

    public OpMap() {
        opMap.put("+", new Operator() {
            @Override
            public Double calc(Double a, Double b) {
                return a + b;
            }
        });
        opMap.put("-", new Operator() {
            @Override
            public Double calc(Double a, Double b) {
                return a - b;
            }
        });
        opMap.put("*", new Operator() {
            @Override
            public Double calc(Double a, Double b) {
                return a * b;
            }
        });
        opMap.put("/", new Operator() {
            @Override
            public Double calc(Double a, Double b) {
                return a / b;
            }
        });

        opMap.put("^", new Operator() {
            @Override
            public Double calc(Double a, Double b) {return Math.pow(a, b);}
        });

    }

    ;

    // Faz o calculo do da expressão
    public Double calc(String o, double a, double b) {
        return opMap.get(o).calc(a, b);
    }


    // Verifica se uma String representa um operando
    public Boolean ehOperator(String o) {
        return !Objects.isNull(opMap.get(o));
    }
}
