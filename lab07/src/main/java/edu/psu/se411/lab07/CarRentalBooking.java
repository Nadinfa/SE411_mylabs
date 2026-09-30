package edu.psu.se411.lab07;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.util.Config;

public class CarRentalBooking extends Booking {

	private double dailyRentalRate;
	private Integer numberOfDays;

	public CarRentalBooking(String bookingId, String customerName, Date travelDate, String destinationCity,
			double dailyRentalRate, Integer numberOfDays) {

		super(bookingId, customerName, travelDate, destinationCity);

		this.dailyRentalRate = dailyRentalRate;
		this.numberOfDays = numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	@Override
	public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {

		if (numberOfDays == null) {
			throw new MissingInformationException("Number of rental days is missing.");
		}

		if (numberOfDays < Config.MIN_RENTAL_DAYS || numberOfDays > Config.MAX_RENTAL_DAYS) {

			throw new InvalidArgumentException(
					"Rental days must be between " + Config.MIN_RENTAL_DAYS + " and " + Config.MAX_RENTAL_DAYS + ".");
		}

		return dailyRentalRate * numberOfDays;
	}
}