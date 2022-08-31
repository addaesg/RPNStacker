import Suporte.InvalidCharException;
import Suporte.StkScanner;

import java.util.EmptyStackException;
import java.util.Scanner;

// Toda a manipulação da Stack e Evalições de expressões ocorrem no arquivo: evaluate.java
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
}
