package chamadas;

import funções.ferramentas;
import utilidade.Leitor_de_txt;

public class coracao_knn {
    public double[][] chamada(int k){
        Leitor_de_txt ler_txt = new Leitor_de_txt();
        ferramentas ferramenta = new ferramentas();

        double[][]matriz_objetos = ler_txt.lerMatrizDeArquivo("src\\banco_de_dados\\Objetos.txt");
        double[][]matriz_base = ler_txt.lerMatrizDeArquivo("src\\banco_de_dados\\Base_sintética.txt");
        double[][]rotulos_objetos = ler_txt.lerMatrizDeArquivo("src\\banco_de_dados\\rotulos\\Objetos_rotulos.txt");
        double[][]rotulos_base = ler_txt.lerMatrizDeArquivo("src\\banco_de_dados\\rotulos\\Base_sintetica_rotulos.txt");
        double[][]matriz_pertinencia = ferramenta.dist_eucli(matriz_objetos, matriz_base);
        double[][]vizinhos = ferramenta.ident_vizinho(matriz_pertinencia, rotulos_base, k);
        double[][] tabela = new double[rotulos_objetos.length][2] ;
        for (int i = 0; i < vizinhos.length; i++) {
            tabela[i][0] = vizinhos[i][0];
            tabela[i][1] = rotulos_objetos[i][0];  
        }
        return tabela;
    }
}
