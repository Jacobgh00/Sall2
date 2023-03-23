package GUI;

import Application.Model.Lager;
import Application.Model.Fad;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LagerApp");

        //Lager input fields
        TextField lagerIdField = new TextField();
        TextField lagerNavnField = new TextField();
        TextField lagerLokationField = new TextField();
        TextField lagerKapacitetField = new TextField();

        //Fad input fields
        TextField fadIdField = new TextField();
        TextField fadTypeField = new TextField();
        TextField fadVolumenField = new TextField();
        TextField fadNuværendeVolumenField = new TextField();
        TextField fadAntalGangeBrugtField = new TextField();


        //Lager og fad listview
        ListView<Lager> lagerListView = new ListView<>();
        ListView<Fad> fadListView = new ListView<>();

        //Add custom cell factories
        lagerListView.setCellFactory(lv -> new ListCell<Lager>() {
            @Override
            protected void updateItem(Lager lager, boolean empty) {
                super.updateItem(lager, empty);
                if (empty || lager == null) {
                    setText(null);
                } else {
                    setText("ID: " + lager.getId() + ", Navn: " + lager.getNavn() + ", Lokation: " + lager.getLokation() + ", Kapacitet: " + lager.getKapacitet());
                }
            }
        });

        fadListView.setCellFactory(lv -> new ListCell<Fad>() {
            @Override
            protected void updateItem(Fad fad, boolean empty) {
                super.updateItem(fad, empty);
                if (empty || fad == null) {
                    setText(null);
                } else {
                    setText("ID: " + fad.getId() + ", Type: " + fad.getFadType() + ", Volumen: " + fad.getVolumen() + ", Nuværende Volumen: " + fad.getNuværendeVolumen() + ", Antal Gange Brugt: " + fad.getAntalGangeBrugt());
                }
            }
        });


        //Lager buttons
        Button tilføjLagerButton = new Button("Tilføj Lager");
        Button sletLagerButton = new Button("Slet Lager");
        Button redigerLagerButton = new Button("Rediger Lager");
        Button clearLagerButton = new Button("Clear Lager");

        //Fad buttons
        Button tilføjFadButton = new Button("Tilføj Fad");
        Button sletFadButton = new Button("Slet Fad");
        Button redigerFadButton = new Button("Rediger Fad");
        Button clearFadButton = new Button("Clear Fad");


        //Lager button actions
        tilføjLagerButton.setOnAction(e -> {
            Lager lager = new Lager(Integer.parseInt(lagerIdField.getText()),
                    lagerNavnField.getText(),
                    lagerLokationField.getText(),
                    Integer.parseInt(lagerKapacitetField.getText()));
            lagerListView.getItems().add(lager);

            //Clear Lager input fields, gør at efter man har tilføjet et lager bliver felterne ryddet.
            lagerIdField.clear();
            lagerNavnField.clear();
            lagerLokationField.clear();
            lagerKapacitetField.clear();
        });

        sletLagerButton.setOnAction(e -> {
            Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
            lagerListView.getItems().remove(selectedLager);
        });

        redigerLagerButton.setOnAction(e -> {
            Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
            if(selectedLager != null){
                RedigerLagerScene redigerLagerScene = new RedigerLagerScene(selectedLager);

                Stage redigerLagerStage = new Stage();
                redigerLagerStage.setTitle("Rediger Lager");
                redigerLagerStage.setScene(redigerLagerScene);
                redigerLagerStage.showAndWait();
                lagerListView.refresh();
            }
        });

        clearLagerButton.setOnAction(e -> {
            lagerIdField.clear();
            lagerNavnField.clear();
            lagerLokationField.clear();
            lagerKapacitetField.clear();
        });

        //Fad button actions
        tilføjFadButton.setOnAction(e -> {
            Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
            if (selectedLager != null) {
                Fad fad = new Fad(Integer.parseInt(fadIdField.getText()),
                        fadTypeField.getText(),
                        Double.parseDouble(fadVolumenField.getText()),
                        Double.parseDouble(fadNuværendeVolumenField.getText()),
                        Integer.parseInt(fadAntalGangeBrugtField.getText()),
                        selectedLager);
                fadListView.getItems().add(fad);

                // Clear Fad input fields
                fadIdField.clear();
                fadTypeField.clear();
                fadVolumenField.clear();
                fadNuværendeVolumenField.clear();
                fadAntalGangeBrugtField.clear();
            }
        });

        sletFadButton.setOnAction(e -> {
            Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
            fadListView.getItems().remove(selectedFad);
            if (selectedFad != null) {
                selectedFad.setLager(null);
            }
        });

        redigerFadButton.setOnAction(event -> {
            Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
            Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
            if (selectedFad != null && selectedLager != null) {

                RedigerFadScene redigerFadScene = new RedigerFadScene(selectedFad);
                Stage redigerFadStage = new Stage();
                redigerFadStage.setTitle("Rediger Fad");
                redigerFadStage.setScene(redigerFadScene);
                redigerFadStage.showAndWait();

                fadListView.refresh();
            }
        });

        clearFadButton.setOnAction(e -> {
            fadIdField.clear();
            fadTypeField.clear();
            fadVolumenField.clear();
            fadNuværendeVolumenField.clear();
            fadAntalGangeBrugtField.clear();
        });


        //layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new Label("Lager ID:"), 0, 0);
        grid.add(lagerIdField, 1, 0);
        grid.add(new Label("Lager Navn:"), 0, 1);
        grid.add(lagerNavnField, 1, 1);
        grid.add(new Label("Lager Lokation:"), 0, 2);
        grid.add(lagerLokationField, 1, 2);
        grid.add(new Label("Lager Kapacitet:"), 0, 3);
        grid.add(lagerKapacitetField, 1, 3);
        grid.add(new Label("Fad ID:"), 2, 0);
        grid.add(fadIdField, 3, 0);
        grid.add(new Label("Fad Type:"), 2, 1);
        grid.add(fadTypeField, 3, 1);
        grid.add(new Label("Fad Volumen:"), 2, 2);
        grid.add(fadVolumenField, 3, 2);
        grid.add(new Label("Nuværende Volumen:"), 2, 3);
        grid.add(fadNuværendeVolumenField, 3, 3);
        grid.add(new Label("Antal Gange Brugt:"), 2, 4);
        grid.add(fadAntalGangeBrugtField, 3, 4);


        HBox lagerButtons = new HBox(10);
        lagerButtons.getChildren().addAll(tilføjLagerButton, redigerLagerButton, sletLagerButton, clearLagerButton);

        HBox fadButtons = new HBox(10);
        fadButtons.getChildren().addAll(tilføjFadButton, redigerFadButton, sletFadButton, clearFadButton);

        VBox listViews = new VBox(10);
        listViews.getChildren().addAll(lagerListView, fadListView);
        grid.add(lagerButtons, 1, 4);
        grid.add(fadButtons, 3, 5);
        grid.add(listViews, 0, 5, 2, 1);


        //Lager listview actionListener
        lagerListView.setOnMouseClicked(e -> {
            Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
            if(selectedLager != null){
                lagerIdField.setText(String.valueOf(selectedLager.getId()));
                lagerNavnField.setText(selectedLager.getNavn());
                lagerLokationField.setText(selectedLager.getLokation());
                lagerKapacitetField.setText(String.valueOf(selectedLager.getKapacitet()));
            }
        });

        // Fad ListView actionlistener
        fadListView.setOnMouseClicked(e -> {
                    Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
                    if (selectedFad != null) {
                        fadIdField.setText(String.valueOf(selectedFad.getId()));
                        fadTypeField.setText(selectedFad.getFadType());
                        fadVolumenField.setText(String.valueOf(selectedFad.getVolumen()));
                        fadNuværendeVolumenField.setText(String.valueOf(selectedFad.getNuværendeVolumen()));
                        fadAntalGangeBrugtField.setText(String.valueOf(Integer.valueOf(selectedFad.getAntalGangeBrugt())));
                    }
                });

                Scene scene = new Scene(grid, 1000, 600);

                //load css file
                scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();


            }
        }



