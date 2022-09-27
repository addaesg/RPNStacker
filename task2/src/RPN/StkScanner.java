package RPN;

import RPN.Exceptions.InvalidCharException;
import RPN.opMap.OpMap;
import RPN.tokens.Token;
import RPN.tokens.TokenType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/*
* StkScanner:
*     Classe responsável por:
*       - 1. Lidar com arquivo stk de input.
*       - 2. Scannear o arquivo e gerar os tokens correspondentes.
*       - 3. Levanta erro se não conseguir identificar um token.
* */
public class StkScanner {
    private Scanner stkscanner;
    private final OpMap op = new OpMap();

    // Scanneia o arquivo e gera os tokens.
    // Levanta erro caso o token não seja reconhecido.
    public ArrayList<Token> scan() throws InvalidCharException {
        ArrayList<Token> tokens = new ArrayList<>();
        while (this.stkscanner.hasNextLine()) {
            String el = this.stkscanner.nextLine().strip();
            try {
                Token curToken = new Token(typeOf(el), el);
                System.out.println(curToken);
                tokens.add(curToken);
            }catch (InvalidCharException e) {
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
    private TokenType typeOf(String el) throws InvalidCharException {
        TokenType tType;
        try {
            Double.parseDouble(el);
            tType = TokenType.NUM;
        } catch (NumberFormatException e) { tType = op.getType(el); }

        if (tType == TokenType.NOP ) throw new InvalidCharException(el);
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
}

//
// Helper Class
//

