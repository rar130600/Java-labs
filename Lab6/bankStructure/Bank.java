package bankStructure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class Bank {
    Map<String, Account> dataAccount = new HashMap<>();
    private List<Transaction> dataTransactions = new ArrayList<>();

    public abstract void transfer(Transaction currentTransaction, PrintStream out);

    public void readTransactionsFromFile(String fileName, PrintStream out) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(fileName));
        out.println("Reading transaction file...");
        while (in.hasNextLine()) {
            String[] line = in.nextLine().split(":");
            try {
                dataTransactions.add(new Transaction(line[0], line[1], Integer.parseInt(line[2])));
            } catch (IllegalArgumentException except) {
                out.println("Transaction miss: " + except.getMessage());
            }
        }
        out.println("File reading is complete. Number of transactions read: " + dataTransactions.size());
        out.println();
    }

    public void runTransactions(PrintStream out) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Transaction transaction : dataTransactions) {
            executorService.submit(() -> transfer(transaction, out));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException except) {
            out.println(except.getMessage());
        }
        dataTransactions.clear();
    }

    public Map<String, Account> getMapAccount() {
        return dataAccount;
    }

    public boolean isTransactionListEmpty() {
        return dataTransactions.isEmpty();
    }
}
