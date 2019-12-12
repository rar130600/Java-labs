package bankStructure;

public abstract class Account {
    protected long balance;

    public Account(long balance) {
        this.balance = balance;
    }

    public abstract long getBalance();

    public abstract void deposit(long amount);

    public abstract void withdraw(long amount);
}
