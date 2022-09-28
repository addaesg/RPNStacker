import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Toda a manipulação da Stack e Evalições de expressões ocorrem na classe: evaluate
public class Main{
    public static void main(String[] args)
    {
        // Scanner com os dados do arquivo
        Scanner scan =  new StkScanner().getstkScanner();

        // Classe responsável pela Stack e as operações aritiméticas
        evaluate eva = new evaluate();

        while(scan.hasNextLine())
        {
            try{
                String el = scan.nextLine();
                eva.next(el);
            }
            catch (EmptyStackException o) {System.out.println("Expressão incompleta"); return;}
            catch (InvalidCharException e){System.out.println("Caractere não reconhecido"); return;}
        }
        if(eva.isOver()) {
            System.out.println("Resultado: " + eva.resultado());
        } else { System.out.println("Expressão incompleta"); }
    };

    // Classe responsável pela stack e avaliação.
    public static class evaluate{
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
                return opMap.get(o).calc(b, a);
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

    // Classe para lidar com o arquivo.stk
    public static class StkScanner{

        public Scanner askForPath() throws FileNotFoundException {
            try {
                Scanner cin = new Scanner(System.in);
                System.out.println("Insira o path absoluto do arquivo .stk com a expressão RPN");
                String path = cin.nextLine();
                return new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                System.out.println("Path inválido");
                throw e;
            }
        }


        public Scanner getstkScanner()
        {
            Scanner stkscan = null;

            Scanner cin = new Scanner(System.in);
            System.out.println("Você quer evaliar a expressão do arquivo teste.stk?\n1->Sim\n2->Nao");
            if (Objects.equals(cin.nextLine(), "1") == false) {
                Boolean NotValidPath = true;
                while (NotValidPath) {
                    try {
                        stkscan = this.askForPath();
                        NotValidPath = false;
                    } catch (FileNotFoundException e) {
                        System.out.println("Ctrl-C para sair do programa");
                    }
                }
            } else {
                try {
                    stkscan = new Scanner(new File(System.getProperty("user.dir") + "/teste.stk"));
                } catch (FileNotFoundException e) {}
            }
            return stkscan;
        }
    }

    // Exception para um input que não representa um numero nem um operado
    public static class InvalidCharException extends Exception {
        public InvalidCharException(String errorMessage) {
            super(errorMessage);
        }
    }
}
