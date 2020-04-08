package bankStructure;

public class AccountSynchronized extends Account {

    public AccountSynchronized(long balance) {
        super(balance);
    }

    @Override
    public synchronized void deposit(long amount) {
        this.balance += amount;
    }

    @Override
    public synchronized void withdraw(long amount) {
        this.balance -= amount;
    }

    @Override
    public synchronized long getBalance() {
        return this.balance;
    }
}
