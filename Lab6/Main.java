import bankStructure.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    private static final int BANK_ACCOUNT_COUNT = 10;
    private static final long BANK_START_BALANCE = 10000;
    private static final String BANK_DATA_FILE_NAME = "BankDataTransfers.txt";

    public static void main(String[] args) {
        try {
            generateData(BANK_DATA_FILE_NAME, BANK_ACCOUNT_COUNT);

            System.out.println("===Synchronized===");
            Bank bankSynchronized = new BankSynchronized(BANK_ACCOUNT_COUNT, BANK_START_BALANCE);
            bankSynchronized.readTransactionsFromFile(BANK_DATA_FILE_NAME, System.out);
            bankSynchronized.runTransactions(System.out);
            bankSynchronized.getMapAccount().forEach((key, value) -> System.out.println(key + ": " + value.getBalance()));
            System.out.println();

            System.out.println("===Concurrent===");
            Bank bankConcurrent = new BankConcurrent(BANK_ACCOUNT_COUNT, BANK_START_BALANCE);
            bankConcurrent.readTransactionsFromFile(BANK_DATA_FILE_NAME, System.out);
            bankConcurrent.runTransactions(System.out);
            bankConcurrent.getMapAccount().forEach((key, value) -> System.out.println(key + ": " + value.getBalance()));

        } catch (FileNotFoundException except) {
            System.err.println("File not found: " + except.getMessage());
        }
    }

    private static void generateData(String fileName, int accountCount) throws FileNotFoundException {
        Random random = new Random();
        PrintWriter out = new PrintWriter(new FileOutputStream(fileName));

        for (int i = 0; i < 100; i++) {
            out.println("" + random.nextInt(accountCount) + ":" + random.nextInt(accountCount) + ":" + random.nextInt(10000));
        }

        out.close();
    }
}
