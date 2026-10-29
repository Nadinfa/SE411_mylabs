
package edu.psu.lab12;

import java.util.*;
import java.util.concurrent.*;

public final class Benchmark {

    public static final int TRANSACTIONS = 1_000_000;
    public static final int BATCH = 1000;

    private Benchmark() {}

    public static Account[] accounts(int count) {
        Account[] a = new Account[count];

        for (int i = 0; i < count; i++) {
            a[i] = new Account(i, 1_000_000L);
        }

        return a;
    }

    public static void batch(Account[] accounts, int start, int end) {

        for (int i = start; i < end; i++) {

            int from = (int)((i * 17L + 3) % accounts.length);
            int to = (int)((i * 31L + 7) % accounts.length);

            Transfer.move(accounts[from], accounts[to], 1);
        }
    }

    public static long total(Account[] a) {
        long sum = 0;

        for (Account account : a) {
            sum += account.getBalance();
        }

        return sum;
    }

    public static void run(boolean forkJoin) throws Exception {

        int workers = Math.max(
            2, Runtime.getRuntime().availableProcessors()
        );

        for (int count : new int[]{1000, 10}) {

            Account[] accounts = accounts(count);
            long expected = total(accounts);

            long start = System.nanoTime();

            if (forkJoin) {

                ForkJoinPool pool = new ForkJoinPool(workers);

                try {
                    pool.invoke(
                        new TransferAction(accounts, 0, TRANSACTIONS)
                    );
                } finally {
                    pool.shutdown();
                }

            } else {

                ExecutorService pool =
                    Executors.newFixedThreadPool(workers);

                try {
                    List<Future<?>> futures = new ArrayList<>();

                    for (int i = 0; i < TRANSACTIONS; i += BATCH) {

                        final int from = i;
                        final int to = Math.min(
                            i + BATCH, TRANSACTIONS
                        );

                        futures.add(
                            pool.submit(() -> batch(accounts, from, to))
                        );
                    }

                    for (Future<?> future : futures) {
                        future.get();
                    }

                } finally {
                    pool.shutdown();
                }
            }

            double ms =
                (System.nanoTime() - start) / 1_000_000.0;

            long actual = total(accounts);

            System.out.printf(
                "%s | accounts=%d | transactions=%d | time=%.2f ms | total preserved=%s%n",
                forkJoin ? "ForkJoinPool" : "ThreadPool",
                count,
                TRANSACTIONS,
                ms,
                expected == actual
            );

            if (actual != expected) {
                throw new AssertionError(
                    "Money was lost or created"
                );
            }
        }
    }

    private static class TransferAction extends RecursiveAction {

        private final Account[] accounts;
        private final int start;
        private final int end;

        TransferAction(Account[] accounts, int start, int end) {
            this.accounts = accounts;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {

            if (end - start <= BATCH) {
                batch(accounts, start, end);
                return;
            }

            int mid = (start + end) >>> 1;

            invokeAll(
                new TransferAction(accounts, start, mid),
                new TransferAction(accounts, mid, end)
            );
        }
    }
}
