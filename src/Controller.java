
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import utilidade.Leitor_de_txt;

import java.util.ArrayList;
import java.util.List;

import chamadas.*;

public class Controller {

    @FXML
    private BubbleChart<Number, Number> bubbleChart; // Referência ao gráfico de bolas
    @FXML
    private Button addButton; // Botão para adicionar dados
    @FXML
    private TextField kInput; // Campo de texto para entrada do valor X
    @FXML
    private TableView<MyData> myTableView;
    @FXML
    private TableColumn<MyData, String> Objeto;
    @FXML
    private TableColumn<MyData, Double> R_KNN;
    @FXML
    private TableColumn<MyData, Double> R_Real;
    @FXML
    private TableColumn<MyData, String> Acerto;



    @FXML
    public void initialize() {
        Leitor_de_txt ler = new Leitor_de_txt();
        double[][] matriz_base = ler.lerMatrizDeArquivo("src//banco_de_dados//Base_sintética.txt");
        double[][] rotulos_base = ler.lerMatrizDeArquivo("src\\banco_de_dados\\rotulos\\Base_sintetica_rotulos.txt");
        double[][]matriz_objetos = ler.lerMatrizDeArquivo("src\\banco_de_dados\\Objetos.txt");
         // Configura a série de dados para objetos-1
         ObservableList<XYChart.Data<Number, Number>> bubbleData1 = FXCollections.observableArrayList();
         XYChart.Series<Number, Number> series = new XYChart.Series<>(bubbleData1);
         series.setName("Objetos da Base do tipo 1");
 
         for (int i = 0; i < matriz_base.length; i++) {
             if (rotulos_base[i][0] == 1) {
                XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(matriz_base[i][0], matriz_base[i][1], 0.0035);
                dataPoint.nodeProperty().addListener((obs, oldNode, newNode) ->{
                    if (newNode != null) {
                       newNode.setStyle("-fx-background-color: #FA8072;");
                    }
                       }
                    );
                bubbleData1.add(dataPoint);
             }
         }
 
         // Configura a série de dados para objetos-2
         ObservableList<XYChart.Data<Number, Number>> bubbleData2 = FXCollections.observableArrayList();
         XYChart.Series<Number, Number> series2 = new XYChart.Series<>(bubbleData2);
         series2.setName("Objetos da Base do tipo 2");
 
         for (int i = 0; i < matriz_base.length; i++) {
             if (rotulos_base[i][0] == 2) {
                 XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(matriz_base[i][0], matriz_base[i][1], 0.0035);
                 dataPoint.nodeProperty().addListener((obs, oldNode, newNode) ->{
                 if (newNode != null) {
                    newNode.setStyle("-fx-background-color: #FFA500;");
                 }
                    }
                 );
                 bubbleData2.add(dataPoint);
             }
         }
         
         ObservableList<XYChart.Data<Number, Number>> bubbleData3 = FXCollections.observableArrayList();
         XYChart.Series<Number, Number> series3 = new XYChart.Series<>(bubbleData3);
         series3.setName("Objetos a serem classificados");
         for (int i = 0; i < matriz_objetos.length; i++) {
             XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(matriz_objetos[i][0], matriz_objetos[i][1], 0.0035);
              // Define a cor do nó após ele ser criado
                dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-background-color: #006400;"); // Cor preta
                }
            });
             bubbleData3.add(dataPoint);
         }

         // Adiciona as séries ao gráfico
         bubbleChart.getData().add(series);
         bubbleChart.getData().add(series2);
         bubbleChart.getData().add(series3);
    }
    
    @FXML
    private void handleAddButtonAction() {
        Leitor_de_txt ler = new Leitor_de_txt();
        double[][]rotulos_objetos = ler.lerMatrizDeArquivo("src\\banco_de_dados\\rotulos\\Objetos_rotulos.txt");
        int k = Integer.parseInt(kInput.getText());
        coracao_knn chama = new coracao_knn();
        double[][] tabela = chama.chamada(k);

       
        Objeto.setCellValueFactory(data -> data.getValue().name);
        Objeto.setResizable(false);
        R_KNN.setCellValueFactory(data -> data.getValue().rotulos_estimados.asObject());
        R_KNN.setResizable(false);
        R_Real.setCellValueFactory(data -> data.getValue().rotulos_reais.asObject());
        R_Real.setResizable(false);
        Acerto.setCellValueFactory(data -> data.getValue().acerto);
        Acerto.setResizable(false);

        List<MyData> dados = carregarDadosDinamicamente(tabela, rotulos_objetos);
        myTableView.setItems(FXCollections.observableArrayList(dados));
        myTableView.setFixedCellSize(25); // Ajuste o valor conforme necessário
        double alturaTabela = myTableView.getFixedCellSize() * myTableView.getItems().size() + 35; // 28 para compensar o cabeçalho
        myTableView.setMinHeight(alturaTabela);
        myTableView.setMaxHeight(alturaTabela);
        double larguraTabela = Objeto.getPrefWidth() + R_KNN.getPrefWidth() + R_Real.getPrefWidth() + Acerto.getPrefWidth() + 5;
        myTableView.setMinWidth(larguraTabela);
        myTableView.setMaxWidth(larguraTabela);
    }
    private List<MyData> carregarDadosDinamicamente(double[][]tabela, double[][]rotulos_objetos) {
        List<MyData> dados = new ArrayList<>();
        String acertiva;
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i][0]==rotulos_objetos[i][0]) {
                acertiva = "Acerto";
            }else{
                acertiva = "Erro";
            }
            dados.add(new MyData("Objeto - " + i, tabela[i][0], rotulos_objetos[i][0], acertiva));
        }
        // Adicione quantos dados forem necessários
        return dados;
    }
}