import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StkScanner {
    private Scanner stkscanner;
    private final OpMap op = new OpMap();

    // Scanneia o arquivo e gera os tokens.
    // Levanta erro caso o token não seja reconhecido.
    public ArrayList<Token> scan() throws Main.InvalidCharException {
        ArrayList<Token> tokens = new ArrayList<>();
        while (this.stkscanner.hasNextLine()) {
            String el = this.stkscanner.nextLine().strip();
            try {
                Token curToken = new Token(typeOf(el), el);
                System.out.println(curToken);
                tokens.add(curToken);
            }catch (Main.InvalidCharException e) {
                System.out.println("Error: Unexpected character: '" + el + "'");
                throw e;
            }
        }
        return tokens;
    }

    // Responsável por lidar com o arquivo e Criar o scanner.
    public Scanner getstkScanner() {
        Scanner stkscan = null;

        Scanner cin = new Scanner(System.in);
        System.out.println("Você quer evaliar a expressão do arquivo teste.stk?\n1->Sim\n2->Nao");
        if (!Objects.equals(cin.nextLine(), "1")) {
            boolean NotValidPath = true;
            while (NotValidPath) {
                try {
                    stkscan = this.askForPath();
                    NotValidPath = false;
                } catch (FileNotFoundException e) { System.out.println("Ctrl-C para sair do programa"); }
            }
        } else {
            try { stkscan = new Scanner(new File(System.getProperty("user.dir") + "/teste.stk"));
            } catch (FileNotFoundException ignored) {}
        }
        this.stkscanner = stkscan;
        return stkscan;
    }

    // Constructor
    public StkScanner() {}

    /*
        Helpers functions
     */
    // Retorna o tipo do token.
    // Retorna um e
    private TokenType typeOf(String el) throws Main.InvalidCharException {
        TokenType tType;
        try {
            Double.parseDouble(el);
            tType = TokenType.NUM;
        } catch (NumberFormatException e) { tType = op.getType(el); }

        if (tType == TokenType.NOP ) throw new Main.InvalidCharException(el);
        else return tType;
    }

    private Scanner askForPath() throws FileNotFoundException {
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

    //
    // Helper Class
    //

    // Class to handle mapping String to the TokenType and its OPERATOR
    public static class OpMap {
        private final Map<String, OpPair> opMap = new HashMap<>();

        public OpMap() {
            opMap.put("+", new OpPair(TokenType.PLUS , ((a, b) -> a + b)));
            opMap.put("-", new OpPair(TokenType.MINUS, (a, b) -> a - b));
            opMap.put("*", new OpPair(TokenType.STAR, (a, b) -> a * b));
            opMap.put("/", new OpPair(TokenType.SLASH, (a, b) -> a / b));
            opMap.put("^", new OpPair(TokenType.POW ,  Math::pow));
        }

        // Faz o calculo do da expressão
        public Double calc(String o, double a, double b) {
            return opMap.get(o).opExp.calc(a, b);
        }

        // Verifica se uma String representa um operando
        public Boolean ehOperator(String o) {
            return !Objects.isNull(opMap.get(o));
        }

        public TokenType getType(String o){ if(ehOperator(o)){ return opMap.get(o).opType;} return TokenType.NOP;};

        // Interface para representar as operações aritiméticas (+, - , *, /, ^)
        interface Operator {
            Double calc(Double a, Double b);
        }

        public static class OpPair {
            public TokenType opType;
            public Operator opExp;
            public OpPair(TokenType tType, Operator operator){
                opType = tType;
                opExp = operator;
            }
        }
    }
}
