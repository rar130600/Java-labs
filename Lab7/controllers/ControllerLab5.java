package controllers;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerLab5 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textAreaOutput;

    @FXML
    private Button btnReadProperties;

    @FXML
    private TextField textFieldFileName;

    private PrintStream out;

    @FXML
    void initialize() {
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int i) {
                textAreaOutput.appendText(String.valueOf((char) i));
            }
        };
        out = new PrintStream(outputStream, true);
    }

    public void onButtonClickEvent(ActionEvent event) {
        String fileName = textFieldFileName.getText();
        textFieldFileName.requestFocus();
        if (fileName.isEmpty()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            Properties properties = new Properties();
            properties.load(br);
            textAreaOutput.setText("");
            properties.list(out);
        } catch (FileNotFoundException except) {
            createAlert("File Error", "File is not found: " + except.getMessage());
        } catch (IOException except) {
            createAlert("Unknown Error", except.getMessage());
        }
        textFieldFileName.setText("");
    }

    private void createAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
