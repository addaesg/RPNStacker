package RPN;

import RPN.Exceptions.InvalidCharException;
import RPN.Regex.Regex;
import RPN.tokens.Token;
import RPN.tokens.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


/*
 * StkScanner:
 *     Classe responsável por:
 *       - 1. Lidar com arquivo stk de input.
 *       - 2. Scannear o arquivo e gerar os tokens correspondentes.
 *       - 3. Levanta erro se não conseguir identificar um token.
 * */
public class StkScanner {
    private Scanner stkscanner;
    private ArrayList<Token> tokens = new ArrayList<>();

    // Constructor
    public StkScanner() {
    }

    // Scanneia o arquivo e gera os tokens.
    // Levanta erro caso o token não seja reconhecido.
    // isNum e isOP estão implementadas, porém não precisaram ser utilizadas.

    public ArrayList<Token> scan() throws InvalidCharException {
        while (this.stkscanner.hasNextLine()) {
            String el = this.stkscanner.nextLine().strip();
            TokenType tokenType = null;

            // não é um caractere válido
            if(!(Regex.isNum(el) ||  Regex.isOP(el))){
                System.out.println("Error: Unexpected character: '" + el + "'");
                throw new InvalidCharException(el);
            }
            else if (Regex.isNum(el)) // é um numero
                tokenType = TokenType.NUM;
            else // é um operador
                tokenType = Regex.getTokenType(el);

            Token curToken = new Token(tokenType, el);
            tokens.add(curToken);
            System.out.println(curToken);
        }
        return tokens;
    }

    /*
        Helpers functions
     */
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
                } catch (FileNotFoundException e) {
                    System.out.println("Ctrl-C para sair do programa");
                }
            }
        } else {
            try {
                stkscan = new Scanner(new File(System.getProperty("user.dir") + "/teste.stk"));
            } catch (FileNotFoundException ignored) {}
        }
        this.stkscanner = stkscan;
        return stkscan;
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

