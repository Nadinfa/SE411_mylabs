package edu.psu.se411.lab07;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.util.Config;

public class FlightBooking extends Booking {

	private double baseTicketPrice;
	private double includedLuggageWeight;
	private Double luggageWeight;

	public FlightBooking(String bookingId, String customerName, Date travelDate, String destinationCity,
			double baseTicketPrice, double includedLuggageWeight, Double luggageWeight) {

		super(bookingId, customerName, travelDate, destinationCity);

		this.baseTicketPrice = baseTicketPrice;
		this.includedLuggageWeight = includedLuggageWeight;
		this.luggageWeight = luggageWeight;
	}

	public void setLuggageWeight(Double luggageWeight) {
		this.luggageWeight = luggageWeight;
	}

	public Double getLuggageWeight() {
		return luggageWeight;
	}

	@Override
	public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {

		if (luggageWeight == null) {
			throw new MissingInformationException("Luggage weight is missing.");
		}

		if (luggageWeight < 0 || luggageWeight > Config.MAX_LUGGAGE_WEIGHT) {

			throw new InvalidArgumentException(
					"Luggage weight must be between 0 and " + Config.MAX_LUGGAGE_WEIGHT + " kg.");
		}

		double extraWeight = luggageWeight - includedLuggageWeight;

		if (extraWeight < 0) {
			extraWeight = 0;
		}

		double priceBeforeTax = baseTicketPrice + (extraWeight * Config.EXTRA_LUGGAGE_RATE);

		return priceBeforeTax * (1 + Config.TAX_RATE);
	}
}