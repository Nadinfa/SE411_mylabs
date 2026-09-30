package edu.psu.se411.lab07;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;

public abstract class Booking {

	private String bookingId;
	private String customerName;
	private Date travelDate;
	private String destinationCity;

	public Booking(String bookingId, String customerName, Date travelDate, String destinationCity) {

		this.bookingId = bookingId;
		this.customerName = customerName;
		this.travelDate = travelDate;
		this.destinationCity = destinationCity;
	}

	public String getBookingId() {
		return bookingId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public abstract double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException;
}