package utilidade;

import java.util.Scanner;

public class Leitora_de_dados {
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

