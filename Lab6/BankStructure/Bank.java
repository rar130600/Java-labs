package BankStructure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Bank {
    Map<String, Account> dataAccount = new HashMap<>();
    private List<Transaction> dataTransactions = new ArrayList<>();

    public abstract void transfer(Transaction currentTransaction);

    public void readTransactionsFromFile(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(fileName));
        System.out.println("Reading transaction file...");
        while (in.hasNextLine()) {
            String[] line = in.nextLine().split(":");
            try {
                dataTransactions.add(new Transaction(line[0], line[1], Integer.parseInt(line[2])));
            } catch (IllegalArgumentException except) {
                System.err.println("Transaction miss: " + except.getMessage());
            }
        }
        System.out.println("File reading is complete. Number of transactions read: " + dataTransactions.size());
        System.out.println();
    }

    public void runTransactions() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Transaction transaction : dataTransactions) {
            executorService.submit(() -> transfer(transaction));
        }

        executorService.shutdown();
    }

    public Map<String, Account> getMapAccount() {
        return dataAccount;
    }
}
