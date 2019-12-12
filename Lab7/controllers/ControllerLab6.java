package controllers;

import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import bankStructure.Bank;
import bankStructure.BankConcurrent;
import bankStructure.BankSynchronized;


public class ControllerLab6 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textAreaSyncOutput;

    @FXML
    private TextArea textAreaConcOutput;

    @FXML
    private Button btnStartSynchronizedBank;

    @FXML
    private Button btnStartConcurrentBank;

    @FXML
    private Button btnGenerateTransactions;

    @FXML
    private TextField textFieldNumberOfAccounts;

    @FXML
    private TextField textFieldStartBalance;

    @FXML
    private Button btnCreateBank;

    private static String fileName = "BankDataTransfers.txt";
    private Bank bankSynchronized;
    private Bank bankConcurrent;
    private PrintStream syncOutput;
    private PrintStream concOutput;
    private int accountCount;

    @FXML
    void initialize() {
        OutputStream outputStreamSync = new OutputStream() {
            @Override
            public void write(int i) {
                textAreaSyncOutput.appendText(String.valueOf((char) i));
            }
        };
        syncOutput = new PrintStream(outputStreamSync, true);

        OutputStream outputStreamConc = new OutputStream() {
            @Override
            public void write(int i) {
                textAreaConcOutput.appendText(String.valueOf((char) i));
            }
        };
        concOutput = new PrintStream(outputStreamConc, true);

        accountCount = Integer.parseInt(textFieldNumberOfAccounts.getText());
        bankSynchronized = new BankSynchronized(accountCount, Integer.parseInt(textFieldStartBalance.getText()));
        bankConcurrent = new BankConcurrent(accountCount, Integer.parseInt(textFieldStartBalance.getText()));
    }

    public void onButtonClickEvent(javafx.event.ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (btnStartSynchronizedBank.equals(source)) {
            textAreaSyncOutput.setText("");
            startBankTransactions(bankSynchronized, syncOutput);
            bankSynchronized.getMapAccount().forEach((key, value) -> syncOutput.println(key + ": " + value.getBalance()));
        } else if (btnStartConcurrentBank.equals(source)) {
            textAreaConcOutput.setText("");
            startBankTransactions(bankConcurrent, concOutput);
            bankConcurrent.getMapAccount().forEach((key, value) -> concOutput.println(key + ": " + value.getBalance()));
        }
        textAreaSyncOutput.setScrollTop(Double.MAX_VALUE);
        textAreaConcOutput.setScrollTop(Double.MAX_VALUE);
    }

    public void btnGenerateTransactionsEvent() {
        textAreaSyncOutput.setText("");
        textAreaConcOutput.setText("");

        try {
            generateData(fileName, accountCount);
            bankSynchronized.readTransactionsFromFile(fileName, syncOutput);
            bankConcurrent.readTransactionsFromFile(fileName, concOutput);
        } catch (FileNotFoundException except) {
            createAlert("File Error", "File is not found: " + except.getMessage());
        }
    }

    public void btnCreateBankEvent() {
        int startBalance;

        try {
            accountCount = Integer.parseInt(textFieldNumberOfAccounts.getText());
        } catch (NumberFormatException except) {
            createAlert("Invalid input", "Field \"Number of accounts\" must be a number.");
            textFieldNumberOfAccounts.setText("");
            textFieldNumberOfAccounts.requestFocus();
            return;
        }
        try {
            startBalance = Integer.parseInt(textFieldStartBalance.getText());
        } catch (NumberFormatException except) {
            createAlert("Invalid input", "Field \"Start Balance\" must be a number.");
            textFieldStartBalance.setText("");
            textFieldStartBalance.requestFocus();
            return;
        }
        bankSynchronized = new BankSynchronized(accountCount, startBalance);
        bankConcurrent = new BankConcurrent(accountCount, startBalance);

        textAreaSyncOutput.setText("");
        textAreaConcOutput.setText("");
    }

    private void createAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void startBankTransactions(Bank bank, PrintStream out) {
        if (bank.isTransactionListEmpty()) {
            return;
        }
        bank.runTransactions(out);
    }

    private static void generateData(String fileName, int accountCount) throws FileNotFoundException {
        Random random = new Random();
        PrintWriter out = new PrintWriter(new FileOutputStream(fileName));

        for (int i = 0; i < 10; i++) {
            out.println("" + random.nextInt(accountCount) + ":" + random.nextInt(accountCount) + ":" + (random.nextInt(10000) - 3000));
        }

        out.close();
    }
}
