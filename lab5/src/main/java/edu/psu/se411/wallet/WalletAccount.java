package edu.psu.se411.wallet;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class WalletAccount {

	private double balance;

	public WalletAccount(double balance) {
		this.balance = balance;
	}

	public void withdraw(double amount) throws InsufficientFundsException {

		if (amount > balance) {
			throw new InsufficientFundsException("insufficient funds");
		}

		balance = balance - amount;

		System.out.println("withdrawal successful");
		System.out.println("remaining balance: " + balance);
	}
}