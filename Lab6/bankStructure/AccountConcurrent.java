package bankStructure;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountConcurrent extends Account {
    private Lock lock = new ReentrantLock();

    public AccountConcurrent(long balance) {
        super(balance);
    }

    @Override
    public void deposit(long amount) {
        lock.lock();
        try {
            this.balance += amount;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(long amount) {
        lock.lock();
        try {
            this.balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getBalance() {
        lock.lock();
        try {
            return this.balance;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }
}
