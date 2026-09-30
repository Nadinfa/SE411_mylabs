package edu.psu.se411.lab07;

import java.util.Date;

import edu.psu.se411.lab07.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07.exceptions.MissingInformationException;
import edu.psu.se411.lab07.util.Config;

public class TrainBooking extends Booking {

	private SeatClass seatClass;
	private Double distance;

	public TrainBooking(String bookingId, String customerName, Date travelDate, String destinationCity,
			SeatClass seatClass, Double distance) {

		super(bookingId, customerName, travelDate, destinationCity);

		this.seatClass = seatClass;
		this.distance = distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public SeatClass getSeatClass() {
		return seatClass;
	}

	@Override
	public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {

		if (distance == null) {
			throw new MissingInformationException("Train distance is missing.");
		}

		if (distance < 1 || distance > Config.MAX_TRAIN_DISTANCE) {

			throw new InvalidArgumentException("Distance must be between 1 and " + Config.MAX_TRAIN_DISTANCE + " km.");
		}

		if (seatClass == SeatClass.STANDARD) {

			return distance * Config.TRAIN_STANDARD_RATE;

		} else {

			return distance * Config.TRAIN_FIRST_CLASS_RATE;
		}
	}
}