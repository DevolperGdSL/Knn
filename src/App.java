
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilidade.Leitor_de_txt;
import funções.ferramentas;


public class App extends Application{


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grafico.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Plano Cartesiano com ScatterChart");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

