package util;

import java.util.Scanner;

public class LeitoraDeDados {
    public static Scanner scanner;
    static{
        scanner = new Scanner(System.in);
    }
    /**Faz a leitura de um tipo num */
    public static int lerNum(){
        int numero = scanner.nextInt();
        return numero;
    }
}

