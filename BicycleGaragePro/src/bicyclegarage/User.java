package bicyclegarage;

import java.util.HashSet;
import java.util.Set;

/*
 * Glossary:
 * 	NIN = National Identity Number ("personnummer" in Swedish)
 * 	PIN = Personal Identification Number
 */

public class User {
	
	private final String firstName;
	private final String lastName;
	private final String NIN;
	private final int PIN;
	
	private Set<Bicycle> bicycles;
	
	private final static int NIN_LENGTH = 12;

	public User(String firstName, String lastName, String NIN, int PIN) {
		this.firstName	= firstName;
		this.lastName	= lastName;
		this.NIN		= NIN;
		this.PIN		= PIN;
		this.bicycles	= new HashSet<Bicycle>();
	}
	
	public String toString() {
		return lastName + ", " + firstName + " (NIN: " + NIN + ", PIN: " + PIN + ")";
	}
	
	public String getNIN() {
		return NIN;
	}
	
	public int getPIN() {
		return PIN;
	}
	
	public Set<Bicycle> getBicycles() {
		return bicycles;
	}
	
	/**
	 * Associates @bicycle with this user.
	 * Returns true if @bicycle was not already
	 * associated with this user.
	 */
	public boolean addBicycle(Bicycle bicycle) {
		return bicycles.add(bicycle);
	}
	
	/**
	 * Returns the bicycle identified by the @ID parameter.
	 * If the bicycle was not found, returns null.
	 */
	public Bicycle getBicycleByID(String ID) {
		for (Bicycle b : bicycles)
			if (b.getID().equals(ID))
				return b;
		return null;
	}
	
	
	/* ===================================================================== *\
	 *	Static methods
	\* ===================================================================== */
	
	public static boolean isValidNIN(String NIN) {
		boolean isValid = false;
		
		isValid = NIN.length() == NIN_LENGTH;

		// Add more checks eventually
		
		return isValid;
	}
	
	public static boolean isValidPIN(int PIN) {
		return 0 <= PIN && PIN < 10000;
	}
}
