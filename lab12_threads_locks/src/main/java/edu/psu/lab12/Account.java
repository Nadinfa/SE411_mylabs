
package edu.psu.lab12;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    final int id;
    long balance;
    final ReentrantLock lock = new ReentrantLock();

    public Account(int id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
