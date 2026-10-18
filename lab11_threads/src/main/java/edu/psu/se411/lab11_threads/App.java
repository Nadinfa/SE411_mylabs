
package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static Integer nbAccounts = 10;
    public static Double initialBalance = 1000.0;
    public static Integer nbTransactions = 10000;

    public static void main(String[] args) {
        logger.info("Application is starting...");

        // 1- Create accounts
        CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
        for (Integer i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
        }

        Thread[] threads = new Thread[nbTransactions];

        for (int i = 0; i < nbTransactions; i++) {

            CryptoAccount randomA =
                accounts[(int)(Math.random() * nbAccounts)];

            CryptoAccount randomB =
                accounts[(int)(Math.random() * nbAccounts)];

            Double randomAmount =
                (double)(Math.random() * 10 + 1);

            Transaction t = new Transaction(
                "T_" + i, randomA, randomB, randomAmount);

            Thread thread = new Thread(t);
            threads[i] = thread;
            thread.start();
        }

        for (int i = 0; i < nbTransactions; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted", e);
                return;
            }
        }

        double totalBalance = getTotalBalance(accounts);

        logger.info("Expected total balance: "
                + (nbAccounts * initialBalance));

        logger.info("Actual total balance: " + totalBalance);

        logger.info("Application is closing...");
    }

    public static double getTotalBalance(CryptoAccount[] accounts) {
        double total = 0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }
}
