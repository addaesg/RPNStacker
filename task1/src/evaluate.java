import Suporte.InvalidCharException;
import Suporte.OpMap;

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
}
