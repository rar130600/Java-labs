package controllers;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import tasks.Task1;
import tasks.Task2;
import tasks.Task3;

public class ControllerLab1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane matrix;

    @FXML
    private TextArea textAreaMatrix;

    @FXML
    private Button btnMatrix;

    @FXML
    private TextField textFieldString;

    @FXML
    private TextArea textAreaString;

    @FXML
    private Button btnString;

    @FXML
    private Button btnBook;

    public void generateMatrix() {
        Task1 task1 = new Task1();
        textAreaMatrix.setText(matrixToString(task1.createMatrix(4, 8)));
    }

    private String matrixToString(int [][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int[] iline : matrix) {
            for (int j : iline) {
                result.append(j).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void createFragments() {
        Task2 task2 = new Task2();
        textAreaString.setText(arrayFragmentsToString(task2.stringFragmentation(textFieldString.getText())));
    }

    private String arrayFragmentsToString(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String i: array) {
            result.append(i).append("\n");
        }
        return result.toString();
    }

    public void startTaskInConsole() {
        Task3 task3 = new Task3();
        try {
            task3.startTask();
        } catch (IllegalArgumentException except) {
            System.err.println(except.getMessage());
        } catch (ArrayIndexOutOfBoundsException except) {
            System.err.println(except.getMessage());
        } catch (NullPointerException except) {
            System.err.println(except.getMessage());
        } catch (InputMismatchException except) {
            System.err.println(except.getMessage());
        }
    }
}
