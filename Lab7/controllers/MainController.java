package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLab1;

    @FXML
    private Button btnLab2;

    @FXML
    private Button btnLab3;

    @FXML
    private Button btnLab4;

    @FXML
    private Button btnLab5;

    @FXML
    private Button btnLab6;

    private Stage stage;

    @FXML
    public void onButtonClickEvent(javafx.event.ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (btnLab1.equals(source)) {
            openNewScene("/main/lab1.fxml");
        } else if (btnLab2.equals(source)) {
            openNewScene("/main/lab2.fxml");
        } else if (btnLab3.equals(source)) {
            openNewScene("/main/lab3.fxml");
        } else if (btnLab4.equals(source)) {
            openNewScene("/main/lab4.fxml");
            stage.setOnHidden(ControllerLab4::closeApplicationEvent);
        } else if (btnLab5.equals(source)) {
            openNewScene("/main/lab5.fxml");
        } else if (btnLab6.equals(source)) {
            openNewScene("/main/lab6.fxml");
        }
    }

    private void openNewScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            loader.load();
            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException except) {
            System.err.println(except.getMessage());
        }
    }
}