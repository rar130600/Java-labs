package bankStructure;

import java.io.PrintStream;

public class BankConcurrent extends Bank {
    public BankConcurrent(int countOfAccounts, long startBalance) {
        for (int i = 0; i < countOfAccounts; i++) {
            dataAccount.put(String.valueOf(i), new AccountConcurrent(startBalance));
        }
    }

    @Override
    public void transfer(Transaction currentTransaction, PrintStream out) {
        AccountConcurrent fromAccount = (AccountConcurrent) dataAccount.get(currentTransaction.getFromId());
        AccountConcurrent toAccount = (AccountConcurrent) dataAccount.get(currentTransaction.getToId());

        if (fromAccount.getBalance() < currentTransaction.getAmount()) {
           out.println("Transaction failed.");
            return;
        }

        while (true) {
            boolean lock1 = fromAccount.getLock().tryLock();
            boolean lock2 = toAccount.getLock().tryLock();

            if (lock1 && lock2) {
                break;
            }

            if (lock1) {
                fromAccount.getLock().unlock();
            }

            if (lock2) {
                toAccount.getLock().unlock();
            }
        }

        try {
            fromAccount.withdraw(currentTransaction.getAmount());
            toAccount.deposit(currentTransaction.getAmount());
        } finally {
            fromAccount.getLock().unlock();
            toAccount.getLock().unlock();
        }

        out.println("Transaction completed.");
    }
}
