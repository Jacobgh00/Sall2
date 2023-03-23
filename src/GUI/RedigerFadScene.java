package GUI;

import Application.Model.Fad;
import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RedigerFadScene extends Scene {

    private TextField fadIdField;
    private TextField fadTypeField;
    private TextField volumenField;
    private TextField nuværendeVolumenField;
    private TextField antalGangeBrugtField;
    private TextField lagerIdField;
    private Button gemButton;
    private Button annullerButton;

    private Fad fad;

    public RedigerFadScene(Fad fad) {
        super(new VBox(), 400, 450);

        this.fad = fad;

        fadIdField = new TextField(String.valueOf(fad.getId()));
        fadTypeField = new TextField(fad.getFadType());
        volumenField = new TextField(String.valueOf(fad.getVolumen()));
        nuværendeVolumenField = new TextField(String.valueOf(fad.getNuværendeVolumen()));
        antalGangeBrugtField = new TextField(String.valueOf(fad.getAntalGangeBrugt()));
        lagerIdField = new TextField(String.valueOf(fad.getLager().getId()));

        gemButton = new Button("Gem");
        gemButton.setOnAction(e -> {
            fad.setId(Integer.parseInt(fadIdField.getText()));
            fad.setFadType(fadTypeField.getText());
            fad.setVolumen(Double.parseDouble(volumenField.getText()));
            fad.setNuværendeVolumen(Double.parseDouble(nuværendeVolumenField.getText()));
            fad.setAntalGangeBrugt(Integer.parseInt(antalGangeBrugtField.getText()));

            // Luk scenen
            ((Stage) gemButton.getScene().getWindow()).close();
        });

        annullerButton = new Button("Annuller");
        annullerButton.setOnAction(e -> {
            // Luk scenen uden at gemme ændringer
            ((Stage) annullerButton.getScene().getWindow()).close();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("ID:"), fadIdField,
                new Label("Fadtype:"), fadTypeField,
                new Label("Volumen:"), volumenField,
                new Label("Nuværende Volumen:"), nuværendeVolumenField,
                new Label("Antal gange brugt:"), antalGangeBrugtField,
                new Label("Lager ID:"), lagerIdField,
                buttonBox
        );
        form.setPadding(new Insets(10));

        setRoot(form);
    }
}
