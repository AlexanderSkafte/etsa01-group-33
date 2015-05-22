package bicyclegarage;

import java.util.*;
import java.io.*;

/*
 * Glossary:
 * 	NIN = National Identification Number ("personnummer" in Swedish)
 * 	PIN = Personal Identification Number
 */

public class User implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String NIN;
    private final int PIN;

    private Set<Bicycle> bicycles;

    private final static int NIN_LENGTH = 10;

    /**
     * Creates a new user of the bicycle garage.
     * @param firstName The first name(s) of the user
     * @param lastName  The last name(s) of the user
     * @param NIN       National Identification Number of the user
     * @param PIN       Personal Identification Number of the user
     */
    public User(String firstName, String lastName, String NIN, int PIN) {
        if (firstName == null || lastName == null || NIN == null
                || !isValidNIN(NIN) || !isValidPIN(PIN)) {
            throw new IllegalArgumentException("Error! Invalid arguments to User");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.NIN = NIN;
        this.PIN = PIN;
        this.bicycles = new HashSet<Bicycle>();
    }

    /**
     * Return a string representation of the user. An example:
     *  "lastname, firstname (NIN: 900101-1234, PIN: 5678)"
     *  @return A string representation of the user
     */
    public String toString() {
        return lastName + ", " + firstName + " (NIN: " + NIN + ", PIN: " + PIN + ")";
    }
    
    /**
     * Prints bicycles that belong to this user
     */
    public void printBicycles() {
    	System.out.print("Bicycles: ");
    	for (Bicycle b : bicycles) {
    		System.out.print(b.getID() + " ");
    	}
    }
    
    /**
     * Clears all bicycles that belong to user
     */
    public void clearBicycles() {
    	this.bicycles = new HashSet<Bicycle>();
    }
    
    /**
     * Returns the first name(s) of the user.
     * @return The first name(s) of the user
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Returns the last name(s) of the user.
     * @return The last name(s) of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the national identification number of the user.
     * @return The national identification number of the user
     */
    public String getNIN() {
        return NIN;
    }

    /**
     * Returns the personal identification number of the user.
     * @return The personal identification number of the user
     */
    public int getPIN() {
        return PIN;
    }
    
    /**
     * Associates [bicycle] with the user. Returns true if [bicycle] was not
     * already associated with the user, else false.
     * @param bicycle The bicycle to associate with the user
     * @return True if bicycle could be associated with user, false otherwise
     */
    public String addBicycle(String ID) {
    	Bicycle bicycle = new Bicycle(ID);
    	
        if (bicycles.contains(bicycle)) {
            System.err.println("[!] Bicycle is already registered.");
            return null;
        }
        
        if (bicycles.add(bicycle)) {
            return bicycle.getID();
        } else {
            System.err.println("[!!!] Something went terribly wrong!"
                                + "Time to debug.");
            return null;            
        }
    }
    
    /**
     * Removes bicycle from user
     * @param ID Bicycle ID
     * @return Whether or not the operation succeeded
     */
    public boolean removeBicycle(String ID) {
    	return bicycles.remove(getBicycleByID(ID));
    }
    
    /**
     * Returns the set of all bicycles which belong to the owner.
     * @return The set of all bicycles which belong to the owner
     */
    public Set<Bicycle> getBicycles() {
        return bicycles;
    }

    /**
     * Returns the bicycle identified by [ID]. If the bicycle was
     * not found, null is returned.
     * @param ID The identification number of the bicycle to find
     * @return The bicycle identified by [ID] if found, else null
     */
    public Bicycle getBicycleByID(String ID) {
        for (Bicycle b : bicycles)
            if (b.getID().equals(ID))
                return b;
        return null;
    }

    /*
     * ===================================================================== *\
     * Static methods \*
     * =====================================================================
     */

    /**
     * Checks so that [NIN] is a valid National Identity Number
     * @param NIN The National Identification Number to check the validity of
     * @return Whether or not [NIN] was valid
     */
    public static boolean isValidNIN(String NIN) {
        boolean isValid = false;

        isValid = NIN.length() == NIN_LENGTH;

        // Add more checks eventually, perhaps with regex?

        return isValid;
    }

    public static boolean isValidPIN(int PIN) {
        return 0 <= PIN && PIN < 10000;
    }
}
