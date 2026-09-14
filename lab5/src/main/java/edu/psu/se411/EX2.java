package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.wallet.WalletAccount;

public class EX2 {

	public static void main(String[] args) {

		WalletAccount wallet = new WalletAccount(500);

		try {
			wallet.withdraw(600);
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
		}
	}
}