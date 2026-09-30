package edu.psu.se411.lab07;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		logger.info("Application is starting");

		try {

			FlightBooking flight = new FlightBooking("B001", "Nadin", new Date(), "New York", 200.0, 20.0, 30.0);

			CarRentalBooking car = new CarRentalBooking("B002", "Nora", new Date(), "Los Angeles", 50.0, 10);

			TrainBooking train = new TrainBooking("B003", "Sara", new Date(), "Chicago", SeatClass.STANDARD,
					100.0);

			System.out.println("Flight Total Price: " + computeTotalPrice(flight));

			System.out.println("Car Rental Total Price: " + computeTotalPrice(car));

			System.out.println("Train Total Price: " + computeTotalPrice(train));

		} catch (MissingInformationException | InvalidArgumentException e) {

			System.out.println("Error: " + e.getMessage());

			logger.error("Exception occurred: " + e.getMessage());
		}

		logger.info("Application is stopping");
	}

	public static double computeTotalPrice(Booking booking)
			throws MissingInformationException, InvalidArgumentException {

		return booking.calculateTotalPrice();
	}
}