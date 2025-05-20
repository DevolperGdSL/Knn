package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.LeitorDeTxt;

public class Knn {
    /**
     * Executa o knn e retorna a Matriz de confusão
     * @param k
     * @return
     */
    public static double[][] executar(int k){

        double[][]objetos = LeitorDeTxt.lerMatrizDeArquivo("src/data/Objetos.txt");

        double[][]base = LeitorDeTxt.lerMatrizDeArquivo("src/data/Base_sintética.txt");

        double[][]rotulosDeObjetos = LeitorDeTxt.lerMatrizDeArquivo("src/data/rotulos/Objetos_rotulos.txt");

        double[][]rotulosDaBase = LeitorDeTxt.lerMatrizDeArquivo("src/data/rotulos/Base_sintetica_rotulos.txt");

        double[][]pertinencia = distanciaEuclidiana(objetos, base);

        double[][]rotulosEstimados = classificaObjetos(pertinencia, rotulosDaBase, k);

        double[][]matrizConfusao = new double[rotulosDeObjetos.length][2] ;
        
        for (int i = 0; i < rotulosEstimados.length; i++) {
            matrizConfusao[i][0] = rotulosEstimados[i][0];
            matrizConfusao[i][1] = rotulosDeObjetos[i][0];  
        }
        return matrizConfusao;
    }
    /**
     * Recebe duas matrizes e calcula a distância euclidiana entre seus objetos, criando uma matriz de pertinencia
     * @param objetos
     * @param base
     * @return pertinencia
     */
    public static double[][] distanciaEuclidiana(double[][] objetos, double[][] base){
        int linhas = base.length;
        int colunas = objetos.length;
        double somaDosQuadrados = 0;
        double pertinencia[][] = new double[linhas][colunas];
        for(int i = 0; i<objetos.length; i++){
            for(int j=0; j< base.length; j++){
                somaDosQuadrados = 0;
                for(int k=0; k<base[0].length; k++){
                    somaDosQuadrados += Math.pow(base[j][k] - objetos[i][k], 2); 
                }
                pertinencia[j][i] = Math.sqrt(somaDosQuadrados);
            }
        }
        return pertinencia;
    }
    /**
     * Separa a matriz em varios vetores, que são as colunas dessa matriz, 
     * retorna uma lista com a posição dos n menores valores de cada coluna 
     * @param matriz
     * @param n
     * @return Lista de vetores
     */
    public static List<double[]> encontraMenorValorPorColuna(double[][] matriz, int n) {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        List<double[]> resultado = new ArrayList<>();
        
        for (int coluna = 0; coluna < colunas; coluna++) {
            // Cria uma lista para armazenar pares de valor
            List<double[]> parValorIndice = new ArrayList<>();
            for (int linha = 0; linha < linhas; linha++) {
                parValorIndice.add(new double[]{matriz[linha][coluna], linha});
            }
            
            // Ordena os pares pela coluna, baseando-se nos valores
            parValorIndice.sort(Comparator.comparingDouble(a -> a[0]));
            
            // Adiciona os n menores valores com índices à lista de resultado
            for (int i = 0; i < Math.min(n, parValorIndice.size()); i++) {
                resultado.add(new double[]{coluna, parValorIndice.get(i)[1]});
            }
        }
        
        return resultado;
    }

    public static Double encontrarModa(double[] vetor) {
        // Mapa para armazenar a frequência de cada elemento
        Map<Double, Integer> frequencia = new HashMap<>();
        
        // Contagem das ocorrências de cada valor
        for (double valor : vetor) {
            frequencia.put(valor, frequencia.getOrDefault(valor, 0) + 1);
        }
        
        // Variáveis para guardar a moda e a maior frequência encontrada
        double moda = vetor[0];
        int maiorFrequencia = 0;
        
        // Encontra o valor com a maior frequência
        for (Map.Entry<Double, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maiorFrequencia) {
                moda = entry.getKey();
                maiorFrequencia = entry.getValue();
            }
        }
        
        return moda;
    }
    /**
     * Estima os rotulos dos objetos a serem classificados, com base na matriz de pertimencia e nos rotulos da base
     * @param pertinencia
     * @param rotulosDaBase
     * @param k
     * @return rotulos estimados dos objetos
     */
    public static double[][] classificaObjetos(double[][] pertinencia, double[][] rotulosDaBase, int k){
        int numeroDeObjetos = pertinencia[0].length; //Numero de objetos a serem avaliados
        double[][] rotulosEstimadosDosObjetos = new double[numeroDeObjetos][1];
        double[] rotulosDosVizinhos = new double[k];
        List<double[]> vizinhosMaisProximos = encontraMenorValorPorColuna(pertinencia, k);//Lista ordenada dos vizinhos mais proximos
        int cont = 0;
        for (int i = 0; i < numeroDeObjetos; i++) {
            for (int j = 0; j < k; j++) {
                double[] valueIndex = vizinhosMaisProximos.get(cont);
                int linhaOriginal = (int)valueIndex[1];
                rotulosDosVizinhos[j] = rotulosDaBase[linhaOriginal][0];
                cont++;
            }
            rotulosEstimadosDosObjetos[i][0] = encontrarModa(rotulosDosVizinhos);
        }
        return rotulosEstimadosDosObjetos;
    }
}
