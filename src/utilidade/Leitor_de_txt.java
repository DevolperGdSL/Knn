package utilidade;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor_de_txt {
    /**
     * Lê uma matriz de double de um arquivo e retorna como uma matriz bidimensional.
     * 
     * @param caminhoArquivo O caminho do arquivo que contém a matriz.
     * @return A matriz de double lida do arquivo ou null se ocorrer um erro.
     */
    public static double[][] lerMatrizDeArquivo(String caminhoArquivo) {
        List<double[]> linhasMatriz = new ArrayList<>(); // Lista para armazenar as linhas da matriz

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                // Divide a linha em elementos, assumindo que os números são separados por espaços
                String[] elementos = linha.split(" ");
                double[] linhaMatriz = new double[elementos.length];

                // Converte os elementos para double
                for (int i = 0; i < elementos.length; i++) {
                    linhaMatriz[i] = Double.parseDouble(elementos[i]);
                }

                // Adiciona a linha à lista
                linhasMatriz.add(linhaMatriz);
            }

            // Converte a lista de linhas em uma matriz bidimensional de double
            double[][] matriz = new double[linhasMatriz.size()][];
            for (int i = 0; i < linhasMatriz.size(); i++) {
                matriz[i] = linhasMatriz.get(i);
            }

            return matriz; // Retorna a matriz

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter os dados para números: " + e.getMessage());
        }

        return null; // Retorna null se ocorrer um erro
    }
}
