package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;

	public CryptoAccount(String id, Double balance) {
		this.id = id;
		this.balance = balance;
	}

	public void addToBalance(Double amount) {
		if (amount == null || amount < 0) throw new IllegalArgumentException("Invalid amount");

		double updated = balance + amount;
		Thread.yield();
		balance = updated;
	}

	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if (amount == null || amount < 0) throw new IllegalArgumentException("Invalid amount");

        if (balance < amount) throw new InsufficientBalanceException("Insufficient balance in " + id);

        double updated = balance - amount;
        Thread.yield();
        balance = updated;
	}

	public String getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

}
