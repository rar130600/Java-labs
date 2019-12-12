package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import undo.UndoStringBuilder;

public class ControllerLab3 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textAreaOutput;

    @FXML
    private TextField textFieldInput;

    @FXML
    private Button btnAppend;

    @FXML
    private Button btnReverse;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDeleteChar;

    @FXML
    private Button btnUndo;

    private UndoStringBuilder undoStringBuilder = new UndoStringBuilder();

    public void onButtonClickEvent(javafx.event.ActionEvent actionEvent) {
        try {
            Object source = actionEvent.getSource();

            if (btnAppend.equals(source)) {
                btnAppendEvent();
            } else if (btnUndo.equals(source)) {
                btnUndoEvent();
            } else if (btnDeleteChar.equals(source)) {
                btnDeleteCharEvent();
            } else if (btnClear.equals(source)) {
                btnClearEvent();
            } else if (btnReverse.equals(source)) {
                btnReverseEvent();
            }

            textAreaOutput.setText(undoStringBuilder.toString());
        } catch (IllegalArgumentException except) {
            System.err.println(except.getMessage());
            return;
        }
    }

    private void btnAppendEvent() throws IllegalArgumentException {
        String input = textFieldInput.getText();
        textFieldInput.requestFocus();
        if (input.isEmpty()) {
            textFieldInput.requestFocus();
            throw new IllegalArgumentException("Text field is empty.");
        }
        undoStringBuilder.append(input);
        textFieldInput.setText("");
    }

    private void btnUndoEvent() {
        undoStringBuilder.undo();
    }

    private void btnDeleteCharEvent() {
        if (undoStringBuilder.lenght() == 0) {
            throw new IllegalArgumentException("Text area is empty.");
        }
        undoStringBuilder.deleteCharAt(undoStringBuilder.lenght() -1);
    }

    private void btnClearEvent() {
        if (undoStringBuilder.lenght() == 0) {
            throw new IllegalArgumentException("Text area is already empty.");
        }
        undoStringBuilder = new UndoStringBuilder();
    }

    private void btnReverseEvent() {
        undoStringBuilder.reverse();
    }
}
