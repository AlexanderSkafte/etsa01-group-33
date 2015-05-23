package bicyclegarage;

import java.util.Date;
import java.io.*;

public class Bicycle implements Serializable {
    private final String ID;
    private Date lastCheckedIn;
    private Date lastCheckedOut;
    private boolean isCheckedIn;

    /**
     * Creates a new Bicycle. Bicycle ID and owner can't be changed after
     * initialization.
     * @param ID The 5-digit ID number of the bicycle
     */
    public Bicycle(String ID) {
        if (ID == null) {
            throw new IllegalArgumentException(
                    "Error! Null parameter to Bicycle constructor.");
        }
        if (!ID.matches("\\d{5}")) {
            throw new IllegalArgumentException(
                    "Error! Length of bicycle ID must be exactly 5 digits.");
        }

        this.ID = ID;
        // isCheckedIn = false;
    }
    
    /**
     * Returns a string containing information about the bicycle
     */
    public String toString(){
    	String checkInDate;
    	String checkOutDate;
    	
    	if (lastCheckedOut == null) {
    		checkOutDate = "Never";
    	} else {
    		checkOutDate = lastCheckedOut.toString();
    	}

    	if (lastCheckedIn == null) {
    		checkInDate = "Never";
    	} else {
    		checkInDate = lastCheckedIn.toString();
    	}
    	return "ID: " + ID + "\n"
    			+ "Is checked in: " + isCheckedIn + "\n"
    			+ "Last checked in: " + checkInDate + "\n"
    			+ "Last checked out: " + checkOutDate;
    }
    
    /**
     * Check the bicycle is checked in
     * @return true if the bicycle is checked in. Otherwise false
     */
    public boolean isCheckedIn() {
    	return isCheckedIn;
    }

    /**
     * Get the bicycle's 5-digit ID number.
     * @return The bicycle's 5-digit ID number.
     */
    public String getID() {
        return ID;
    }

    public boolean checkIn() {
        if (isCheckedIn)
            return false;
        this.lastCheckedIn = new Date();
        this.isCheckedIn = true;
        return true;
    }

    public boolean checkOut() {
        this.lastCheckedOut = new Date();
        this.isCheckedIn = false;
        return false;
    }
}
