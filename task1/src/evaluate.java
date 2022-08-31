import Suporte.InvalidCharException;

import java.util.*;


// Classe responsável pela stack e avaliação.
public class evaluate{
    // stack
    public Stack<Double> stk = new Stack<Double>();

    // Operadores disponíveis
    private OpMap op = new OpMap();

    // Verifica se duas Strings são iguais
    public Boolean isEqual(String a, String b) {return Objects.equals(a, b);};

    // Verifica se a stack está vazia
    public Boolean isEmpty()
    {
      return stk.isEmpty();
    };

    // Verifica se a stack apenas contem o resultado
    public Boolean isOver()
    {
        return (stk.size() == 1);
    };

    // faz o pop do resultado
    public Double resultado()
    {
        return stk.pop();
    }


    // Next
    // Recebe uma String, se ela for numero adiciona na stack
    // Se for um operador então faz o pop dos 2 elementos no tempo da stack e realiza essa operação
    // Além disso, indentifica se é uma expressão invalida ou incompleta
    public void next(String v) throws EmptyStackException, InvalidCharException {
        try{
           double numero = Double.parseDouble(v);
           stk.push(numero);
        } catch (NumberFormatException e) {
            if(op.ehOperator(v)) {
                try{
                    stk.push(op.calc(v, stk.pop(), stk.pop()));
                } catch (EmptyStackException o){ throw o; }
            } else{
                throw new InvalidCharException("Caracter Invalido");
            }
        }
    }

    // OpMap é responsável por armazenar as possíveis operações aritméticas e aplica-las
    // Usa-se um hashMap para acessar a função arimética, onde a chave é a sua versão em String
    public static class OpMap {
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

        // Interface para representar as operações aritiméticas (+, - , *, /, ^)
        static interface Operator{
            Double calc(Double a, Double b);
        }
    }
}
