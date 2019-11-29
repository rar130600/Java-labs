package BankStructure;

public class BankSynchronized extends Bank {

    public BankSynchronized(int countOfAccounts, long startBalance) {
        for (int i = 0; i < countOfAccounts; i++) {
            dataAccount.put(String.valueOf(i), new AccountSynchronized(startBalance));
        }
    }

    @Override
    public void transfer(Transaction currentTransaction) {
        Account fromAccount = dataAccount.get(currentTransaction.getFromId());
        Account toAccount = dataAccount.get(currentTransaction.getToId());

        if (fromAccount.getBalance() < currentTransaction.getAmount()) {
            System.err.println("Transaction failed.");
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

        System.out.println("Transaction completed.");
    }
}
