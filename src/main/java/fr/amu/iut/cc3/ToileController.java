package fr.amu.iut.cc3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToileController implements Initializable {

    private static int rayonCercleExterieur = 200;
    private static int angleEnDegre = 60;
    private static int angleDepart = 90;
    private static int noteMaximale = 20;

    private List<Circle> dataPoints = new ArrayList<>();

    @FXML
    private TextField comp1;

    @FXML
    private TextField comp2;

    @FXML
    private TextField comp3;

    @FXML
    private TextField comp4;

    @FXML
    private TextField comp5;

    @FXML
    private TextField comp6;

    @FXML
    private Pane toile; // Modifier l'attribut fx:id en "toile"

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comp1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp1, 1);
            }
        });

        comp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp2, 2);
            }
        });

        comp3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp3, 3);
            }
        });

        comp4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp4, 4);
            }
        });

        comp5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp5, 5);
            }
        });

        comp6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDataPoint(comp6, 6);
            }
        });
    }

    private void addDataPoint(TextField textField, int axe) {
        try {
            double value = Double.parseDouble(textField.getText());
            Circle dataPoint = createDataPoint(value, axe);
            toile.getChildren().add(dataPoint); // Utiliser le champ toile
            dataPoints.add(dataPoint);
        } catch (NumberFormatException e) {
            // En cas d'erreur de format dans le champ de saisie, ne rien faire
        }
    }

    private Circle createDataPoint(double value, int axe) {
        int x = getXRadarChart(value, axe);
        int y = getYRadarChart(value, axe);
        Circle dataPoint = new Circle(x, y, 5);
        return dataPoint;
    }

    int getXRadarChart(double value, int axe) {
        return (int) (rayonCercleExterieur + Math.cos(Math.toRadians(angleDepart - (axe - 1) * angleEnDegre)) * rayonCercleExterieur
                * (value / noteMaximale));
    }

    int getYRadarChart(double value, int axe) {
        return (int) (rayonCercleExterieur - Math.sin(Math.toRadians(angleDepart - (axe - 1) * angleEnDegre)) * rayonCercleExterieur
                * (value / noteMaximale));
    }

    @FXML
    private void vider(ActionEvent event) {
        toile.getChildren().removeAll(dataPoints);
        dataPoints.clear();
    }
}