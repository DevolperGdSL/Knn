package funções;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ferramentas {

    public double[][] dist_eucli(double[][] matriz_objeto, double[][] matriz_base){
        int linhas = matriz_base.length;
        int colunas = matriz_objeto.length;
        double somaDosQuadrados = 0;
        double pertinencia[][] = new double[linhas][colunas];
        for(int v = 0; v<matriz_objeto.length; v++){
            for(int o=0; o< matriz_base.length; o++){
                somaDosQuadrados = 0;
                for(int i=0; i<matriz_base[0].length; i++){
                    somaDosQuadrados += Math.pow(matriz_base[o][i] - matriz_objeto[v][i], 2); 
                }
                pertinencia[o][v] = Math.sqrt(somaDosQuadrados);
            }
        }
        return pertinencia;
    }

    public static List<double[]> findNMinValuesPerColumn(double[][] matrix, int n) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<double[]> result = new ArrayList<>();
        
        for (int col = 0; col < cols; col++) {
            // Cria uma lista para armazenar pares de valor e índice
            List<double[]> valueIndexPairs = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                valueIndexPairs.add(new double[]{matrix[row][col], row});
            }
            
            // Ordena os pares pela coluna, baseando-se nos valores
            valueIndexPairs.sort(Comparator.comparingDouble(a -> a[0]));
            
            // Adiciona os n menores valores com índices à lista de resultado
            for (int i = 0; i < Math.min(n, valueIndexPairs.size()); i++) {
                result.add(new double[]{col, valueIndexPairs.get(i)[1]});
            }
        }
        
        return result;
    }

    public Double encontrarModa(double[] vetor) {
        // Mapa para armazenar a frequência de cada elemento
        Map<Double, Integer> frequencia = new HashMap<>();
        
        // Contagem das ocorrências de cada valor
        for (double valor : vetor) {
            frequencia.put(valor, frequencia.getOrDefault(valor, 0) + 1);
        }
        
        // Variáveis para guardar a moda e a frequência máxima encontrada
        double moda = vetor[0];
        int maxFrequencia = 0;
        
        // Encontra o valor com a maior frequência
        for (Map.Entry<Double, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maxFrequencia) {
                moda = entry.getKey();
                maxFrequencia = entry.getValue();
            }
        }
        
        return moda;
    }

    public double[][] ident_vizinho(double[][] matriz_pertinencia, double[][] rotulos_base, int k){
        int num_obj_base = matriz_pertinencia[0].length;
        double[][] matriz_rotulos_obj_estimados = new double[num_obj_base][1];
        double[] rotulos_vizinhos = new double[k];
        List<double[]> result = findNMinValuesPerColumn(matriz_pertinencia, k);
        int cont = 0;
        for (int i = 0; i < num_obj_base; i++) {
            for (int j = 0; j < k; j++) {
                double[] valueIndex = result.get(cont);
                int linhaOriginal = (int)valueIndex[1];
                rotulos_vizinhos[j] = rotulos_base[linhaOriginal][0];
                cont++;
            }
            matriz_rotulos_obj_estimados[i][0] = encontrarModa(rotulos_vizinhos);
        }
        return matriz_rotulos_obj_estimados;
    }
    


}
