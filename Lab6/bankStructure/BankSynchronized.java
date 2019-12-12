package bankStructure;

import java.io.PrintStream;

public class BankSynchronized extends Bank {

    public BankSynchronized(int countOfAccounts, long startBalance) {
        for (int i = 0; i < countOfAccounts; i++) {
            dataAccount.put(String.valueOf(i), new AccountSynchronized(startBalance));
        }
    }

    @Override
    public void transfer(Transaction currentTransaction, PrintStream out) {
        Account fromAccount = dataAccount.get(currentTransaction.getFromId());
        Account toAccount = dataAccount.get(currentTransaction.getToId());

        if (fromAccount.getBalance() < currentTransaction.getAmount()) {
            out.println("Transaction failed.");
            return;
        }

        Account first, second;
        if (currentTransaction.getFromId().compareTo(currentTransaction.getToId()) < 0) {
            first = fromAccount;
            second = toAccount;
        } else {
            first = toAccount;
            second = fromAccount;
        }

        synchronized (first) {
            synchronized (second) {
                fromAccount.withdraw(currentTransaction.getAmount());
                toAccount.deposit(currentTransaction.getAmount());
            }
        }

        out.println("Transaction completed.");
    }
}
