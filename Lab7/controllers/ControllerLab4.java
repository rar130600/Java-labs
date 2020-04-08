package controllers;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import explorers.FileExplorer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerLab4 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textAreaOutput;

    @FXML
    private TextField textFieldInput;

    @FXML
    private Button btnEnter;

    static PrintStream outDefault;
    static PrintStream errDefault;
    private String command;
    private FileExplorer fe = new FileExplorer();


    @FXML
    void onButtonClickEvent(ActionEvent event) {
        textFieldInput.requestFocus();

        command = textFieldInput.getText().trim();
        if (command.isEmpty()) {
            return;
        }

        textFieldInput.setText("");

        if (command.equals("exit")) {
            Stage stage = (Stage) btnEnter.getScene().getWindow();
            stage.close();
            return;
        }
        if (command.equals("help")) {
            fe.helpPrint();
            System.out.print(fe.getPath() + "$:\n");
            return;
        }
        fe.runCommand(command);
        System.out.print(fe.getPath() + "$:\n");
    }

    @FXML
    void initialize() {
        outDefault = System.out;
        errDefault = System.err;

        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int i) {
                textAreaOutput.appendText(String.valueOf((char) i));
            }
        };
        PrintStream printStream = new PrintStream(outputStream, true);
        System.setOut(printStream);
        System.setErr(printStream);

        System.out.println("Type \"help\" for more information about commands");
        System.out.print(fe.getPath() + "$:\n");
    }

    public static void closeApplicationEvent(WindowEvent windowEvent) {
        System.setOut(outDefault);
        System.setErr(errDefault);
    }
}
