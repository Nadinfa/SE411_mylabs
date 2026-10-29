
package edu.psu.lab12;

public final class Transfer {

    private Transfer() {}

    public static boolean move(Account from, Account to, long amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount");
        }

        if (from == to) {
            return true;
        }

        Account first = from.id < to.id ? from : to;
        Account second = from.id < to.id ? to : from;

        if (first.id == second.id) {
            throw new IllegalArgumentException("IDs must be unique");
        }

        first.lock.lock();

        try {
            second.lock.lock();

            try {
                if (from.balance < amount) {
                    return false;
                }

                from.balance -= amount;
                to.balance += amount;

                return true;

            } finally {
                second.lock.unlock();
            }

        } finally {
            first.lock.unlock();
        }
    }
}
