package controllers;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import animals.Animal;
import animals.Herbivore;
import animals.Omnivore;
import animals.Predator;
import animals.Food;

public class ControllerLab2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textAreaAnimal;

    @FXML
    private Button btnPrintAll;

    @FXML
    private Button btnPrintFirstFiveNames;

    @FXML
    private Button btnPrintLastThreeId;

    @FXML
    private Button btnSort;

    @FXML
    private Button btnPrintToFile;

    @FXML
    private TextField textFieldFileNameToPrint;

    @FXML
    private ComboBox<String> comboBoxType;

    @FXML
    private TextField textFieldAnimalId;

    @FXML
    private TextField textFieldAnimalName;

    @FXML
    private CheckBox checkBox;

    @FXML
    private HBox hBoxForCheckBox;

    @FXML
    private TextField textFieldFoodType;

    @FXML
    private TextField textFieldFoodAmount;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddFromFile;

    @FXML
    private TextField textFieldFileNameToAdd;

    private ArrayList<Animal> animalList = new ArrayList<Animal>();

    @FXML
    void initialize() {
        comboBoxType.setItems(FXCollections.observableArrayList("Herbivore", "Omnivore", "Predator"));
        comboBoxType.setValue("Herbivore");
    }

    public void onButtonClickEvent(javafx.event.ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (btnAdd.equals(source)) {
            btnAddEvent();
        } else if (btnAddFromFile.equals(source)) {
            btnAddFromFileEvent();
        } else if (btnPrintToFile.equals(source)) {
            btnPrintToFileEvent();
        }
    }

    public void checkBoxClickEvent() {
        hBoxForCheckBox.setDisable(!hBoxForCheckBox.isDisable());
    }

    public void printAll() {
        textAreaAnimal.setText("");
        for (int i = 0; i < animalList.size(); i++) {
            textAreaAnimal.appendText(animalList.get(i).toString() + "\n");
        }
    }

    public void printFirstFiveName() {
        if (animalList.size() < 5) {
            createAlert("Printing Error", "Not enough animals.\nAdd more animals!");
            return;
        }
        textAreaAnimal.setText("");
        for (int i = 0; i < 5; i++) {
            textAreaAnimal.appendText(animalList.get(i).getName() + "\n");
        }
    }

    public void printLastThreeId() {
        if (animalList.size() < 3) {
            createAlert("Printing Error", "Not enough animals.\nAdd more animals!");
            return;
        }
        textAreaAnimal.setText("");
        for (int i = animalList.size() - 3; i < animalList.size(); i++) {
            textAreaAnimal.appendText(animalList.get(i).getId() + "\n");
        }
    }

    public void animalListSorting() {
        animalList.sort((o1, o2) -> (o1.getFood() != o2.getFood()) ?
                o2.getFood().getAmount() - o1.getFood().getAmount() :
                o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        printAll();
    }

    static void readFromFile(List<Animal> list, String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
        while (true) {
            try {
                list.add((Animal) input.readObject());
            } catch (EOFException exept) {
                input.close();
                break;
            }
        }
    }

    static void writeToFile(List<Animal> list, String fileName) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
        for (Animal i : list) {
            output.writeObject(i);
        }
        output.close();
    }

    private void createAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void resetTextFieldsAfterAdding() {
        textFieldAnimalId.setText("");
        textFieldAnimalName.setText("");
        textFieldFoodType.setText("");
        textFieldFoodAmount.setText("");
    }

    private void btnAddEvent() {
        String animalIdString = textFieldAnimalId.getText();
        String animalName = textFieldAnimalName.getText();

        if (animalIdString.isEmpty() | animalName.isEmpty()) {
            createAlert("User Error", "Please, fill in all main fields.");
            return;
        }

        int animalId;
        try {
            animalId = Integer.parseInt(animalIdString);
        } catch (NumberFormatException except) {
            createAlert("User Error", "In the field of ID there should be a number!");
            textFieldAnimalId.setText("");
            textFieldAnimalId.requestFocus();
            return;
        }

        Food food = null;

        if (checkBox.isSelected()) {
            String foodType = textFieldFoodType.getText();
            String foodAmount = textFieldFoodAmount.getText();
            if (foodType.isEmpty() | foodAmount.isEmpty()) {
                createAlert("User Error", "Please, fill in all additional fields.");
                return;
            }
            try {
                food = new Food(foodType, Integer.parseInt(foodAmount));
            } catch (NumberFormatException except) {
                createAlert("Invalid Input", "In the field of amount there should be a number!");
                textFieldFoodAmount.setText("");
                textFieldFoodAmount.requestFocus();
                return;
            }
        }

        switch (comboBoxType.getValue()) {
            case "Herbivore":
                animalList.add(new Herbivore(animalId, animalName, food));
                break;
            case "Omnivore":
                animalList.add(new Omnivore(animalId, animalName, food));
                break;
            case "Predator":
                animalList.add(new Predator(animalId, animalName, food));
                break;
        }
        resetTextFieldsAfterAdding();
    }

    private void btnAddFromFileEvent() {
        try {
            String fileName = textFieldFileNameToAdd.getText();
            if (fileName.isEmpty()) {
                return;
            }
            readFromFile(animalList, fileName);
        } catch (IOException | ClassNotFoundException except) {
            createAlert("File Reading Error", "File is not found: " + except.getMessage());
        }
        textFieldFileNameToAdd.setText("");
    }

    private void btnPrintToFileEvent() {
        try {
            String fileName = textFieldFileNameToPrint.getText();
            if (fileName.isEmpty())
            {
                fileName = "animal";
            }
            writeToFile(animalList, fileName);
        } catch (IOException except) {
            createAlert("File Writing Error", "File is not found: " + except.getMessage());
        }
        textFieldFileNameToPrint.setText("");
    }
}
