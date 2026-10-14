package edu.psu.se411.lab09_inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab09_inventory.model.*;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		logger.info("Application is starting...");

		try {

			Inventory<Item> inventory = new Inventory<Item>();

			inventory.add(new Book("B001", "Concurrency in Java", "Salah Obeid"));

			inventory.add(new ElectronicDevice("M001", "USB drive 256Gb", "Toshiba"));

			displayInventory(inventory);

			System.out.println(inventory.findItems(new SearchByName("Concurrency in Java")));

			System.out.println(inventory.findItems(new SearchBookByAuthor("Salah Obeid")));

			System.out.println(inventory.findItems(new SearchByCategory("Toshiba")));

			System.out.println(inventory.findItems(new SearchById("B001")));

			System.out.println(inventory.findItems(item -> item.getId().equals("M001")));

		} catch (Exception e) {

			logger.error("An error occurred: ", e);

		}

		logger.info("Application is closing");
	}

	public static void displayInventory(Inventory<?> inventory) {

		for (Object item : inventory.getElements()) {
			System.out.println(item);
		}
	}
}