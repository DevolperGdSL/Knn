package models;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelaMatrizConfusao {
    public final SimpleStringProperty name;
    public final SimpleDoubleProperty rotulos_estimados;
    public final SimpleDoubleProperty rotulos_reais;
    public final SimpleStringProperty acerto;

    public TabelaMatrizConfusao(String name, double rotulos_e, double rotulos_r, String acerto) {
        this.name = new SimpleStringProperty(name);
        this.rotulos_estimados = new SimpleDoubleProperty(rotulos_e);
        this.rotulos_reais = new SimpleDoubleProperty(rotulos_r);
        this.acerto = new SimpleStringProperty(acerto);
    }
}

