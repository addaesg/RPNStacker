package Suporte;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Classe para lidar com o arquivo.stk
public class StkScanner{

    public Scanner askForPath() throws FileNotFoundException{
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
