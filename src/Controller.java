
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import chamadas.*;

public class Controller {

    @FXML
    private BubbleChart<Number, Number> bubbleChart; // Referência ao gráfico de bolas
    @FXML
    private Button addButton; // Botão para adicionar dados
    @FXML
    private TextField kInput; // Campo de texto para entrada do valor X

    // Lista para armazenar os dados das bolas
    private ObservableList<XYChart.Data<Number, Number>> bubbleData;

    @FXML
    public void initialize() {
        // Inicializa a lista de dados do gráfico
        bubbleData = FXCollections.observableArrayList();
        // Configura a série de dados no gráfico
        XYChart.Series<Number, Number> series = new XYChart.Series<>(bubbleData);
        bubbleChart.getData().add(series);
    }
    
    @FXML
    private void handleAddButtonAction(){
        int k = Integer.parseInt(kInput.getText());
        coracao_knn chama = new coracao_knn();
        double[][] tabela = chama.chamada(k);
    }
}
