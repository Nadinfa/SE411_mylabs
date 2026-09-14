package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;

public class EX1 {

	static void ValidateAge(int age) throws InvalidAgeException

	{

		if (age < 18)

			throw new InvalidAgeException("invalide age");

		else

			System.out.println("valid age");

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {

			ValidateAge(15);

		}

		catch (InvalidAgeException e)

		{

			System.out.println(e.getMessage());

		}

	}

}